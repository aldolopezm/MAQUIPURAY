package com.maquipuray.maquipuray_apk_transporte.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maquipuray.maquipuray_apk_transporte.R;
import com.maquipuray.maquipuray_apk_transporte.data.preferences.MYConstantsPreferences;
import com.maquipuray.maquipuray_apk_transporte.data.preferences.MySharedPreference;
import com.maquipuray.maquipuray_apk_transporte.data.remote.ApiEndPoint;
import com.maquipuray.maquipuray_apk_transporte.data.remote.model.ApiResponse;
import com.maquipuray.maquipuray_apk_transporte.data.remote.model.PedidosResponse;
import com.maquipuray.maquipuray_apk_transporte.ui.main.mapa.MapsActivity;
import com.maquipuray.maquipuray_apk_transporte.ui.pedidos.PedidosAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements PedidosAdapter.ClickListener {

    public static final String EXTRA_PEDIDO = "pedido";
//    public static final String EXTRA_POSITION = "position";
//    public static final String EXTRA_DETAIL = "detail";

    private static final String TAG = "MainActivity";
    RecyclerView RvPedidos;
    private Context mcontext;
    private ProgressBar progress_circular;
    private List<PedidosResponse> pedidosResponseList;
    private PedidosAdapter pedidosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView tv_message = findViewById(R.id.tv_message);


        mcontext = this;
        RvPedidos = (findViewById(R.id.rv_lista_pedidos));

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        InitViews();

//        Bundle b = getIntent().getExtras();
//        String message = "MEssage"; // or other values
//        if(b != null)
//            message = b.getString("message");
//
//        tv_message.setText(message);

        ObtenerData();
    }

    private void ObtenerData(){
        pedidosResponseList.clear();
        pedidosAdapter = new PedidosAdapter(pedidosResponseList, mcontext, this);

        RvPedidos.setAdapter(pedidosAdapter);
        RvPedidos.setHasFixedSize(true);

        pedidosAdapter.notifyDataSetChanged();

        progress_circular.setVisibility(View.VISIBLE);

        String MiToken = MYConstantsPreferences.MAQUIPURAY_AUTHORIZATION + MySharedPreference.getInstance(mcontext).ObtenerToken();
        ApiEndPoint.GET_CLIENT_MAQUIPURAY().getPedidos(MiToken)
                .enqueue(new Callback<ApiResponse<PedidosResponse>>() {
                    @Override
                    public void onResponse(@NonNull Call<ApiResponse<PedidosResponse>> call, @NonNull Response<ApiResponse<PedidosResponse>> response) {
                        progress_circular.setVisibility(View.GONE);
                        if (response.body() != null) {
                            Log.e(TAG, "onResponse: " + response.body().getData().toString());

                            SetDataRecyclerview(response.body().getData());

                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse<PedidosResponse>> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure: Fallo la respuesta", t);
                        progress_circular.setVisibility(View.GONE);
                    }
                });
    }
    private void SetDataRecyclerview(List<PedidosResponse> lista) {
        pedidosResponseList.addAll(lista);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Collections.sort(pedidosResponseList, Comparator.comparing(PedidosResponse::getIdPedido));
        }
//        CollectionUtils.filter(lista, o -> ((PedidosResponse) o).getIdPedido() < 30);

        Collections.reverse(pedidosResponseList);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Optional.ofNullable(pedidosResponseList).map(List::stream).orElseGet(Stream::empty);
//        }
        ;
//        Collections.sort(pedidosResponseList,(pedidosResponse, t1) -> t1.getIdPedido());
//        Collections.sort(lista, Comparator.comparing(pedidosResponse -> pedidosResponse.getIdPedido()));
//        Collections.sort(lista,PedidosResponse::compareTo);

        //ExecutorService

        pedidosAdapter = new PedidosAdapter(pedidosResponseList, mcontext, this);
        RvPedidos.setAdapter(pedidosAdapter);
        RvPedidos.setHasFixedSize(true);

        pedidosAdapter.notifyDataSetChanged();
//        recyclerViewCards.setClipToPadding(true);


    }

    private void InitViews() {
        pedidosResponseList = new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext);
        //        recyclerViewCards.addItemDecoration(itemDecoration);

        progress_circular = findViewById(R.id.progress_circular);

        progress_circular.setVisibility(View.VISIBLE);
        RvPedidos.setLayoutManager(linearLayoutManager);
        RvPedidos.setHasFixedSize(true);


    }

    @Override
    public void onItemClick(int PositionHolder, PedidosResponse item) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra(EXTRA_PEDIDO, (Serializable) item);
//        intent.putExtra(EXTRA_POSITION, position);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pedidos, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_action_reload:
                // do whatever
                Toast.makeText(mcontext, "Reload", Toast.LENGTH_SHORT).show();
                ObtenerData();

                break;
            default:

                break;
        }



        return super.onOptionsItemSelected(item);
    }

}
