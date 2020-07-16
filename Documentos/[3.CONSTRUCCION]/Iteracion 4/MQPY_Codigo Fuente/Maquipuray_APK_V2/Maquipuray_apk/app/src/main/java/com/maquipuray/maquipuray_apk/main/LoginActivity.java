package com.maquipuray.maquipuray_apk.main;

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
import com.maquipuray.maquipuray_apk.MyApp;
import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.helpers.ConnectivityReceiver;
import com.maquipuray.maquipuray_apk.preferences.MySharedPreference;
import com.maquipuray.maquipuray_apk.remote.ApiEndPoint;

import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity

        implements ConnectivityReceiver.ConnectivityReceiverListener,
        View.OnClickListener {

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

        checkConnection();

        isLoginEmail = MySharedPreference.getInstance(mcontext).isLogin();

        emailTil = findViewById(R.id.email_til);
        passwordTil = findViewById(R.id.password_til);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.tv_register);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
//        register.setOnClickListener(this);

//        if (isLoginEmail != null) {
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//            finish();
//        }

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
                    if (checkConnection())
                        autenticar(email.getText().toString(), password.getText().toString());
                }
            case R.id.tv_register:
//                openDialogWinLevels();
//                Intent intent = new Intent(getApplicationContext(), Register.class);
//                startActivity(intent);

                break;

            default:
        }

    }

    //Check internet connectivity code
    // Method to manually check connection status
    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showToast(isConnected);
        return isConnected;
    }

    // Showing the status in Toast
    private void showToast(boolean isConnected) {
        if (!isConnected)
            Toast.makeText(getApplicationContext(), getString(R.string.no_connectivity), Toast.LENGTH_LONG).show();
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

                                MySharedPreference.getInstance(mcontext).GuardarToken(token);
                                MySharedPreference.getInstance(mcontext).GuardarCod(idUsuario);

                                MySharedPreference.getInstance(mcontext).Login("Login");

                                Toast.makeText(LoginActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
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

    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        MyApp.getInstance().setConnectivityListener(this);
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showToast(isConnected);
    }
}
