package com.example.beinmediatest.ui.main.feed.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageModel {
    //region Property
    private String medium;
    private String original;
    //endregion

    //region Getters & Setters
    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
    //endregion
}
