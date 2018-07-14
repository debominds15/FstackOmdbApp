package com.demo.fstack.fstackapp.core.network;

import android.util.Log;

import com.demo.fstack.fstackapp.util.AppConstants;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class FStackRetrofitClient {

    private static FStackRetrofitClient mFStackRetrofitClient;
    private static IFStackEndPointsRequestAPI mWSAPIService;
    private static final String TAG = FStackRetrofitClient.class.getName();
    private static Retrofit retrofit = null;

    /**
     * Return the instance of singleton
     *
     * @return
     */
    public static FStackRetrofitClient getInstance() {
        mFStackRetrofitClient = new FStackRetrofitClient();
        return mFStackRetrofitClient;
    }


    private FStackRetrofitClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mWSAPIService = retrofit.create(IFStackEndPointsRequestAPI.class);
    }

    /**
     * Gets the instance of the service API interafce
     *
     * @return
     */
    public static IFStackEndPointsRequestAPI getWSAPIService() {
        return mWSAPIService;
    }

    /**
     * Responsible for checking the response and pushing to appropriate callback
     *
     * @param response
     * @param callback
     * @param <T>
     */
    public <T> void onResponse(retrofit2.Response<T> response, FStackRetrofitClient.onResponseReceived<T> callback) {
        String errMessage = "Unknown error";
        T result = null;
        /**
         * A 401 (or any non-2xx response code) will actually go to the response callback, because it was a successful response even though
         * it may not have been a successful operation on the server. You can check this in onResponse by calling response.isSuccess().
         */
        Log.d(TAG, "Code :" + response.code() + "," + "Status :" + response.isSuccessful());
        if ((response.code() == HttpURLConnection.HTTP_OK
                || response.code() == HttpURLConnection.HTTP_ACCEPTED
                || response.code() == HttpURLConnection.HTTP_CREATED) && (response.isSuccessful())) {
            if (response.body() != null) {
                result = response.body();
                Log.e(TAG, "" + result.toString());
            } else {
                errMessage = "Parser error";
                Log.e(TAG, "" + errMessage);
            }
        } else {
            try {
                errMessage = response.errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
                errMessage = "Parser error";
            }
            Log.e(TAG, "" + errMessage);
        }

        if (result != null) {
            callback.onResponseSuccess(result);
        }
    }

    /**
     * @param <T>
     */
    public interface onResponseReceived<T> {
        void onResponseSuccess(T object);

        void onResponseFailure(String error);
    }
}
