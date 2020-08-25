package com.maquipuray.maquipuray_apk.ui.datos;

import android.util.Log;

import androidx.annotation.NonNull;

import com.maquipuray.maquipuray_apk.MyApp;
import com.maquipuray.maquipuray_apk.data.preferences.MYConstantsPreferences;
import com.maquipuray.maquipuray_apk.data.preferences.MySharedPreference;
import com.maquipuray.maquipuray_apk.data.remote.ApiEndPoint;
import com.maquipuray.maquipuray_apk.data.remote.model.ApiResponse;
import com.maquipuray.maquipuray_apk.data.remote.model.CategoriaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by rogergcc on 8/08/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class GetDataPromociones {


    public void GetPromociones(){

        String MiToken = MYConstantsPreferences.MAQUIPURAY_AUTHORIZATION + MySharedPreference.getInstance(MyApp.getContext()).ObtenerToken();
        ApiEndPoint.GET_CLIENT_MAQUIPURAY().getCategorias(MiToken)
                .enqueue(new Callback<ApiResponse<CategoriaResponse>>() {
                    @Override
                    public void onResponse(@NonNull Call<ApiResponse<CategoriaResponse>> call, @NonNull Response<ApiResponse<CategoriaResponse>> response) {
//                        progress_circular.setVisibility(View.GONE);
                        if (response.body() != null) {
                            Log.e(TAG, "onResponse: " + response.body().getData().toString());


                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse<CategoriaResponse>> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure: Fallo la respuesta", t);
//                        progress_circular.setVisibility(View.GONE);
                    }
                });
    }


}
