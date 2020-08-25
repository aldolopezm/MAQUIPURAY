package com.maquipuray.maquipuray_apk.ui.register;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.data.remote.ApiEndPoint;
import com.maquipuray.maquipuray_apk.data.remote.model.Usuario;
import com.maquipuray.maquipuray_apk.data.remote.model.UsuarioResponse;

import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

//    ActivityRegisterBinding binding;

    TextInputLayout firstnameTil, lastnameTil, emailTil, passwordTil, retypePasswordTil, contactTil;
    EditText firstname, lastname, email, password, retypePassword, contact_telefono;
    Button signUp;
    TextView signIn;
    private Usuario usuario;
    private DatabaseReference mDatabase;
    private String TAG="taG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


//        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
//        View view = binding.getRoot();
//        setContentView(view);

//        setSupportActionBar(binding.toolbar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        InitView();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        signUp.setOnClickListener(this);
        signIn.setOnClickListener(this);
    }

    public void InitView() {
        firstnameTil = findViewById(R.id.firstname_til);
        lastnameTil = findViewById(R.id.lastname_til);
        emailTil = findViewById(R.id.email_til);
        passwordTil = findViewById(R.id.password_til);
        retypePasswordTil = findViewById(R.id.retype_password_til);
        contactTil = findViewById(R.id.contact_til);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        retypePassword = findViewById(R.id.retype_password);
        contact_telefono = findViewById(R.id.edt_contact_telefono);
        signUp = findViewById(R.id.sign_up);
        signIn = findViewById(R.id.sign_in);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_up) {
            firstnameTil.setError(null);
            lastnameTil.setError(null);
            emailTil.setError(null);
            passwordTil.setError(null);
            retypePasswordTil.setError(null);
            contactTil.setError(null);

            if (firstname.getText().toString().equals(""))
                firstnameTil.setError("Complete su Nombre !");

            else if (lastname.getText().toString().equals(""))
                lastnameTil.setError("Complete su apellido !");

            else if (password.getText().toString().equals(""))
                passwordTil.setError("Complete su Contraseña !");

//            else if (retypePassword.getText().toString().equals(""))
//                retypePasswordTil.setError("Complete su Password !");

            else if (!EmailValidator.getInstance().isValid(email.getText().toString()))
                emailTil.setError("Correo no válido!");

            else if (password.getText().toString().length() < 4)
                passwordTil.setError("Contraseña debe tener al menos 4!");

//            else if (retypePassword.getText().length() < 4)
//                retypePasswordTil.setError("Password must have at least 4 characters!");

//            else if (!retypePassword.getText().toString().equals(password.getText().toString()))
//                retypePasswordTil.setError("Your passwords don't match!");

            else if (contact_telefono.getText().toString().equals(""))
                contactTil.setError("Complete su Nº Celular. !");
//            else if (contact.getText().toString().length() < 10)
//                contactTil.setError("Contact no. is not valid!");
            else {

//                if (checkConnection()) {
                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setPassword(password.getText().toString());

                usuario.setIdTipoUsuario(4);
                usuario.setEstado(0);

                usuario.setNombreCompletoPersona(firstname.getText().toString().trim()+", "+lastname.getText().toString().trim());
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                usuario.setFechaRegistro(timeStamp);

                registerUser();
//                }
            }

        } else if (view.getId() == R.id.sign_in) {
            finish();
        }
    }

    private void registerUser() {
//        $data->idPersona = $request->input('idPersona');
//        $data->idTipoUsuario = 2;
//        $data->email = $request->input('email');
//        $data->password = bcrypt($request->input('password'));
//        $data->verificado = 1;
//
////        2020-07-24 00:08:44
//        $data->fechaRegistro = Carbon::now()->toDateTimeString();
//        $data->estado = 1;

        ApiEndPoint.GET_CLIENT_MAQUIPURAY().registrarUsuario(usuario).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                if (response.body() == null) return;
                if (!response.isSuccessful()) return;

                JSONObject jsonObject ;
                try {
                    jsonObject = new JSONObject(response.body().string());
                    boolean respuesta = jsonObject.getBoolean("respuesta");
                    String mensaje = jsonObject.getString("mensaje");
                    UsuarioResponse.Data usuarioData = null;
                    JSONObject data = null;
                    if (!respuesta) {

                        Toast.makeText(RegisterActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    data = jsonObject.getJSONObject("data");
                    Type type = new TypeToken<UsuarioResponse.Data>() {}.getType();

                    usuarioData = new Gson().fromJson(data.toString(), type);

                    RegistrarCoductorFirebaseDB(mensaje,usuarioData);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }




            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG,"error no registrado");
            }
        });

    }

    public void RegistrarCoductorFirebaseDB(String mensaje, UsuarioResponse.Data usuarioResponse) {
        String key;


//        key = mDatabase.push().getKey();
        key = usuarioResponse.getIdPersona()+"";
        HashMap<String, String> data = new HashMap<>();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

        data.put("codigo", key);
        data.put("idTipoUsuario", usuario.getIdTipoUsuario()+"");
        data.put("login", "1");
        data.put("email", usuarioResponse.getEmail());

        data.put("fechaRegistro", timeStamp);

        data.put("nombre", firstname.getText().toString().trim()+", "+lastname.getText().toString().trim());
        data.put("telefono", contact_telefono.getText().toString().trim());

        data.put("key",key);

        mDatabase.child("Cliente").child(key).setValue(data);

//        Toast.makeText(getApplicationContext(), "Conductor Registrado en BD", Toast.LENGTH_SHORT).show();

        Toast.makeText(RegisterActivity.this, mensaje, Toast.LENGTH_SHORT).show();


    }


}