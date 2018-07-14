package com.demo.fstack.fstackapp.core;

public interface Executor {

    /**
     * This method should call the usecase's run method and thus start the usecase. This should be called
     * on a background thread as usecases might do lengthy operations.
     *
     * @param usecase The usecase to run.
     */
    void execute(final Usecase usecase);
}