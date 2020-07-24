package com.maquipuray.maquipuray_apk.ui.categorias;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.data.preferences.MYConstantsPreferences;
import com.maquipuray.maquipuray_apk.data.preferences.MySharedPreference;
import com.maquipuray.maquipuray_apk.data.remote.ApiEndPoint;
import com.maquipuray.maquipuray_apk.data.remote.model.ApiResponse;
import com.maquipuray.maquipuray_apk.data.remote.model.CategoriaResponse;
import com.maquipuray.maquipuray_apk.ui.negocio.NegocioProductosFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedasFragment extends Fragment

        implements BusquedasAdapter.ClickListener {

    private static final String TAG = "BusquedasFragment";
    private Context mcontext;
    private RecyclerView rv_lista;
    private List<CategoriaResponse> categoriaResponseList;

    private ProgressBar progress_circular;

    public BusquedasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_busquedas, container, false);

        View view = inflater.inflate(R.layout.fragment_categorias, container, false);
        mcontext = this.getContext();


        InitViews(view);


        String MiToken = MYConstantsPreferences.MAQUIPURAY_AUTHORIZATION + MySharedPreference.getInstance(mcontext).ObtenerToken();
        ApiEndPoint.GET_CLIENT_MAQUIPURAY().getCategorias(MiToken)
                .enqueue(new Callback<ApiResponse<CategoriaResponse>>() {
                    @Override
                    public void onResponse(@NonNull Call<ApiResponse<CategoriaResponse>> call, @NonNull Response<ApiResponse<CategoriaResponse>> response) {
                        progress_circular.setVisibility(View.GONE);
                        if (response.body() != null) {
                            Log.e(TAG, "onResponse: " + response.body().getData().toString());

                            SetDataRecyclerview(response.body().getData());
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse<CategoriaResponse>> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure: Fallo la respuesta", t);
                        progress_circular.setVisibility(View.GONE);
                    }
                });
        return view;
    }

    private void InitViews(View view) {
        GridLayoutManager mdefaultGridLayoutManager = new GridLayoutManager(mcontext, 2);
        //        recyclerViewCards.addItemDecoration(itemDecoration);

        rv_lista = view.findViewById(R.id.rv_lista);

        progress_circular = view.findViewById(R.id.progress_circular);

        progress_circular.setVisibility(View.VISIBLE);

        rv_lista.setLayoutManager(mdefaultGridLayoutManager);
        rv_lista.setHasFixedSize(true);

        categoriaResponseList = new ArrayList<>();
        categoriaResponseList.clear();
    }

    private void SetDataRecyclerview(List<CategoriaResponse> categoriaResponseList) {

        BusquedasAdapter busquedasAdapter = new BusquedasAdapter(categoriaResponseList, mcontext, this);
        rv_lista.setAdapter(busquedasAdapter);
        rv_lista.setHasFixedSize(true);
        busquedasAdapter.notifyDataSetChanged();
//        recyclerViewCards.setClipToPadding(true);


    }

    @Override
    public void onItemClick(int position, ImageView view,String categoria, CategoriaResponse categoriaBind) {
//        CategoriaResponse categoriaD = categoriaResponseList.getServiCliente(position);

//        Toast.makeText(mcontext, "Cod Categoria :"+categoriaBind.getIdCategoria()+
//                "\n Nombre"+categoriaBind.getNombre()
//                , Toast.LENGTH_SHORT).show();
//
        addFragment(categoriaBind);
    }

    //    public /* synthetic */ void c(View view) {
    private void addFragment(CategoriaResponse categoriaSelected) {
        NegocioProductosFragment fragment = new NegocioProductosFragment ();

        Bundle args = new Bundle();
        args.putInt("cod", categoriaSelected.getIdCategoria());
        args.putString("title", categoriaSelected.getNombre());
        fragment.setArguments(args);

//        DetalleVentaRealizadaFragment().AcercaDeFragment().AcercaDeFragment((int) R.id.content_main, (CambiarClaveFragment) new BuscarClienteFragment()).AcercaDeFragment((String) null).AnularCompraFragment();
        if (getFragmentManager() != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,
                            fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
