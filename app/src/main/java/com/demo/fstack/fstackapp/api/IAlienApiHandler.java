package com.demo.fstack.fstackapp.api;

import android.util.Log;

import com.demo.fstack.fstackapp.core.network.FStackAbstractApiRequest;
import com.demo.fstack.fstackapp.core.network.FStackRetrofitClient;
import com.demo.fstack.fstackapp.model.AlienDetailModel;
import com.demo.fstack.fstackapp.model.AlienResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IAlienApiHandler extends FStackAbstractApiRequest {
    private static final String TAG = IAlienApiHandler.class.getSimpleName();

    private FStackRetrofitClient.onResponseReceived mCallbackListener;

    public IAlienApiHandler() {
        Log.d(TAG, "START of IAlienApiHandler");

        Log.d(TAG, "COMPLETION of IAlienApiHandler");
    }

    public void fetchAliens(FStackRetrofitClient.onResponseReceived callbackListener, String apiKey, String query, String year, String searchType, int pageNo) {
        Log.d("fetchAliens", "start fetchAliens()::  apiKey-->" + apiKey);

        mCallbackListener = callbackListener;
        Call<AlienResponseModel> modelCall = FStackRetrofitClient.getWSAPIService()
                .searchAliens(apiKey, query, year, searchType, pageNo);
        modelCall.enqueue(new Callback<AlienResponseModel>() {
            @Override
            public void onResponse(Call<AlienResponseModel> call, Response<AlienResponseModel> response) {
                Log.d(TAG, "START of onResponse");
                mUFSWSRetroClient.onResponse(response, mCallbackListener);
                Log.d(TAG, "COMPLETION of onResponse");
            }

            @Override
            public void onFailure(Call<AlienResponseModel> call, Throwable t) {
                Log.d(TAG, "START of onFailure");
                mCallbackListener.onResponseFailure(t.toString());
                Log.d(TAG, "COMPLETION of onFailure" + t.toString());
            }
        });
    }

    public void fetchAlienDetails(FStackRetrofitClient.onResponseReceived callbackListener, String apiKey, String imdbId) {
        Log.d("fetchAlienDetails", "start fetchAlienDetails()::  apiKey-->" + apiKey);

        mCallbackListener = callbackListener;
        Call<AlienDetailModel> modelCall = FStackRetrofitClient.getWSAPIService()
                .getAlienDetail(apiKey, imdbId);
        modelCall.enqueue(new Callback<AlienDetailModel>() {
            @Override
            public void onResponse(Call<AlienDetailModel> call, Response<AlienDetailModel> response) {
                Log.d(TAG, "START of onResponse");
                mUFSWSRetroClient.onResponse(response, mCallbackListener);
                Log.d(TAG, "COMPLETION of onResponse");
            }

            @Override
            public void onFailure(Call<AlienDetailModel> call, Throwable t) {
                Log.d(TAG, "START of onFailure");
                mCallbackListener.onResponseFailure(t.toString());
                Log.d(TAG, "COMPLETION of onFailure" + t.toString());
            }
        });
    }
}

