package com.demo.fstack.fstackapp.presenter;

import com.demo.fstack.fstackapp.core.presentation.presenter.BasePresenter;

public interface IAlienPresenter extends BasePresenter {
    void searchAliens(String apiKey, String query, String year, String type, int pageNo);

    void getAlienDetails(String apiKey, String imdbId);
}
