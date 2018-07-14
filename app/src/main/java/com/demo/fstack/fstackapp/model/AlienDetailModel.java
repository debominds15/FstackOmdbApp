package com.demo.fstack.fstackapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlienDetailModel {
    @SerializedName("Released")
    private String released;

    @SerializedName("Website")
    private String website;

    @SerializedName("Type")
    private String type;

    @SerializedName("imdbVotes")
    private String imdbVotes;

    @SerializedName("Ratings")
    private List<Ratings> ratings;

    @SerializedName("Runtime")
    private String runtime;

    @SerializedName("Response")
    private String response;

    @SerializedName("Poster")
    private String poster;

    @SerializedName("imdbID")
    private String imdbID;

    @SerializedName("Country")
    private String country;

    @SerializedName("BoxOffice")
    private String boxOffice;

    @SerializedName("Title")
    private String title;

    @SerializedName("DVD")
    private String dvd;

    @SerializedName("imdbRating")
    private String imdbRating;

    @SerializedName("Year")
    private String year;

    @SerializedName("Rated")
    private String rated;

    @SerializedName("Actors")
    private String actors;

    @SerializedName("Plot")
    private String plot;

    @SerializedName("Metascore")
    private String metascore;

    @SerializedName("Writer")
    private String writer;

    @SerializedName("Production")
    private String production;

    @SerializedName("Genre")
    private String genre;

    @SerializedName("Language")
    private String language;

    @SerializedName("Awards")
    private String awards;

    @SerializedName("Director")
    private String director;

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public List<Ratings> getRatings() {
        return ratings;
    }

    public void setRatings(List<Ratings> ratings) {
        this.ratings = ratings;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDVD() {
        return dvd;
    }

    public void setDVD(String dvd) {
        this.dvd = dvd;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "ClassPojo [Released = " + released + ", Website = " + website + ", Type = " + type + ", imdbVotes = " + imdbVotes + ", Ratings = " + ratings + ", Runtime = " + runtime + ", Response = " + response + ", Poster = " + poster + ", imdbID = " + imdbID + ", Country = " + country + ", BoxOffice = " + boxOffice + ", Title = " + title + ", DVD = " + dvd + ", imdbRating = " + imdbRating + ", Year = " + year + ", Rated = " + rated + ", Actors = " + actors + ", Plot = " + plot + ", Metascore = " + metascore + ", Writer = " + writer + ", Production = " + production + ", Genre = " + genre + ", Language = " + language + ", Awards = " + awards + ", Director = " + director + "]";
    }
}