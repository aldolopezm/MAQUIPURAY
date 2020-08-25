package com.maquipuray.maquipuray_apk;

import android.util.Log;

import com.maquipuray.maquipuray_apk.data.remote.Service2;
import com.maquipuray.maquipuray_apk.data.remote.mock.RetrofitMock;
import com.maquipuray.maquipuray_apk.data.remote.model.ApiResponse;
import com.maquipuray.maquipuray_apk.data.remote.model.CategoriaResponse;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by rogergcc on 16/07/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class TestService2 {


    @Test
    public void testSimpleService2() throws IOException {
        Service2 service = RetrofitMock.getServiCliente();

//        Call<CategoriaResponse> call = service.creer(new CategoriaResponse());
//        Response<Utilisateur> response = call.execute();
//        Utilisateur resultat = response.body();
//        Log.i("RETROFIT", resultat.toString());


        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8zNS4xODQuMjAuMjI2OjIyMzNcL1VzdWFyaW9BdXRlbnRpY2FyV2ViSnNvbiIsImlhdCI6MTU5MzQ0NzgxMiwibmJmIjoxNTkzNDQ3ODEyLCJqdGkiOiJBM3lRTFJKUFpQV01tRGowIiwic3ViIjoxLCJwcnYiOiIxNDMwNmRkOGI2OWUyNTIyZGExOTU3NTliYmUxMjJiY2QxZTUyMmQxIn0.8sKoCWGvXuzEpPNGg1yB0dZ2EqZfgeUGU4J2XMMMCyA";

//        ApiResponse<PromocionResponse> responsoPromocion = service.getPromocionesXCategoria(token,1).execute().body();
//        Log.i("RETROFIT", responsoPromocion.getData().size()+"");
//

        ApiResponse<CategoriaResponse> responseCategorias = service.getCategorias(token).execute().body();
        Log.i("RETROFIT", responseCategorias.getData().size() + "");
    }
}
