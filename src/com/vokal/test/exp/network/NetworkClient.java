package com.vokal.test.exp.network;

import java.util.HashMap;

public class NetworkClient {
    protected AbstractNetworkImpl mImpl;
    private static NetworkClient sInstance;

    public static NetworkClient getInstance() { 
        if (sInstance == null) {
            sInstance = new NetworkClient();
        }

        return sInstance;
    }

    public void setImpl(AbstractNetworkImpl aImpl) {
        mImpl = aImpl;
    }

    public String get(String aUrl, HashMap<String, String> aParams) {
        if (mImpl != null) {
            try {
                return mImpl.get(aUrl, aParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String post(String aUrl, HashMap<String, String> aParams) {
        if (mImpl != null) {
            try {
                return mImpl.post(aUrl, aParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    public String put(String aUrl, HashMap<String, String> aParams) {
        if (mImpl != null) {
            try {
                return mImpl.put(aUrl, aParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String delete(String aUrl) {
        if (mImpl != null) {
            try {
                return mImpl.delete(aUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
