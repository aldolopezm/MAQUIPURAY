package com.maquipuray.maquipuray_apk.ui.medioentrega;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.data.preferences.MYConstantsPreferences;
import com.maquipuray.maquipuray_apk.data.preferences.MySharedPreference;
import com.maquipuray.maquipuray_apk.data.remote.ApiEndPoint;
import com.maquipuray.maquipuray_apk.data.remote.model.PedidoRegistrar;
import com.maquipuray.maquipuray_apk.data.remote.model.PromocionResponse;
import com.maquipuray.maquipuray_apk.ui.main.MainActivity;
import com.maquipuray.maquipuray_apk.ui.mapadireccion.MapsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElegirMedioEntregaActivity extends AppCompatActivity {

    private static final int RC_SELECT_LOCATION = 1;
    Context mcontext;
    int CodigoPromocion = 0;
    CardView cv_direccion_actual;
    EditText edt_direccion_pedido;
    Button btn_registrar_pedido,btn_ver_seguimiento_pedido;
    private String TAG = "Values";
    private TextView tv_direccion_mapa,tv_elegir_direccion_actual;
    private String locationLat = "";
    private String locationLng = "";
    private TextView tv_ubicacion_pedido;
    private DatabaseReference mDatabase;
    private PromocionResponse promocionResponse;
    private String keyPedido;
    int CodUsuario ;

    private static final int REQUEST_CODE = 101;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private String direccion;
    private Geocoder geocoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_medio_entrega);
        mcontext = this;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Intent myIntent = getIntent(); // gets the previously created intent

        CodUsuario = MySharedPreference.getInstance(this).GetCod();

        if (myIntent != null) {
            CodigoPromocion = myIntent.getIntExtra("codPromocion", 0);
            promocionResponse =(PromocionResponse) myIntent.getSerializableExtra("pedido_promocion");
        }

        tv_direccion_mapa = findViewById(R.id.tv_direccion_mapa);
        tv_elegir_direccion_actual = findViewById(R.id.tv_elegir_direccion_actual);

        cv_direccion_actual = findViewById(R.id.cv_direccion_actual);

        tv_ubicacion_pedido = findViewById(R.id.tv_ubicacion_pedido);
        edt_direccion_pedido = findViewById(R.id.edt_direccion_pedido);
        btn_registrar_pedido = findViewById(R.id.btn_registrar_pedido);
        btn_ver_seguimiento_pedido = findViewById(R.id.btn_ver_seguimiento_pedido);


        tv_elegir_direccion_actual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationLat = currentLocation.getLatitude()+"";
                locationLng = currentLocation.getLongitude()+"";
                try {
                    List<Address> addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
                    if (addresses.size() > 0) {
                        Address address = addresses.get(0);

                        direccion = address.getAddressLine(0);

                        edt_direccion_pedido.setText(direccion);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        tv_direccion_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ElegirMedioEntregaActivity.this, MapsActivity.class);
//                startActivity(intent,RC_SELECT_LOCATION);
                startActivityForResult(intent, RC_SELECT_LOCATION);
            }
        });

        btn_registrar_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (locationLat.equals("") || locationLng.equals("")) {
                    Toast.makeText(mcontext, "Eliga una opcion para obtener la ubicacion", Toast.LENGTH_SHORT).show();
                    return;
                }

                EnviarPedido(view);

            }
        });
        btn_ver_seguimiento_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (keyPedido.equals("")) {
                    Toast.makeText(mcontext, "Registre un pedido", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(mcontext, PedidoSeguimientoActivity.class);
                intent.putExtra("pedid_key", keyPedido);
                intent.putExtra("pedid_lat", locationLat);
                intent.putExtra("pedid_lon", locationLng);

                startActivity(intent);

            }
        });
        requestMultiplePermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                }
                break;
        }

    }


    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    //Toast.makeText(getApplicationContext(), currentLocation.getLatitude() + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();

                    geocoder = new Geocoder(ElegirMedioEntregaActivity.this);
                }
            }
        });
    }

    private void openSettingsDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ElegirMedioEntregaActivity.this);
        builder.setTitle("Required Permissions");
        builder.setMessage("This app require permission to use awesome feature. Grant them in app settings.");
        builder.setPositiveButton("Take Me To SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, 101);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }
    private void requestMultiplePermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.ACCESS_COARSE_LOCATION

                )
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {

//                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();

                            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mcontext);
                            fetchLocation();
                            //init();
//                            initSocket();

                        }

                        // check for permanent denial of any permission
                        else if (report.isAnyPermissionPermanentlyDenied()) {
                            // check for permanent denial of any permission show alert dialog
                            // navigating to Settings
                            openSettingsDialog();
                        } else {

                            Toast.makeText(getApplicationContext(), "All permissions are not granted..",
                                    Toast.LENGTH_SHORT).show();
                        }

//                        if (report.isAnyPermissionPermanentlyDenied()) {
//                            // show alert dialog navigating to Settings
//                            openSettingsDialog();
//                        }

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();

    }

    //region REGION PEDIDO
    public void EnviarPedido(View view) {
        final int CodUsuario = MySharedPreference.getInstance(this).GetCod();

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Se registrara el pedido en el Servidor!")
                .setTitle("¿Realizar Pedido?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        registrarPedido(view, CodUsuario, CodigoPromocion);

//                        Toast.makeText(mcontext, "Pedido Registrado", Toast.LENGTH_SHORT).show();
                    }
                })
//                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                })
        ;
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    public void registrarPedido(View view, int idUsuario, int idPromocion) {
        String MiToken = MYConstantsPreferences.MAQUIPURAY_AUTHORIZATION + MySharedPreference.getInstance(this).ObtenerToken();

        PedidoRegistrar pedidoRegistrar = new PedidoRegistrar(
                idPromocion, idUsuario, edt_direccion_pedido.getText().toString().trim(), locationLat, locationLng);

        ApiEndPoint.GET_CLIENT_MAQUIPURAY().solicitarRegistroPedido(MiToken, pedidoRegistrar)
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


//                            Toast.makeText(mcontext, mensaje, Toast.LENGTH_SHORT).show();

                            Snackbar snackbar = Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG);
                            snackbar.setAction("Action", null);
                            View yourSnackBarView = snackbar.getView(); //get your snackbar view

                            TextView textView = yourSnackBarView.findViewById(R.id.snackbar_text); //Get reference of snackbar textview
                            textView.setTextSize(12);
                            textView.setMaxLines(3); // Change your max lines

                            registrarPedidoDB();

                            snackbar.show();

                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(mcontext, "Error: ", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onFailure: getMessage " + t.getMessage());
                        Log.e(TAG, "onFailure: getCause " + t.getCause());
                    }
                });
    }


    public void registrarPedidoDB() {
        String key;

//        final String nombre = MySharedPreference.getInstance(this).ObtenerNombre();

        key = mDatabase.push().getKey();

        keyPedido=key;
        HashMap<String, String> data = new HashMap<>();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());


        data.put("precio", promocionResponse.getPrecio());
//        data.put("usuarioNombre", nombre);
        data.put("fechaRegistro", timeStamp);
//        data.put("codigoPromocion", CodigoPromocion+"");
        data.put("codigoPromocion", promocionResponse.getCodigoPromocion());
        data.put("nombre", promocionResponse.getNombre());

        data.put("estado", "Registrado");

        data.put("imagen", promocionResponse.getImagen());
        data.put("latitud", locationLat);
        data.put("longitud", locationLng);
        data.put("codigoCliente", CodUsuario+"");


        data.put("conductor", "");
        data.put("fechaAsignado", "");

        data.put("direccion", edt_direccion_pedido.getText().toString().trim());

//        data.put("idPedido",key);


        data.put("key",key);

        mDatabase.child("Pedido").child(key).setValue(data);
        Toast.makeText(getApplicationContext(), "Pedido Registrado FBD", Toast.LENGTH_SHORT).show();

        btn_ver_seguimiento_pedido.setEnabled(true);



        Intent intent = new Intent(ElegirMedioEntregaActivity.this, MainActivity.class);
        intent.putExtra("pedido_confirmado", 2);
        startActivity(intent);

//        finish();
    }
    //endregion


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SELECT_LOCATION && resultCode == RESULT_OK) {
//            loadContacts();

            int isDelete = -1;
            String lat = "";
            String lng = "";
            String Direccion = "";

            if (data != null) {
                lat = data.getStringExtra("Lat");
                lng = data.getStringExtra("Long");
                Direccion = data.getStringExtra("Direccion");
                locationLat = lat;
                locationLng = lng;
                edt_direccion_pedido.setText("");

                edt_direccion_pedido.setText(Direccion);

//                tv_ubicacion_pedido.setText("Lat:"+lat +"  Long" +lng);
            }


        }
    }
}