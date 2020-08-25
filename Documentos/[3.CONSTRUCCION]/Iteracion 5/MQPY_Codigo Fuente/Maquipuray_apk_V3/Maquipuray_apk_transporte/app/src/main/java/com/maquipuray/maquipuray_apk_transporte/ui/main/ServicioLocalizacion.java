package com.maquipuray.maquipuray_apk_transporte.ui.main;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.maquipuray.maquipuray_apk_transporte.R;
import com.maquipuray.maquipuray_apk_transporte.data.preferences.MySharedPreference;

import java.util.HashMap;


/**
 * Created by ROGERGCC on 5/02/2020.
 */
public class ServicioLocalizacion extends Service {
    public static final String NOTIFICATION_CHANNEL_ID = "1000";
    public static final String NOTIFICATION_CHANNEL_NAME = "localizacion";
    private FusedLocationProviderClient mFusedLocationClient;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

//    private Socket mSocket;

    private DatabaseReference mDatabase;

    @Override
    public void onCreate() {

//        mSocket = App.getSocket();
//        if(!mSocket.connected())
//            mSocket.connect();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000); // 10 seconds
        locationRequest.setFastestInterval(5 * 1000); // 5 seconds
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {


                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        wayLatitude = location.getLatitude();
                        wayLongitude = location.getLongitude();

//                        Log.i("milocalizacion", "lat:" + wayLatitude + " lon:" + wayLongitude);

                        ActualizarUbicationDB(wayLatitude + "", wayLongitude + "");

                    }
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        wayLatitude = location.getLatitude();
                        wayLongitude = location.getLongitude();
                        Log.e("milocalizacion", "lat:" + wayLatitude + " lon:" + wayLongitude);
                    }
                }
            }
        };


        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    public void ActualizarUbicationDB(String locationLat, String locationLng) {
        String key;

        int codigoUsuario = MySharedPreference.getInstance(this).GetCod();
//        key = mDatabase.push().getKey();
        key = codigoUsuario + "";

        HashMap<String, String> data = new HashMap<>();

//        data.put("codigo", codigoUsuario+"");
//        data.put("nombre", nombre+", " + apellido);
//
//        data.put("fechaRegistro", timeStamp);
//        data.put("latitud", locationLat);
//        data.put("longitud", locationLng);
//        data.put("telefono", telefono);
//
////        data.put("idPedido",key);
//        data.put("key",key);

//        mDatabase.child("Conductor").child(key).setValue(data);

        mDatabase.child("Conductor").child(key).child("latitud").setValue(locationLat);
        mDatabase.child("Conductor").child(key).child("login").setValue("1");
        mDatabase.child("Conductor").child(key).child("longitud").setValue(locationLng);


//        Toast.makeText(getApplicationContext(), "Pedido Registrado FBD", Toast.LENGTH_SHORT).show();

    }

    //despues q el servicio este creado
    @Override
    public int onStartCommand(Intent intenc, int flags, int idArranque) {

//        Toast.makeText(this,"Servicio localizaciones arrancado ",
//                Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder notific = new NotificationCompat.Builder(this)
                .setContentTitle("Servicio de localizaciones")
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
//                .setDefaults(Notification.DEFAULT_SOUND)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentText("En un servicio")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(
                            NOTIFICATION_CHANNEL_ID,
                            NOTIFICATION_CHANNEL_NAME,
                            NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(notificationChannel);
            notific.setChannelId(NOTIFICATION_CHANNEL_ID);
        }
        startForeground(101,notific.build()
        );

        return START_STICKY;

    }
    @Override public IBinder onBind(Intent intencion) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFusedLocationClient.removeLocationUpdates(locationCallback);
    }
}
