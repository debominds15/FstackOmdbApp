package com.demo.fstack.fstackapp.repository;

import com.demo.fstack.fstackapp.core.network.FStackRetrofitClient;

public interface IAlienServiceRepository {
    interface IRemoteTasks {
        void getAliens(FStackRetrofitClient.onResponseReceived callbackListener, String apiKey, String query, String year, String searchType, int pageNo);

        void fetchAlienDetails(FStackRetrofitClient.onResponseReceived callbackListener, String apiKey, String imdbId);
    }
}
