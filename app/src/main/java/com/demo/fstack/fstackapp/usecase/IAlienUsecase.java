package com.demo.fstack.fstackapp.usecase;

import com.demo.fstack.fstackapp.core.BaseUseCase;
import com.demo.fstack.fstackapp.core.network.FStackRetrofitClient;

public interface IAlienUsecase  extends BaseUseCase {
    void actionFetchAliens(FStackRetrofitClient.onResponseReceived callbackListener, String apiKey, String query, String year, String searchType, int pageNo, int type);
    void actionGetAlienDetails(FStackRetrofitClient.onResponseReceived callbackListener, String apiKey, String imdbId, int type);
}