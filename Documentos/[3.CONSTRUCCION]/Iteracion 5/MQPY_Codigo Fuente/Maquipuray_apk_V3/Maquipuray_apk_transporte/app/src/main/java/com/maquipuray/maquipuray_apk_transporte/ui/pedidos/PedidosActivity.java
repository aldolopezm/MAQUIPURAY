package com.maquipuray.maquipuray_apk_transporte.ui.pedidos;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maquipuray.maquipuray_apk_transporte.R;
import com.maquipuray.maquipuray_apk_transporte.data.preferences.MySharedPreference;
import com.maquipuray.maquipuray_apk_transporte.data.remote.model.ConductorFData;
import com.maquipuray.maquipuray_apk_transporte.data.remote.model.PedidosResponse;
import com.maquipuray.maquipuray_apk_transporte.ui.main.LoginActivity;
import com.maquipuray.maquipuray_apk_transporte.ui.main.mapa.MapsActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PedidosActivity extends AppCompatActivity {

    public static final String EXTRA_PEDIDO = "pedido";
    private static final String TAG = "MainActivity";
    SharedPreferences sharedpreferences;
    RecyclerView RvPedidos;
    PedidosAdapter.ClickListener clickListener = null;
    private DatabaseReference mDatabase;
    private Context mcontext;
    private ProgressBar progress_circular;
    private List<PedidosResponse> pedidosResponseList;
    private PedidosAdapter pedidosAdapter;
    private LinearLayout linearLayout;
    private TextView tv_datos_coductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        mcontext = this;
        RvPedidos = (findViewById(R.id.rv_lista_pedidos));

        progress_circular = (findViewById(R.id.progress_circular));

        linearLayout = findViewById(R.id.linear_layout);
        tv_datos_coductor = findViewById(R.id.tv_datos_coductor);
        pedidosResponseList = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference();


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        RvPedidos.setLayoutManager(mLayoutManager);
        RvPedidos.setItemAnimator(new DefaultItemAnimator());

        clickListener = new PedidosAdapter.ClickListener() {
            @Override
            public void onItemClick(int PositionHolder, PedidosResponse item) {
//                Toast.makeText(mcontext,  item.toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PedidosActivity.this, MapsActivity.class);
                intent.putExtra(EXTRA_PEDIDO, (Serializable) item);
//        intent.putExtra(EXTRA_POSITION, position);
                startActivity(intent);

            }
        };

        ObtenerDatosConductor(MySharedPreference.getInstance(mcontext).GetCod()+"");
        //ActualizarLoginUsuarioDB("1");


        getData();

//        if (isMyServiceRunning(ServicioLocalizacion.class)) {
//            Toast.makeText(mcontext, "Already Runnin an Now Stop", Toast.LENGTH_SHORT).show();
//            stopService(new Intent(PedidosActivity.this, ServicioLocalizacion.class));
//        } else {
//            Toast.makeText(mcontext, "Started", Toast.LENGTH_SHORT).show();
//            //startService(new Intent(PedidosActivity.this, ServicioLocalizacion.class));
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                startForegroundService(new Intent(PedidosActivity.this, ServicioLocalizacion.class));
//            } else {
//                startService(new Intent(PedidosActivity.this,
//                        ServicioLocalizacion.class));
//            }
//        }


    }

    public void ObtenerDatosConductor(String conductorKey) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Conductor")
                .child(conductorKey);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ConductorFData conductorFData = dataSnapshot.getValue(ConductorFData.class);
                if (conductorFData == null) return;

                String ConductorDatos = tv_datos_coductor.getText().toString()+conductorFData.getNombre() + "\n"
                        + conductorFData.getEmail() + "\n";

                tv_datos_coductor.setText(ConductorDatos);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void ActualizarLoginUsuarioDB(String login) {
        String key;

        int codigoUsuario = MySharedPreference.getInstance(this).GetCod();
//        key = mDatabase.push().getKey();
        key = codigoUsuario + "";


//        mDatabase.child("Conductor").child(key).setValue(data);

        mDatabase.child("Conductor").child(key).child("login").setValue(login);


//        Toast.makeText(getApplicationContext(), "Pedido Registrado FBD", Toast.LENGTH_SHORT).show();

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

    @Override
    public void onBackPressed() {

        salirApp();
//        super.onBackPressed();
    }

    public void salirApp() {
        final int CodUsuario = MySharedPreference.getInstance(this).GetCod();

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage("Saldras de tu cuenta")
                .setTitle("Cerra Sesion")
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        MySharedPreference.getInstance(mcontext).Logout();

                        //ActualizarLoginUsuarioDB("0");

//                        stopService(new Intent(PedidosActivity.this,
//                                ServicioLocalizacion.class));

                        Intent intent = new Intent(PedidosActivity.this, LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        PedidosActivity.this.startActivity(intent);
                        PedidosActivity.this.finish();


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

    public void getData() {
        pedidosResponseList.clear();
        progress_circular.setVisibility(View.VISIBLE);
        mDatabase.child("Pedido").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pedidosResponseList.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    PedidosResponse pedido = ds.getValue(PedidosResponse.class);

                    if (pedido == null) return;

                    if (pedido.getEstado().equals("Registrado")) {

                        pedidosResponseList.add(pedido);
                    }
                }

                if (pedidosResponseList.size() == 0) {
                    RvPedidos.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);

                } else {
                    RvPedidos.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                }

                progress_circular.setVisibility(View.GONE);

                pedidosAdapter = new PedidosAdapter(pedidosResponseList, mcontext, clickListener);

                RvPedidos.setAdapter(pedidosAdapter);
                RvPedidos.setHasFixedSize(true);

                pedidosAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
//                RvPedidos.hideShimmerAdapter();
//                swipeRefreshLayout.setRefreshing(false);
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

}