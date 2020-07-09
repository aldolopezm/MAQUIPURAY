package com.maquipuray.maquipuray_apk.busquedas;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.maquipuray.maquipuray_apk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedasFragment extends Fragment {


    public BusquedasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_busquedas, container, false);
    }

}
