package com.maquipuray.maquipuray_apk.ui.medioentrega;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.data.remote.model.ConductorFData;
import com.maquipuray.maquipuray_apk.data.remote.model.PedidoFData;

public class PedidoSeguimientoActivity extends FragmentActivity implements OnMapReadyCallback {

    BitmapDescriptor icon, iconDestino;
    private GoogleMap mMap;
    private String pedid_key,pedid_lat,pedid_lon;
    private DatabaseReference mDatabase;
    private String TAG = "VAL";
    private String KEY_CONDUCTOR = "";
    private TextView tv_conductor_datos, tv_pedido_asignado;
    private Marker mimarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_seguimiento);

        tv_conductor_datos = findViewById(R.id.tv_conductor_datos);
        tv_pedido_asignado = findViewById(R.id.tv_pedido_asignado);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent myIntent = getIntent(); // gets the previously created intent

        Drawable circleDrawable = getResources().getDrawable(R.drawable.ic_taxi);
        icon = getMarkerIconFromDrawable(circleDrawable);

        Drawable circleDrawableDestino = getResources().getDrawable(R.drawable.ic_location);
        iconDestino = getMarkerIconFromDrawable(circleDrawableDestino);

        if (myIntent != null) {
            pedid_key = myIntent.getStringExtra("pedid_key");
            pedid_lat = myIntent.getStringExtra("pedid_lat");
            pedid_lon = myIntent.getStringExtra("pedid_lon");

            ObtenerPedido(pedid_key);

        }


//        ObtenerPedido(pedid_key);

//        Toast.makeText(this, pedid_key, Toast.LENGTH_SHORT).show();


    }

    private BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
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

        // Add a marker in Sydney and move the camera

//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng mapTargetLatLng = new LatLng(Double.parseDouble(pedid_lat), Double.parseDouble(pedid_lon));

        mMap.addMarker(new MarkerOptions().position(mapTargetLatLng)
                .icon(iconDestino).title("Mi destino"));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(mapTargetLatLng));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mapTargetLatLng, 18));
    }

    public void ObtenerConductorId(String pedid_key) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Pedido")
                .child(pedid_key);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                PedidoFData pedido = dataSnapshot.getValue(PedidoFData.class);
                if (pedido == null) return;

                if (pedido.getEstado().equals("En curso")) {
                    tv_pedido_asignado.setText(pedido.getEstado());
                    MostrarConductor(pedido.getConductor());
                }

//                tv_conductor_datos.setText(pedido.getConductor());
//                MostrarConductor(pedido.getConductor());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void ObtenerPedido(String pedid_key) {
        String key;
//        final int CodUsuario = MySharedPreference.getInstance(this).GetCod();
//        final String nombre = MySharedPreference.getInstance(this).ObtenerNombre();


        mDatabase.child("Pedido").child(pedid_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                PedidoFData pedido = snapshot.getValue(PedidoFData.class);

                //System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
//                Log.e( TAG, String.valueOf(snapshot.getValue()) );

//                String latitud= snapshot.getValue(String.class);
                if (pedido == null) return;

                if (pedido.getEstado().equals("En curso")) {
                    tv_pedido_asignado.setText(pedido.getEstado());
                    ObtenerConductorId(pedid_key);
                }

//                Toast.makeText(PedidoSeguimientoActivity.this, String.valueOf(snapshot.getValue()), Toast.LENGTH_SHORT).show();

//                tv_conductor_datos.setText(snapshot.getKey()  + " - " + latitud);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void MostrarConductor(String conductorId) {
        mDatabase.child("Conductor").child(conductorId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ConductorFData conductorFData = snapshot.getValue(ConductorFData.class);

                if (conductorFData == null) return;
                String datosConductor = "Conductor: " + conductorFData.getNombre() + "\n"
                        + "Telefono: " + conductorFData.getTelefono();


                tv_conductor_datos.setText(datosConductor);
                if (mimarker != null) {
                    mimarker.remove();
                }

                LatLng mapTargetLatLng = new LatLng(Double.parseDouble(conductorFData.getLatitud()), Double.parseDouble(conductorFData.getLongitud()));

                mimarker = mMap.addMarker(new MarkerOptions().position(mapTargetLatLng)
                        .icon(icon).title("Conductor"));

//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mapTargetLatLng, 16));

//                Toast.makeText(PedidoSeguimientoActivity.this, conductorFData.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}


