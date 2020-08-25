package com.maquipuray.maquipuray_apk.data.remote.mock;

import com.maquipuray.maquipuray_apk.data.remote.ApiEndPoint;
import com.maquipuray.maquipuray_apk.data.remote.RetrofitClient;
import com.maquipuray.maquipuray_apk.data.remote.Service2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

/**
 * Created by rogergcc on 16/07/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class RetrofitMock {


    static boolean reel = false;

    public static Service2 getServiCliente(){
        if (reel) return getClinet();
        else{return getClintMock(); }
    }

    public static Service2 getClinet(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(RetrofitClient.provideOkHttp())
                .baseUrl(ApiEndPoint.API_MAQUIPURAY)
                .build();

        return retrofit.create(Service2.class);
    }

    public static Service2 getClintMock(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(RetrofitClient.provideOkHttp())
                .baseUrl(ApiEndPoint.API_MAQUIPURAY)
                .build();

        NetworkBehavior behviour = NetworkBehavior.create();
        MockRetrofit mock = new MockRetrofit.Builder(retrofit).networkBehavior(behviour).build();
        BehaviorDelegate<Service2> delegate = mock.create(Service2.class);

        return new MockService2(delegate);
    }
}
