package com.maquipuray.maquipuray_apk_transporte.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maquipuray.maquipuray_apk_transporte.R;
import com.maquipuray.maquipuray_apk_transporte.data.preferences.MySharedPreference;
import com.maquipuray.maquipuray_apk_transporte.data.remote.ApiEndPoint;
import com.maquipuray.maquipuray_apk_transporte.data.remote.model.ConductorFData;
import com.maquipuray.maquipuray_apk_transporte.ui.main.register.RegisterActivity;
import com.maquipuray.maquipuray_apk_transporte.ui.pedidos.PedidosActivity;

import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    TextInputLayout emailTil, passwordTil;
    EditText email, password;
    MaterialButton btn_login;
    TextView register;
    private Context mcontext;
    private String isLoginEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mcontext = this;


        isLoginEmail = MySharedPreference.getInstance(mcontext).isLogin();

        emailTil = findViewById(R.id.email_til);
        passwordTil = findViewById(R.id.password_til);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.tv_register);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        register.setOnClickListener(this);

        if (!isLoginEmail.equals("")) {
            Intent intent = new Intent(getApplicationContext(), PedidosActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_login:
//                openRate();
                emailTil.setError(null);
                passwordTil.setError(null);
                if (email.getText().toString().equals(""))
                    emailTil.setError("Complete Email !");
                else if (password.getText().toString().equals(""))
                    passwordTil.setError("Complete Password ");
                else if (!EmailValidator.getInstance().isValid(email.getText().toString()))
                    emailTil.setError("Email no valido!");
                else {
//                    checkConnection();

                        autenticar(email.getText().toString(), password.getText().toString());
                }
                break;
            case R.id.tv_register:

                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);

                break;

            default:
        }

    }


    // Showing the status in Toast
    private void showToast(boolean isConnected) {
        if (!isConnected)
            Toast.makeText(getApplicationContext(), getString(R.string.no_connectivity), Toast.LENGTH_LONG).show();
    }


    public void VerificarConductorExitencia(String conductorKey,JSONObject data,String token,String mensaje) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Conductor")
                .child(conductorKey);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ConductorFData conductorFData = dataSnapshot.getValue(ConductorFData.class);
                if (conductorFData == null) {
                    Toast.makeText(mcontext, "Datos incorrectos, Conductor no existe", Toast.LENGTH_SHORT).show();
                    return;
                }

//                String ConductorDatos = tv_datos_coductor.getText().toString()+conductorFData.getNombre() + "\n"
//                        + conductorFData.getEmail() + "\n";
//
//                tv_datos_coductor.setText(ConductorDatos);

                JSONObject persona = null;

                String nombre = null;
                String apellido = null;
                String telefono = null;
                String email = null;
                int idUsuario = 0;
                try {
                    idUsuario = data.getInt("idUsuario");
                    persona = data.getJSONObject("persona");
                    nombre = persona.getString("nombre");
                    apellido = persona.getString("apellido");
                    telefono = persona.getString("telefono");
                    email = persona.getString("email");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String[] NombreApellido = conductorFData.getNombre().split("[,]");

                MySharedPreference.getInstance(mcontext).GuardarNombre(NombreApellido[0]);
                MySharedPreference.getInstance(mcontext).GuardarApellido(NombreApellido[1]);
                MySharedPreference.getInstance(mcontext).GuardarTelefono(conductorFData.getTelefono());
                MySharedPreference.getInstance(mcontext).GuardarCorreo(conductorFData.getEmail());

                MySharedPreference.getInstance(mcontext).GuardarToken(token);
                MySharedPreference.getInstance(mcontext).GuardarCod(idUsuario);

                MySharedPreference.getInstance(mcontext).Login("Login");


                Toast.makeText(LoginActivity.this, mensaje, Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                Intent intent = new Intent(LoginActivity.this, PedidosActivity.class);

                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void autenticar(String email, String password) {

        ApiEndPoint.GET_CLIENT_MAQUIPURAY().autenticarUsuario(email, password)
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
                            String token = jsonObject.getString("token");


                            if (respuesta) {
                                JSONObject data = jsonObject.getJSONObject("data");

                                int idUsuario = data.getInt("idUsuario");

                                JSONObject persona = data.getJSONObject("persona");

                                String nombre = persona.getString("nombre");
                                String apellido = persona.getString("apellido");
                                String telefono = persona.getString("telefono");
                                String email = persona.getString("email");

                                VerificarConductorExitencia(idUsuario+"", data,token,mensaje);


                            } else {

                                Toast.makeText(LoginActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
//                        if (response.body().getRespuesta()) {
//                            String MyToken = response.body().getToken();
//
//                            MySharedPreference.getInstance(mcontext).GuardarToken(MyToken);
//
//                            MySharedPreference.getInstance(mcontext).Login("Login");
//
//                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        } else {
//                            Toast.makeText(LoginActivity.this, "Incorrecto", Toast.LENGTH_SHORT).show();
//                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e(TAG, "onFailure: getMessage " + t.getMessage());
                        Log.e(TAG, "onFailure: getCause " + t.getCause());
                    }
                });
    }


}
