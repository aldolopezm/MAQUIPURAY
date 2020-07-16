package com.maquipuray.maquipuray_apk.negocio;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.preferences.MYConstantsPreferences;
import com.maquipuray.maquipuray_apk.preferences.MySharedPreference;
import com.maquipuray.maquipuray_apk.remote.ApiEndPoint;
import com.maquipuray.maquipuray_apk.remote.model.ApiResponse;
import com.maquipuray.maquipuray_apk.remote.model.PromocionResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NegocioProductosFragment extends Fragment implements NegocioPromocionesProductosAdapter.ClickListener{


    CardView card_negocio_producto;
    private Context mcontext;
    RecyclerView rv_lista_promociones;
    private NegocioPromocionesProductosAdapter negocioPromocionesProductosAdapter;
    private ProgressBar progress_circular;

    private List<PromocionResponse> promocionResponseList;

    private static final String TAG = "NegocioProductosFragmen";
    private int CodCategoria;

    public NegocioProductosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_negocio_productos, container, false);
        mcontext = this.getContext();
        TextView tv_title_categoria= view.findViewById(R.id.tv_title_categoria);
        //card_negocio_producto= view.findViewById(R.id.card_negocio_producto);

        if (getArguments() != null) {
            CodCategoria = getArguments().getInt("cod");
            String title_f = getArguments().getString("title");

            tv_title_categoria.setText(title_f);
        }

        mcontext = this.getContext();
        InitViews(view);



        String MiToken = MYConstantsPreferences.MAQUIPURAY_AUTHORIZATION + MySharedPreference.getInstance(mcontext).ObtenerToken();
        ApiEndPoint.GET_CLIENT_MAQUIPURAY().getPromocionesXCategoria(MiToken,CodCategoria)
                .enqueue(new Callback<ApiResponse<PromocionResponse>>() {
                    @Override
                    public void onResponse(@NonNull Call<ApiResponse<PromocionResponse>> call, @NonNull Response<ApiResponse<PromocionResponse>> response) {
                        progress_circular.setVisibility(View.GONE);
                        if (response.body() != null) {
                            Log.e(TAG, "onResponse: " + response.body().getData().toString());

                            SetDataRecyclerview(response.body().getData());
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse<PromocionResponse>> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure: Fallo la respuesta", t);
                        progress_circular.setVisibility(View.GONE);
                    }
                });



        return view;
    }

    private void SetDataRecyclerview(List<PromocionResponse> lista) {

        negocioPromocionesProductosAdapter = new NegocioPromocionesProductosAdapter(lista,mcontext,this);
        rv_lista_promociones.setAdapter(negocioPromocionesProductosAdapter);
        rv_lista_promociones.setHasFixedSize(true);

        negocioPromocionesProductosAdapter.notifyDataSetChanged();
//        recyclerViewCards.setClipToPadding(true);


    }
    private void InitViews(View view) {

        rv_lista_promociones= (view.findViewById(R.id.rv_lista_promociones));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext);
        //        recyclerViewCards.addItemDecoration(itemDecoration);

        progress_circular = view.findViewById(R.id.progress_circular);

        progress_circular.setVisibility(View.VISIBLE);
        rv_lista_promociones.setLayoutManager(linearLayoutManager);
        rv_lista_promociones.setHasFixedSize(true);


        promocionResponseList = new ArrayList<>();
        promocionResponseList.clear();
    }

    @Override
    public void onItemClick(int PositionHolder, final PromocionResponse item) {
        final int CodUsuario = MySharedPreference.getInstance(mcontext).GetCod();
//        Toast.makeText(mcontext
//                , "Codigo usuario: " +MySharedPreference.getInstance(mcontext).GetCod() +"\nPromocion: "+item.getNombre()
//                , Toast.LENGTH_SHORT).show();
        final AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);


        builder.setMessage("Se registrara el pedido en el Servidor!")
                .setTitle("¿Realizar Pedido?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        registrarPedido(CodUsuario,item.getIdPromocion());

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

    public void registrarPedido(int idUsuario, int idPromocion) {
        String MiToken = MYConstantsPreferences.MAQUIPURAY_AUTHORIZATION + MySharedPreference.getInstance(mcontext).ObtenerToken();

        ApiEndPoint.GET_CLIENT_MAQUIPURAY().registrarPedido(MiToken, idUsuario, idPromocion)
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

//                            String token = jsonObject.getString("token");

                            Toast.makeText(mcontext, mensaje, Toast.LENGTH_SHORT).show();
//                            if (respuesta) {
//                                JSONObject data = jsonObject.getJSONObject("data");
//                                MySharedPreference.getInstance(mcontext).GuardarToken(token);
//
////                                MySharedPreference.getInstance(mcontext).Login("Login");
//                                Toast.makeText(LoginActivity.this, mensaje, Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(Pedido.this, MainActivity.class));
//                            } else {
//
//                                Toast.makeText(mcontext, mensaje, Toast.LENGTH_SHORT).show();
//                            }

                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
//                        Toast.makeText(mcontext, "Pedido Confirmado: " + response.body().toString(), Toast.LENGTH_SHORT).show();

//                        Snackbar.make(view, "Pedido Confirmado", Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(mcontext, "Error: ", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onFailure: getMessage " + t.getMessage());
                        Log.e(TAG, "onFailure: getCause " + t.getCause());
                    }
                });
    }
}
