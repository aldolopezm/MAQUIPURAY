package com.maquipuray.maquipuray_apk.ui.menudestacados;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.data.preferences.MYConstantsPreferences;
import com.maquipuray.maquipuray_apk.data.preferences.MySharedPreference;
import com.maquipuray.maquipuray_apk.data.remote.ApiEndPoint;
import com.maquipuray.maquipuray_apk.data.remote.model.ApiResponse;
import com.maquipuray.maquipuray_apk.data.remote.model.PromocionResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DestacadosFragment extends Fragment {

    RecyclerView rv_lista_promociones_destacadas;
    private TextView tv_protocolo;
    private PromocionesDestacadaAdapter promocionesDestacadaAdapter;

    private Context mcontext;
    private ProgressBar progress_circular;

    private List<PromocionResponse> promocionResponseList;

    private static final String TAG = "NegocioProductosFragmen";
    private int CodCategoria;

    public DestacadosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_destacados, container, false);

        View view = inflater.inflate(R.layout.fragment_destacados, container, false);

        mcontext = this.getContext();

        rv_lista_promociones_destacadas= (view.findViewById(R.id.rv_lista_promociones_destacadas));
        tv_protocolo= (view.findViewById(R.id.tv_protocolo));



        InitViews(view);

        GetPromociones();
        tv_protocolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPdf("https://www.cdc.gov/coronavirus/2019-ncov/downloads/community/COVID-Restaurant-Bar-manager-checklist.pdf");
            }
        });
        return view;
    }

    private void InitViews(View view) {

        rv_lista_promociones_destacadas= (view.findViewById(R.id.rv_lista_promociones_destacadas));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext);
        //        recyclerViewCards.addItemDecoration(itemDecoration);

//        progress_circular = view.findViewById(R.id.progress_circular);

//        progress_circular.setVisibility(View.VISIBLE);




        promocionResponseList = new ArrayList<>();
        promocionResponseList.clear();
    }

    public void openPdf(String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setDataAndType(Uri.parse(url), "application/pdf");

        Intent chooser = Intent.createChooser(browserIntent, getString(R.string.chooser_title));
        chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // optional

        startActivity(chooser);
    }

    public void GetPromociones(){

        String MiToken = MYConstantsPreferences.MAQUIPURAY_AUTHORIZATION + MySharedPreference.getInstance(mcontext).ObtenerToken();
        ApiEndPoint.GET_CLIENT_MAQUIPURAY().getPromocionesXCategoria(MiToken,1)
                .enqueue(new Callback<ApiResponse<PromocionResponse>>() {
                    @Override
                    public void onResponse(@NonNull Call<ApiResponse<PromocionResponse>> call, @NonNull Response<ApiResponse<PromocionResponse>> response) {
//                        progress_circular.setVisibility(View.GONE);
                        if (response.body() != null) {
                            Log.e(TAG, "onResponse: " + response.body().getData().toString());

                            SetDataRecyclerview(response.body().getData());
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse<PromocionResponse>> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure: Fallo la respuesta", t);
//                        progress_circular.setVisibility(View.GONE);
                    }
                });


    }

    private void SetDataRecyclerview(List<PromocionResponse> lista) {



        rv_lista_promociones_destacadas.setLayoutManager(new LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL,false));
        rv_lista_promociones_destacadas.setHasFixedSize(true);

        promocionesDestacadaAdapter = new PromocionesDestacadaAdapter(lista,mcontext);

        rv_lista_promociones_destacadas.setAdapter(promocionesDestacadaAdapter);


    }

}
