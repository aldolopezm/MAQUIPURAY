package com.maquipuray.maquipuray_apk.ui.menucuenta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.data.preferences.MySharedPreference;
import com.maquipuray.maquipuray_apk.ui.main.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CuentaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CuentaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView tv_usuario, tv_correo, tv_telefono;
    private Button btn_logout;
    private Context mcontext;
    private FragmentActivity mActivity;

    public CuentaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CuentaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CuentaFragment newInstance(String param1, String param2) {
        CuentaFragment fragment = new CuentaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_cuenta, container, false);

        mcontext = this.getContext();
        mActivity = this.getActivity();
        tv_usuario = view.findViewById(R.id.tv_usuario);
        tv_correo = view.findViewById(R.id.tv_correo);
        tv_telefono = view.findViewById(R.id.tv_telefono);
        btn_logout = view.findViewById(R.id.btn_logout);


        String usuario = MySharedPreference.getInstance(mcontext).ObtenerNombre() + ", "+ MySharedPreference.getInstance(mcontext).ObtenerApellido();
        tv_usuario.setText(usuario);
        tv_correo.setText(MySharedPreference.getInstance(mcontext).ObtenerCorreo());
        tv_telefono.setText(MySharedPreference.getInstance(mcontext).ObtenerTelefono());
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySharedPreference.getInstance(mcontext).Logout();

                Intent intent = new Intent(mcontext, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
                mActivity.finish();

            }
        });

        return view;
    }
}