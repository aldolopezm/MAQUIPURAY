package com.maquipuray.maquipuray_apk.ui.pedidos;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.data.preferences.MYConstantsPreferences;
import com.maquipuray.maquipuray_apk.data.preferences.MySharedPreference;
import com.maquipuray.maquipuray_apk.data.remote.ApiEndPoint;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pedido extends AppCompatActivity {

    private static final String TAG = "Pedido";
    Context mcontext;
    private int idUsuario;
    private int idPromocion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        mcontext = this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        final EditText edt_codigo_usuario = findViewById(R.id.edt_codigo_usuario);
        final EditText edt_codigo_promocion = findViewById(R.id.edt_codigo_promocion);


        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(edt_codigo_usuario.getText().toString())) {
                    Toast.makeText(mcontext, "Complete los Campos", Toast.LENGTH_SHORT).show();
                    edt_codigo_usuario.setError("Ingrese Codigo de Usuario");

                    return;
                }

                if (TextUtils.isEmpty(edt_codigo_promocion.getText().toString())) {
                    Toast.makeText(mcontext, "Complete los Campos", Toast.LENGTH_SHORT).show();
                    edt_codigo_promocion.setError("Ingrese codigo de la Promocion");
                    return;
                }

                registrarPedido(idUsuario, idPromocion);

            }
        });


        idUsuario = Integer.parseInt(edt_codigo_usuario.getText().toString());
        idPromocion = Integer.parseInt(edt_codigo_promocion.getText().toString());

    }

    public void registrarPedido(int idUsuario, int idPromocion) {
        String MiToken = MYConstantsPreferences.MAQUIPURAY_AUTHORIZATION + MySharedPreference.getInstance(mcontext).ObtenerToken();

        ApiEndPoint.GET_CLIENT_MAQUIPURAY().registrarPedido(MiToken, idUsuario, idPromocion)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.body() == null) return;
                        if (!response.isSuccessful()) return;

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.body().string());
                            boolean respuesta = jsonObject.getBoolean("respuesta");
                            String mensaje = jsonObject.getString("mensaje");
                            Toast.makeText(Pedido.this, mensaje, Toast.LENGTH_SHORT).show();

                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(Pedido.this, "Error: ", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onFailure: getMessage " + t.getMessage());
                        Log.e(TAG, "onFailure: getCause " + t.getCause());
                    }
                });
    }
}
