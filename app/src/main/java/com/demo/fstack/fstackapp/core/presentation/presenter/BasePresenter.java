package com.demo.fstack.fstackapp.core.presentation.presenter;

public interface BasePresenter {

    /**
     * Method that should signal the appropriate view to show the appropriate error with the provided message.
     */
    void onError(String message);
}