package com.example.beinmediatest.ui.main.feed.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("language")
    @Expose
    public String language;
    @SerializedName("genres")
    @Expose
    public List<String> genres = null;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("runtime")
    @Expose
    public Integer runtime;
    @SerializedName("averageRuntime")
    @Expose
    public int averageRuntime;
    @SerializedName("premiered")
    @Expose
    public String premiered;
    @SerializedName("officialSite")
    @Expose
    public String officialSite;
    @SerializedName("schedule")
    @Expose
    public ScheduleResponse schedule;
    @SerializedName("rating")
    @Expose
    public RatingResponse rating;
    @SerializedName("weight")
    @Expose
    public int weight;
    @SerializedName("network")
    @Expose
    public NetworkResponse network;
    @SerializedName("webChannel")
    @Expose
    public Object webChannel;
    @SerializedName("dvdCountry")
    @Expose
    public Object dvdCountry;
    @SerializedName("externals")
    @Expose
    public ExternalsResponse externals;
    @SerializedName("image")
    @Expose
    public ImageResponse image;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("updated")
    @Expose
    public int updated;
    @SerializedName("_links")
    @Expose
    public LinksResponse links;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public int getAverageRuntime() {
        return averageRuntime;
    }

    public void setAverageRuntime(int averageRuntime) {
        this.averageRuntime = averageRuntime;
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

    public ScheduleResponse getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleResponse schedule) {
        this.schedule = schedule;
    }

    public RatingResponse getRating() {
        return rating;
    }

    public void setRating(RatingResponse rating) {
        this.rating = rating;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public NetworkResponse getNetwork() {
        return network;
    }

    public void setNetwork(NetworkResponse network) {
        this.network = network;
    }

    public Object getWebChannel() {
        return webChannel;
    }

    public void setWebChannel(Object webChannel) {
        this.webChannel = webChannel;
    }

    public Object getDvdCountry() {
        return dvdCountry;
    }

    public void setDvdCountry(Object dvdCountry) {
        this.dvdCountry = dvdCountry;
    }

    public ExternalsResponse getExternals() {
        return externals;
    }

    public void setExternals(ExternalsResponse externals) {
        this.externals = externals;
    }

    public ImageResponse getImage() {
        return image;
    }

    public void setImage(ImageResponse image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }

    public LinksResponse getLinks() {
        return links;
    }

    public void setLinks(LinksResponse links) {
        this.links = links;
    }
}

