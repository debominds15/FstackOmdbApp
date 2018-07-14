package com.demo.fstack.fstackapp;

import com.demo.fstack.fstackapp.core.presentation.view.BaseView;
import com.demo.fstack.fstackapp.model.AlienDetailModel;
import com.demo.fstack.fstackapp.model.AlienResponseModel;

public interface IAlienContract {
    interface IAlienView extends BaseView {
        void searchAliensResponse(AlienResponseModel model);
        void getAlienDetailsResponse(AlienDetailModel model);
    }
    void switchToAllAliensFragment(String query, String year, String type);
    void switchToAllCustomAliensFragment();
    void switchToAlienDetailFragment(String imdbId);
}
