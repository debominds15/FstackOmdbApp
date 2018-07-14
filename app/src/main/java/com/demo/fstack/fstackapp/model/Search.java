package com.demo.fstack.fstackapp.model;

import com.google.gson.annotations.SerializedName;

public class Search {
    @SerializedName("Year")
    private String year;

    @SerializedName("Type")
    private String type;

    @SerializedName("Poster")
    private String poster;

    @SerializedName("imdbID")
    private String imdbID;

    @SerializedName("Title")
    private String title;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ClassPojo [Year = " + year + ", Type = " + type + ", Poster = " + poster + ", imdbID = " + imdbID + ", Title = " + title + "]";
    }
}