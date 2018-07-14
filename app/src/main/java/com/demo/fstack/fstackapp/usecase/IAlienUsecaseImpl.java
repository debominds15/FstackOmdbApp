package com.demo.fstack.fstackapp.usecase;


import com.demo.fstack.fstackapp.core.Executor;
import com.demo.fstack.fstackapp.core.MainThread;
import com.demo.fstack.fstackapp.core.Usecase;
import com.demo.fstack.fstackapp.core.network.FStackRetrofitClient;
import com.demo.fstack.fstackapp.repository.IAlienServiceRepository;
import com.demo.fstack.fstackapp.repository.IAlienServiceRepositoryImpl;

public class IAlienUsecaseImpl extends Usecase implements IAlienUsecase {

    private IAlienServiceRepository.IRemoteTasks taskRegisterRepository;
    private FStackRetrofitClient.onResponseReceived mRegisterCallbackListener;
    private int mType;
    private String mApiKey;
    private String mSearchType;
    private String mSearchYear;
    private String mQuery;
    private String mImdbId;
    private int mPageNo;

    public IAlienUsecaseImpl(Executor threadExecutor, MainThread mainThread) {
        super(threadExecutor, mainThread);
        taskRegisterRepository = new IAlienServiceRepositoryImpl();
    }

    @Override
    public void run() {
        switch (mType) {
            case 1:
                taskRegisterRepository.getAliens(mRegisterCallbackListener, mApiKey, mQuery, mSearchYear, mSearchType, mPageNo);
                break;
            case 2:
                taskRegisterRepository.fetchAlienDetails(mRegisterCallbackListener, mApiKey, mImdbId);
                break;
        }

    }

    @Override
    public void actionFetchAliens(FStackRetrofitClient.onResponseReceived callbackListener, String apiKey, String query, String year, String searchType, int pageNo, int type) {
        this.mRegisterCallbackListener = callbackListener;
        mApiKey = apiKey;
        mQuery = query;
        mType = type;
        mPageNo = pageNo;
        mSearchType = searchType;
        mSearchYear = year;
    }

    @Override
    public void actionGetAlienDetails(FStackRetrofitClient.onResponseReceived callbackListener, String apiKey, String imdbId, int type) {
        this.mRegisterCallbackListener = callbackListener;
        mApiKey = apiKey;
        mImdbId = imdbId;
        mType = type;
    }
}