package com.example.beinmediatest.api;

public interface ApiStateListener {

    //region onSuccess
    void onSuccess(Object... params);
    //endregion

    //region onFailure
    void onFailure(Object... params);
    //endregion

}
