package com.example.beinmediatest.api;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    //region Variables
    public static Retrofit retrofit = null;
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

    //endregion


    //region Handle headers for api calls
    private static OkHttpClient GetHttpOkClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        return httpClientBuilder.build();
    }

    //endregion

}