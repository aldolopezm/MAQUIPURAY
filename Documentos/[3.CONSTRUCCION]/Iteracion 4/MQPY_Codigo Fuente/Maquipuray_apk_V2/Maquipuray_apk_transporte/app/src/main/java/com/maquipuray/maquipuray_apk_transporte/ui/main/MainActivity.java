package com.maquipuray.maquipuray_apk_transporte.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.maquipuray.maquipuray_apk_transporte.R;
import com.maquipuray.maquipuray_apk_transporte.data.preferences.MYConstantsPreferences;
import com.maquipuray.maquipuray_apk_transporte.data.preferences.MySharedPreference;
import com.maquipuray.maquipuray_apk_transporte.data.remote.ApiEndPoint;
import com.maquipuray.maquipuray_apk_transporte.data.remote.model.ApiResponse;
import com.maquipuray.maquipuray_apk_transporte.data.remote.model.PedidosResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
    implements PedidosAdapter.ClickListener
    {

    private Context mcontext;
    private ProgressBar progress_circular;
    private static final String TAG = "MainActivity";
    private List<PedidosResponse> pedidosResponseList;
    RecyclerView RvPedidos;
    private PedidosAdapter pedidosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView tv_message = findViewById(R.id.tv_message);



        mcontext = this;
        InitViews();

//        Bundle b = getIntent().getExtras();
//        String message = "MEssage"; // or other values
//        if(b != null)
//            message = b.getString("message");
//
//        tv_message.setText(message);

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
        Collections.reverse(pedidosResponseList);
        ;
//        Collections.sort(pedidosResponseList,(pedidosResponse, t1) -> t1.getIdPedido());
//        Collections.sort(lista, Comparator.comparing(pedidosResponse -> pedidosResponse.getIdPedido()));
//        Collections.sort(lista,PedidosResponse::compareTo);


        pedidosAdapter = new PedidosAdapter(pedidosResponseList,mcontext,this);
        RvPedidos.setAdapter(pedidosAdapter);
        RvPedidos.setHasFixedSize(true);

        pedidosAdapter.notifyDataSetChanged();
//        recyclerViewCards.setClipToPadding(true);


    }

    private void InitViews() {
        pedidosResponseList = new ArrayList<>();
        pedidosResponseList.clear();
        RvPedidos = (findViewById(R.id.rv_lista_pedidos));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext);
        //        recyclerViewCards.addItemDecoration(itemDecoration);

        progress_circular = findViewById(R.id.progress_circular);

        progress_circular.setVisibility(View.VISIBLE);
        RvPedidos.setLayoutManager(linearLayoutManager);
        RvPedidos.setHasFixedSize(true);



    }

        @Override
        public void onItemClick(int PositionHolder, PedidosResponse item) {

        }
    }
