package com.example.beinmediatest.repository.feedrepository;

import com.example.beinmediatest.api.ApiClient;
import com.example.beinmediatest.api.ApiStateListener;
import com.example.beinmediatest.api.endpoints.ShowMovieApis;
import com.example.beinmediatest.ui.main.feed.response.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedRepository {

    //region Variables
    private ShowMovieApis showMovieApis;
    Call<List<MoviesResponse>> moviesResponseCall;
    //endregion

    //region Constructor
    public FeedRepository() {
        showMovieApis = ApiClient.getAPIClient().create(ShowMovieApis.class);
    }
    //endregion

    //region Get home data
    public void connectToGetMovieApi(final ApiStateListener apiStateListener) {
        moviesResponseCall = showMovieApis.getMovies();
        moviesResponseCall.enqueue(new Callback<List<MoviesResponse>>() {
            @Override
            public void onResponse(Call<List<MoviesResponse>> call, Response<List<MoviesResponse>> response) {
                if (response.body() == null) {
                    try {
                        apiStateListener.onFailure(700, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (response.code() == 200)
                        apiStateListener.onSuccess(response.body());
                    else
                        apiStateListener.onFailure(response.code(), false);
                }
            }

            @Override
            public void onFailure(Call<List<MoviesResponse>> call, Throwable t) {
                apiStateListener.onFailure(null, true);
            }
        });
    }

    //endregion

}
