package com.example.beinmediatest.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class App extends Application {

    //region Variables
    private static App instance;
    //endregion

    //region Get instance
    public static App getInstance() {
        return instance;
    }
    //endregion

    //region On create

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    //endregion

    //region Methods
    public static boolean hasNetwork(){
        return instance.isNetworkConnected();
    }

    private boolean isNetworkConnected(){
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
    //endregion
}
