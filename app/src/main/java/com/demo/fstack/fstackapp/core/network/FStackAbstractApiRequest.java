package com.demo.fstack.fstackapp.core.network;

public abstract class FStackAbstractApiRequest {

    private static final String TAG = FStackAbstractApiRequest.class.getSimpleName();

    protected FStackRetrofitClient mUFSWSRetroClient;
    protected IFStackEndPointsRequestAPI mUFSWSRequestAPI;

    public FStackAbstractApiRequest() {
        mUFSWSRetroClient = FStackRetrofitClient.getInstance();
        mUFSWSRequestAPI = FStackRetrofitClient.getWSAPIService();
    }

}
