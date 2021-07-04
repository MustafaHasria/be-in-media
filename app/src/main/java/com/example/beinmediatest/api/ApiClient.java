package com.example.beinmediatest.api;


import com.example.beinmediatest.app.App;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;


public class ApiClient {

    //region Variables
    public static Retrofit retrofit = null;
    public static long cacheSize = 200 * 1024 * 1024;
    //endregion

    //region Get retrofit api client
    public static Retrofit getAPIClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl("http://api.tvmaze.com/").
                    client(GetHttpOkClient()).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    private static Cache cache() {
        return new Cache(App.getInstance().getCacheDir(), cacheSize);
    }


    //endregion

    //region Handle headers for api calls
    private static OkHttpClient GetHttpOkClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .cache(cache())
                .retryOnConnectionFailure(true)
                .addInterceptor(offlineInterceptor());

        return httpClientBuilder.build();
    }

    private static Interceptor offlineInterceptor() {
        return chain -> {
            Request request = chain.request();

            if (!App.hasNetwork()) {

                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build();

                request = request.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .cacheControl(cacheControl)
                        .build();
            } else {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(5, SECONDS)
                        .build();
                Request original = chain.request();
                request = original.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", cacheControl.toString())
                        .method(original.method(), original.body())
                        .build();
            }
            return chain.proceed(request);
        };
    }

    //endregion

}