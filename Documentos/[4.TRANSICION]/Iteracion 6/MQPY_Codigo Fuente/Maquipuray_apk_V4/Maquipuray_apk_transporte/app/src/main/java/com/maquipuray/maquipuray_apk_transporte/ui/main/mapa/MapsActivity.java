package com.maquipuray.maquipuray_apk_transporte.ui.main.mapa;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentActivity;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.AvoidType;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.maquipuray.maquipuray_apk_transporte.R;
import com.maquipuray.maquipuray_apk_transporte.data.Utils;
import com.maquipuray.maquipuray_apk_transporte.data.preferences.MySharedPreference;
import com.maquipuray.maquipuray_apk_transporte.data.remote.model.PedidosResponse;
import com.maquipuray.maquipuray_apk_transporte.ui.main.MainActivity;
import com.maquipuray.maquipuray_apk_transporte.ui.main.ServicioLocalizacion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback
        , LocationListener {


    LatLng pos;
    PedidosResponse pedido;
    TextView tv_pedidos_details, tv_location_1, tv_location_2;
    BitmapDescriptor icon, iconDestino;
    int codigoUsuario;
    String nombre;
    String apellido;
    String telefono;
    private GoogleMap mMap;
    private String TAG = "Valuies";
    private DatabaseReference mDatabase;
    private FusedLocationProviderClient mFusedLocationClient;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private Marker mimarker;
    private Button btn_aceptar_pedido,btn_cancelar_pedido;
    private boolean pedidoEnCurso;
    private Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mcontext = this;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        tv_pedidos_details = findViewById(R.id.tv_pedidos_details);
        btn_aceptar_pedido = findViewById(R.id.btn_aceptar_pedido);
        btn_cancelar_pedido = findViewById(R.id.btn_cancelar_pedido);
        tv_location_1 = findViewById(R.id.tv_location_1);
        tv_location_2 = findViewById(R.id.tv_location_2);
        Intent intent = getIntent();
//         icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_taxi);

//        icon = vectorToBitmap(R.drawable.ic_taxi,ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));

        Drawable circleDrawable = getResources().getDrawable(R.drawable.ic_taxi);
        icon = getMarkerIconFromDrawable(circleDrawable);

        Drawable circleDrawableDestino = getResources().getDrawable(R.drawable.ic_location);
        iconDestino = getMarkerIconFromDrawable(circleDrawableDestino);


        pedido = (PedidosResponse) intent.getSerializableExtra(MainActivity.EXTRA_PEDIDO);

        codigoUsuario = MySharedPreference.getInstance(this).GetCod();
        nombre = MySharedPreference.getInstance(this).ObtenerNombre();
        apellido = MySharedPreference.getInstance(this).ObtenerApellido();
        telefono = MySharedPreference.getInstance(this).ObtenerTelefono();

        requestMultiplePermission();
        btn_aceptar_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActualizarConductorOcupado("Ocupado");

                ActualizarLocalizacion();

                AceptarAsignacionPedido();

//                AsginarRutaPedido();


                btn_aceptar_pedido.setEnabled(false);

                btn_cancelar_pedido.setEnabled(true);
                pedidoEnCurso=true;

            }
        });
        btn_cancelar_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFusedLocationClient.removeLocationUpdates(locationCallback);
                pedidoEnCurso=false;

                //Detiene el Servicio de Localizacion (OnDestroy) del ServicioLocalizacion

                stopService(new Intent(MapsActivity.this,
                        ServicioLocalizacion.class));

                ActualizarConductorOcupado("Disponible");
                FinalizarPedidoEstado();

                finish();


            }
        });
    }
    @Override
    public void onResume() {
//        Toast.makeText(this, "REsumen", Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    public void FinalizarPedidoEstado() {
        String key;

        HashMap<String, String> data = new HashMap<>();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());


        mDatabase.child("Pedido").child(pedido.getKey()).child("estado").setValue("Finalizado");
        mDatabase.child("Pedido").child(pedido.getKey()).child("conductor").setValue(codigoUsuario+"");
        mDatabase.child("Pedido").child(pedido.getKey()).child("fechaFinalizacion").setValue(timeStamp);

    }

    public void ActualizarConductorOcupado(String situacion) {
        String key;

        int codigoUsuario = MySharedPreference.getInstance(this).GetCod();
        key = codigoUsuario + "";

        mDatabase.child("Conductor").child(key).child("situacion").setValue(situacion);


//        Toast.makeText(getApplicationContext(), "Pedido Registrado FBD", Toast.LENGTH_SHORT).show();

    }

    private BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    private BitmapDescriptor vectorToBitmap(@DrawableRes int id, @ColorInt int color) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        assert vectorDrawable != null;
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        DrawableCompat.setTint(vectorDrawable, color);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private void ActualizarLocalizacion() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(Utils.TIME_SECOND_INTERVAL * 1000); // x seconds
        locationRequest.setFastestInterval(Utils.TIME_SECOND_FAST_INTERVAL * 1000); // x seconds

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {


                if (locationResult == null) {
                    return;
                }
                if (!pedidoEnCurso) {
                    return;
                }

                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        wayLatitude = location.getLatitude();
                        wayLongitude = location.getLongitude();
                        Log.i("milocalizacion", "lat:" + wayLatitude + " lon:" + wayLongitude);
//                        Toast.makeText(MapsActivity.this, "Cambio 1", Toast.LENGTH_SHORT).show();
                        tv_location_1.setText("lat:" + wayLatitude + " lon:" + wayLongitude);

                        LatLng mapTargetLatLng = new LatLng(wayLatitude, wayLongitude);


                        if (mimarker != null) {
                            mimarker.remove();
                        }

                        mimarker = mMap.addMarker(new MarkerOptions().position(new LatLng(wayLatitude, wayLongitude))
//                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)).title("Aqui estoy"));
                                .icon(icon).title("Aqui estoy"));

                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mapTargetLatLng, 17));

//                        mMap.addMarker(markerOptions);

                        //Ubicacion de Conductor antes
                        ActualizarUbicationDB(wayLatitude + "", wayLongitude + "");

                        if (isMyServiceRunning(ServicioLocalizacion.class)) {
                            //Toast.makeText(mcontext, "Already Runnin an Now Stop", Toast.LENGTH_SHORT).show();
                            stopService(new Intent(MapsActivity.this, ServicioLocalizacion.class));
                        } else {
                            //Toast.makeText(mcontext, "Started", Toast.LENGTH_SHORT).show();
                            //startService(new Intent(MapsActivity.this, ServicioLocalizacion.class));

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                startForegroundService(new Intent(MapsActivity.this, ServicioLocalizacion.class));
                            } else {
                                startService(new Intent(MapsActivity.this,
                                        ServicioLocalizacion.class));
                            }
                        }


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


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

    private boolean isMyServiceRunning(Class<?> ServicioLocalizacion) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (ServicioLocalizacion.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    public void AsginarRutaPedido() {
        //                        -18.003434703128445,-70.23815218359232;
        GoogleDirection.withServerKey(getString(R.string.google_maps_key))
                .from(new LatLng(wayLatitude, wayLongitude))
                .to(new LatLng(Double.parseDouble(pedido.getLatitud()), Double.parseDouble(pedido.getLongitud())))
                .avoid(AvoidType.FERRIES)
                .avoid(AvoidType.HIGHWAYS)
                .transportMode(TransportMode.DRIVING)
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction) {
                        if (direction.isOK()) {
                            // Do something
//                                            Toast.makeText(MapsActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "ok");
                        } else {
                            // Do something
//                                            Toast.makeText(MapsActivity.this, "NOT Ok", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "not ok");
                        }
                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {
                        // Do something
//                                        Toast.makeText(MapsActivity.this, "Fallo", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Fallo");
                    }
                });
    }

    public void ActualizarUbicationDB(String locationLat, String locationLng) {
        String key;


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
        mDatabase.child("Conductor").child(key).child("longitud").setValue(locationLng);


//        Toast.makeText(getApplicationContext(), "Pedido Registrado FBD", Toast.LENGTH_SHORT).show();

    }


    public void AceptarAsignacionPedido() {
        String key;


//        key = mDatabase.push().getKey();
        key = codigoUsuario + "";

        HashMap<String, String> data = new HashMap<>();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());


//        mDatabase.child("Conductor").child(key).setValue(data);

        mDatabase.child("Pedido").child(pedido.getKey()).child("estado").setValue("En curso");
        mDatabase.child("Pedido").child(pedido.getKey()).child("conductor").setValue(codigoUsuario+"");
        mDatabase.child("Pedido").child(pedido.getKey()).child("fechaAsignado").setValue(timeStamp);

//        mDatabase.child("Pedido").child(pedido.getKey()).child("conductorLatLong").setValue(timeStamp);
//        mDatabase.child("Pedido").child(pedido.getKey()).child("conductorNombre").setValue(timeStamp);
//        mDatabase.child("Pedido").child(pedido.getKey()).child("conductorTelefono").setValue(timeStamp);


//        Toast.makeText(getApplicationContext(), "Pedido Registrado FBD", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFusedLocationClient!=null){
        mFusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mFusedLocationClient!=null){
        mFusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }

    private void openSettingsDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
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

    public void init() {
//        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//        btn_pedir_taxi.setEnabled(true);
//        SupportMapFragment mapFragment = (SupportMapFragment)
//                getSupportFragmentManager().findFragmentById(R.id.mapa);

        String detalles_pedido = pedido.getCodigoPromocion() + "\n" + pedido.getNombre();
        tv_pedidos_details.setText(detalles_pedido);
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
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();

                            init();
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

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            openSettingsDialog();
                        }

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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.uber_style));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap = googleMap;


        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);



            btn_aceptar_pedido.setEnabled(true);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(wayLatitude, wayLongitude), 17));

//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(wayLatitude, wayLongitude), 16));


        }
        LatLng mapTargetLatLng = new LatLng(Double.parseDouble(pedido.getLatitud()), Double.parseDouble(pedido.getLongitud()));

        mMap.addMarker(new MarkerOptions().position(mapTargetLatLng)
                .icon(iconDestino).title(pedido.getUsuarioNombre()));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mapTargetLatLng, 17));

//        DatabaseReference ref = mDatabase.child("Tasks");
//        ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
////                getTasks(dataSnapshot);
////                mTasksRecyclerView.setVisibility(View.VISIBLE);
////                mProgressBar.setVisibility(View.INVISIBLE);
//            }
//        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, "Moviendo: Lat" + location.getLatitude() + "\n Lon:" + location.getLongitude(), Toast.LENGTH_SHORT).show();
    }

}