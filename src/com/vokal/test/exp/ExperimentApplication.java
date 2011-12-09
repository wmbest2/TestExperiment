package com.vokal.test.exp;

import android.app.Application;

import com.vokal.test.exp.network.*;

public class ExperimentApplication extends Application {

    @Override
    public void onCreate() {
        NetworkClient.getInstance().setImpl(new NetworkUtilsImpl());
    }
}
