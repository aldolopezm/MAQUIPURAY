package com.maquipuray.maquipuray_apk.data.remote.mock;

import com.maquipuray.maquipuray_apk.data.remote.Service2;
import com.maquipuray.maquipuray_apk.data.remote.model.ApiResponse;
import com.maquipuray.maquipuray_apk.data.remote.model.CategoriaResponse;
import com.maquipuray.maquipuray_apk.data.remote.model.PromocionResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

/**
 * Created by rogergcc on 16/07/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class MockService2 implements Service2 {

    private BehaviorDelegate<Service2> delegate;

    public MockService2(BehaviorDelegate<Service2> delegate){
        this.delegate = delegate;
    }

    @Override
    public Call<ApiResponse<CategoriaResponse>> getCategorias(String token) {
        List<CategoriaResponse> lista = new ArrayList<>();
        ApiResponse<CategoriaResponse> api = new ApiResponse<>();
        for (int i = 0 ; i < 10 ; i++) {
            CategoriaResponse u = new CategoriaResponse();
            u.setIdCategoria(i);
            u.setNombre("Mock"+ i);
            lista.add(u);
        }
        api.setData(lista);
        return this.delegate.returningResponse(api).getCategorias(token);
    }

    @Override
    public Call<ApiResponse<PromocionResponse>> getPromocionesXCategoria(String token, int idCategoria) {
        List<PromocionResponse> lista = new ArrayList<>();
        ApiResponse<PromocionResponse> api = new ApiResponse<>();
        for (int i = 0 ; i < 10 ; i++) {
            PromocionResponse u = new PromocionResponse();
            u.setIdPromocion(i);
            u.setIdCategoria("2");
            u.setNombre("Mock"+ i);
            lista.add(u);
        }
        api.setData(lista);
        return this.delegate.returningResponse(api).getPromocionesXCategoria(token,idCategoria);
    }

}