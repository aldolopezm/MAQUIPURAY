package com.maquipuray.maquipuray_apk;


import android.app.Application;
import android.content.Context;

import com.maquipuray.maquipuray_apk.helpers.ConnectivityReceiver;

/**
 * Created by  on 29/06/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class MyApp extends Application {

    private static Context mContext;
    private static MyApp mInstance;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mInstance = this;
        mContext = getApplicationContext();
    }

    public static synchronized MyApp getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
