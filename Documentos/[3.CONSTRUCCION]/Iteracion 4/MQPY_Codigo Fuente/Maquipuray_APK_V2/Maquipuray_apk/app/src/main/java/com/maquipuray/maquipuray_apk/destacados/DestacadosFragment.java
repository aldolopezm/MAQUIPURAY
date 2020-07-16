package com.maquipuray.maquipuray_apk.destacados;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.helpers.PromocionesData;

/**
 * A simple {@link Fragment} subclass.
 */
public class DestacadosFragment extends Fragment {

    Context mcontext;
    RecyclerView rv_lista_promociones_destacadas;

    private PromocionesDestacadaAdapter promocionesDestacadaAdapter;
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



        rv_lista_promociones_destacadas.setLayoutManager(new LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL,false));
        rv_lista_promociones_destacadas.setHasFixedSize(true);

        promocionesDestacadaAdapter = new PromocionesDestacadaAdapter(PromocionesData.getPromocionResponsesDemo(),mcontext);

        rv_lista_promociones_destacadas.setAdapter(promocionesDestacadaAdapter);

        return view;
    }

}
