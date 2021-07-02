package com.example.beinmediatest.ui.main.feed;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beinmediatest.api.ApiStateListener;
import com.example.beinmediatest.repository.feedrepository.FeedRepository;
import com.example.beinmediatest.ui.main.feed.model.ImageModel;
import com.example.beinmediatest.ui.main.feed.model.MoviesModel;
import com.example.beinmediatest.ui.main.feed.response.MoviesResponse;
import com.example.beinmediatest.util.AppConst;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FeedViewModel extends AndroidViewModel {

    //region Variables
    private MutableLiveData<List<MoviesModel>> movies ;
    private MutableLiveData<String> states;
    private FeedRepository feedRepository;
    private List<MoviesResponse> moviesResponses;
    private List<MoviesModel> moviesModelList;
    //endregion

    public FeedViewModel(@NonNull @NotNull Application application) {
        super(application);
        feedRepository = new FeedRepository();
        movies = new MutableLiveData<>(new ArrayList<>());
        states = new MutableLiveData<>(AppConst.NOTHING);
    }

    public void getFeeds(){
        states.postValue(AppConst.LOADING);
        feedRepository.connectToGetMovieApi(new ApiStateListener() {
            @Override
            public void onSuccess(Object... params) {
                moviesResponses = (List<MoviesResponse>) params[0];
                moviesModelList = new ArrayList<>();
                for (int i = 0; i < moviesResponses.size(); i++) {
                    MoviesModel  moviesModel = new MoviesModel();
                    moviesModel.setId(moviesResponses.get(i).getId());
                    moviesModel.setName(moviesResponses.get(i).getName());
                    moviesModel.setSummary(moviesResponses.get(i).getSummary());
                    ImageModel imageModel = new ImageModel();
                    imageModel.setMedium(moviesResponses.get(i).getImage().getMedium());
                    imageModel.setOriginal(moviesResponses.get(i).getImage().getOriginal());
                    moviesModel.setImage(imageModel);
                    moviesModelList.add(moviesModel);
                }
                movies.postValue(moviesModelList);
                states.postValue(AppConst.SUCCESS);
            }

            @Override
            public void onFailure(Object... params) {
                states.postValue(AppConst.Fail);
            }
        });
    }

    public LiveData<List<MoviesModel>> getListLiveData() {
        return movies;
    }

    public MutableLiveData<String> getStates() {
        return states;
    }
}
