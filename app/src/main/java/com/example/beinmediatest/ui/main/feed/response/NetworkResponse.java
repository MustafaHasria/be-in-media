package com.example.beinmediatest.ui.main.feed.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NetworkResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country")
    @Expose
    private CountryResponse country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryResponse getCountry() {
        return country;
    }

    public void setCountry(CountryResponse country) {
        this.country = country;
    }
}
