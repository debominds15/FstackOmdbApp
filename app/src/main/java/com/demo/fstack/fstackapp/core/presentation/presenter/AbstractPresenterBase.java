package com.demo.fstack.fstackapp.core.presentation.presenter;


import com.demo.fstack.fstackapp.core.Executor;
import com.demo.fstack.fstackapp.core.MainThread;
import com.demo.fstack.fstackapp.core.MainThreadImpl;
import com.demo.fstack.fstackapp.core.ThreadExecutor;


public abstract class AbstractPresenterBase {
    protected Executor mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenterBase() {
        mExecutor = ThreadExecutor.getInstance();
        mMainThread = MainThreadImpl.getInstance();
    }
}