package com.demo.fstack.fstackapp.core;

public interface BaseUseCase {

    /**
     * This is the main method that starts a usecase. It will make sure that the usecase operation is done on a
     * background thread.
     */
    void execute();
}