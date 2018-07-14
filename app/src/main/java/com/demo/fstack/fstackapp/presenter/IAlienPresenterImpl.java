package com.demo.fstack.fstackapp.presenter;

import com.demo.fstack.fstackapp.IAlienContract;
import com.demo.fstack.fstackapp.core.network.FStackRetrofitClient;
import com.demo.fstack.fstackapp.core.presentation.presenter.AbstractPresenterBase;
import com.demo.fstack.fstackapp.model.AlienDetailModel;
import com.demo.fstack.fstackapp.model.AlienResponseModel;
import com.demo.fstack.fstackapp.usecase.IAlienUsecase;
import com.demo.fstack.fstackapp.usecase.IAlienUsecaseImpl;

public class IAlienPresenterImpl extends AbstractPresenterBase implements IAlienPresenter, FStackRetrofitClient.onResponseReceived {
    private static final String TAG = IAlienPresenterImpl.class.getSimpleName();
    private IAlienContract.IAlienView mView;
    private IAlienUsecase mUsecase;
    private static final int SEARCH_ALIENS_TYPE = 1;
    private static final int FETCH_ALIEN_DETAILS_TYPE = 2;

    public IAlienPresenterImpl(IAlienContract.IAlienView mView) {
        this.mView = mView;
    }

    @Override
    public void searchAliens(String apiKey, String query, String year, String type, int pageNo) {
        mView.showProgress();
        mUsecase = new IAlienUsecaseImpl(mExecutor, mMainThread);
        mUsecase.actionFetchAliens(this, apiKey, query, year, type, pageNo, SEARCH_ALIENS_TYPE);
        mUsecase.execute();
    }

    @Override
    public void getAlienDetails(String apiKey, String imdbId) {
        mView.showProgress();
        mUsecase = new IAlienUsecaseImpl(mExecutor, mMainThread);
        mUsecase.actionGetAlienDetails(this, apiKey, imdbId, FETCH_ALIEN_DETAILS_TYPE);
        mUsecase.execute();
    }

    @Override
    public void onError(String message) {
        mView.hideProgress();
    }

    @Override
    public void onResponseSuccess(Object object) {
        mView.hideProgress();
        if (object instanceof AlienResponseModel) {
            AlienResponseModel model = (AlienResponseModel) object;
            mView.searchAliensResponse(model);
        }

        if (object instanceof AlienDetailModel) {
            AlienDetailModel model = (AlienDetailModel) object;
            mView.getAlienDetailsResponse(model);
        }
    }

    @Override
    public void onResponseFailure(String error) {
        mView.hideProgress();
    }
}
