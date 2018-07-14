package com.demo.fstack.fstackapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlienResponseModel {
    @SerializedName("Search")
    private List<Search> searches;

    @SerializedName("totalResults")
    private String totalResults;

    @SerializedName("Response")
    private String response;

    public List<Search> getSearch() {
        return searches;
    }

    public void setSearch(List<Search> search) {
        this.searches = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ClassPojo [Search = " + searches + ", totalResults = " + totalResults + ", Response = " + response + "]";
    }
}