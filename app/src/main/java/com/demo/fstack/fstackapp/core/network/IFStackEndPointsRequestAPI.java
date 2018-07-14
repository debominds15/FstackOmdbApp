package com.demo.fstack.fstackapp.core.network;

import com.demo.fstack.fstackapp.model.AlienDetailModel;
import com.demo.fstack.fstackapp.model.AlienResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IFStackEndPointsRequestAPI {
    //Search movies
    @GET("/")
    Call<AlienResponseModel> searchAliens(@Query("apikey") String apiKey,
                                          @Query("s") String query,
                                          @Query("y") String year,
                                          @Query("type") String type,
                                          @Query("page") int page);

    //Movie Detail Page
    @GET("/")
    Call<AlienDetailModel> getAlienDetail(@Query("apikey") String apiKey,
                                          @Query("i") String imdbId);

}
