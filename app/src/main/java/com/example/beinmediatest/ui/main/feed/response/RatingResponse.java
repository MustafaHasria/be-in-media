package com.example.beinmediatest.ui.main.feed.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingResponse {
    @SerializedName("average")
    @Expose
    private Float average;

    public Float getAverage() {
        return average;
    }

    public void setAverage(Float average) {
        this.average = average;
    }
}
