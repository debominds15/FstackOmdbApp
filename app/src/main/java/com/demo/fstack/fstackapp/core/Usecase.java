package com.demo.fstack.fstackapp.core;

public abstract class Usecase implements BaseUseCase {

    protected Executor mThreadExecutor;
    protected MainThread mMainThread;

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    public Usecase(Executor threadExecutor, MainThread mainThread) {
        mThreadExecutor = threadExecutor;
        mMainThread = mainThread;
    }

    /**
     * This method contains the actual business logic of the usecase. It SHOULD NOT BE USED DIRECTLY but, instead, a
     * developer should call the execute() method of an usecase to make sure the operation is done on a background thread.
     * <p/>
     * This method should only be called directly while doing unit/integration tests. That is the only reason it is declared
     * public as to help with easier testing.
     */
    public abstract void run();

    public void cancel() {
        mIsCanceled = true;
        mIsRunning = false;
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    public void onFinished() {
        mIsRunning = false;
        mIsCanceled = false;
    }

    public void execute() {
        // mark this usecase as running
        this.mIsRunning = true;
        // start running this usecase in a background thread
        mThreadExecutor.execute(this);
    }

}