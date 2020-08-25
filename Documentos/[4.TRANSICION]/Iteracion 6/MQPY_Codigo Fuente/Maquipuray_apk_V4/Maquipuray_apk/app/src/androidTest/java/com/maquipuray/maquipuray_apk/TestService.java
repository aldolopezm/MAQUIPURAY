package com.maquipuray.maquipuray_apk;

import android.util.Log;

import com.maquipuray.maquipuray_apk.data.preferences.MYConstantsPreferences;
import com.maquipuray.maquipuray_apk.data.remote.ApiEndPoint;
import com.maquipuray.maquipuray_apk.data.remote.WebServicesAPIService;
import com.maquipuray.maquipuray_apk.data.remote.model.ApiResponse;
import com.maquipuray.maquipuray_apk.data.remote.model.CategoriaResponse;
import com.maquipuray.maquipuray_apk.data.remote.model.PromocionResponse;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestService {
//    @Test
//    public void testSimple() throws IOException {
//        WebServicesAPIService service = RetrofitClient.getClient();
//        Call<String> call = service.getCategorias("jorisdeguet");
//        Response<String> response = call.execute();
//        String resultat = response.body();
//        Log.i("RETROFIT", resultat);
//    }

    @Test
    public void testCategorias() throws IOException {
        WebServicesAPIService service = ApiEndPoint.GET_CLIENT_MAQUIPURAY();
        String token = MYConstantsPreferences.MAQUIPURAY_AUTHORIZATION +
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8zNS4xODQuMjAuMjI2OjIyMzNcL1VzdWFyaW9BdXRlbnRpY2FyV2ViSnNvbiIsImlhdCI6MTU5MzQ0NzgxMiwibmJmIjoxNTkzNDQ3ODEyLCJqdGkiOiJBM3lRTFJKUFpQV01tRGowIiwic3ViIjoxLCJwcnYiOiIxNDMwNmRkOGI2OWUyNTIyZGExOTU3NTliYmUxMjJiY2QxZTUyMmQxIn0.8sKoCWGvXuzEpPNGg1yB0dZ2EqZfgeUGU4J2XMMMCyA";

        Call<ApiResponse<CategoriaResponse>> call = service.getCategorias(token);
        Response<ApiResponse<CategoriaResponse>> response = call.execute();

        ApiResponse<CategoriaResponse> resultat = response.body();

        Log.i("RETROFIT_CATEGORIAS", resultat.getData().toString());

    }

    @Test
    public void testPromocionesxCategoria() throws IOException {
        WebServicesAPIService service = ApiEndPoint.GET_CLIENT_MAQUIPURAY();
        String token = MYConstantsPreferences.MAQUIPURAY_AUTHORIZATION +
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8zNS4xODQuMjAuMjI2OjIyMzNcL1VzdWFyaW9BdXRlbnRpY2FyV2ViSnNvbiIsImlhdCI6MTU5MzQ0NzgxMiwibmJmIjoxNTkzNDQ3ODEyLCJqdGkiOiJBM3lRTFJKUFpQV01tRGowIiwic3ViIjoxLCJwcnYiOiIxNDMwNmRkOGI2OWUyNTIyZGExOTU3NTliYmUxMjJiY2QxZTUyMmQxIn0.8sKoCWGvXuzEpPNGg1yB0dZ2EqZfgeUGU4J2XMMMCyA";

        Call<ApiResponse<PromocionResponse>> call = service.getPromocionesXCategoria(token, 1);
        Response<ApiResponse<PromocionResponse>> response = call.execute();
        ApiResponse<PromocionResponse> resultat = response.body();
        Log.i("RETROFIT_PROMOCIONES", resultat.getData().toString());
    }
//
//    @Test
//    public void testSimpleUtilisateurStructure() throws IOException {
//        WebServicesAPIService service = RetrofitClient.getClient();
//        Call<Utilisateur> call = service.utilisateur("jorisdeguet");
//        Response<Utilisateur> response = call.execute();
//        Utilisateur resultat = response.body();
//        Log.i("RETROFIT", resultat.toString());
//    }
}
