package com.example.beinmediatest.ui.main.feed.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleResponse {
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("days")
    @Expose
    private List<String> days = null;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }
}
