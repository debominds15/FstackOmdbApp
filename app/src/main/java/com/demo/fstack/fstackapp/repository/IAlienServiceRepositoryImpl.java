package com.demo.fstack.fstackapp.repository;

import com.demo.fstack.fstackapp.api.IAlienApiHandler;
import com.demo.fstack.fstackapp.core.network.FStackRetrofitClient;

public class IAlienServiceRepositoryImpl implements IAlienServiceRepository.IRemoteTasks {
    @Override
    public void getAliens(FStackRetrofitClient.onResponseReceived callbackListener, String apiKey, String query, String year, String searchType, int pageNo) {
        new IAlienApiHandler().fetchAliens(callbackListener, apiKey, query, year, searchType, pageNo);
    }

    @Override
    public void fetchAlienDetails(FStackRetrofitClient.onResponseReceived callbackListener, String apiKey, String imdbId) {
        new IAlienApiHandler().fetchAlienDetails(callbackListener, apiKey, imdbId);
    }
}
