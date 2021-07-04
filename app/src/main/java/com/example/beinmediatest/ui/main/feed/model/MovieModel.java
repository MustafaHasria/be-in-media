package com.example.beinmediatest.ui.main.feed.model;

import com.example.beinmediatest.ui.main.feed.response.ExternalsResponse;
import com.example.beinmediatest.ui.main.feed.response.ImageResponse;
import com.example.beinmediatest.ui.main.feed.response.LinksResponse;
import com.example.beinmediatest.ui.main.feed.response.NetworkResponse;
import com.example.beinmediatest.ui.main.feed.response.RatingResponse;

public class MoviesModel {

    private int id;
    private String name;
    private String type;
    private String language;
    private String status;
    private String premiered;
    private String officialSite;
    private ImageModel image;
    private String summary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPremiered() {
        return premiered;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    public ImageModel getImage() {
        return image;
    }

    public void setImage(ImageModel image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}

