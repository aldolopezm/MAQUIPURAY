package com.maquipuray.maquipuray_apk.data.remote;

import com.maquipuray.maquipuray_apk.data.remote.model.ApiResponse;
import com.maquipuray.maquipuray_apk.data.remote.model.CategoriaResponse;
import com.maquipuray.maquipuray_apk.data.remote.model.PromocionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by rogergcc on 16/07/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */

public interface Service2 {

//    @POST("create")
//    Call<Utilisateur> creer(Utilisateur utilisateur);
//
//    @GET("utilisateurs")
//    Call<List<Utilisateur>> utilisateurs();

    @Headers("Content-Type: application/json")
    @GET("CategoriaListarJson")
    Call<ApiResponse<CategoriaResponse>> getCategorias(@Header("Authorization") String token);


    @Headers("Content-Type: application/json")
    @GET("PromocionListarCategoriaJson")
    Call<ApiResponse<PromocionResponse> > getPromocionesXCategoria(@Header("Authorization") String token,
                                                                   @Query("idCategoria") int idCategoria);

}