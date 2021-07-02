package com.example.beinmediatest.api.endpoints;


import com.example.beinmediatest.ui.main.feed.response.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShowMovieApis {
    String SHOW_ALL = "shows";

    @GET(SHOW_ALL)
    Call<List<MoviesResponse>> getMovies();
}
