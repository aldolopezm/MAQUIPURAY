package com.maquipuray.maquipuray_apk.ui.menumispedidos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.data.preferences.MySharedPreference;
import com.maquipuray.maquipuray_apk.data.remote.model.PedidosResponse;
import com.maquipuray.maquipuray_apk.ui.medioentrega.PedidoSeguimientoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisPedidosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisPedidosFragment extends Fragment {

    public static final String EXTRA_PEDIDO = "pedido";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MainActivity";
    SharedPreferences sharedpreferences;
    RecyclerView RvPedidos;
    PedidosAdapter.ClickListener clickListener = null;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatabaseReference mDatabase;
    private Context mcontext;
    private ProgressBar progress_circular;
    private List<PedidosResponse> pedidosResponseList;
    private PedidosAdapter pedidosAdapter;
    private LinearLayout linearLayout;
    private int CodUsuario;


    public MisPedidosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MisPedidosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MisPedidosFragment newInstance(String param1, String param2) {
        MisPedidosFragment fragment = new MisPedidosFragment();
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


        View view = inflater.inflate(R.layout.fragment_mis_pedidos, container, false);

        mcontext = this.getContext();

        CodUsuario = MySharedPreference.getInstance(mcontext).GetCod();
        RvPedidos = (view.findViewById(R.id.rv_lista_pedidos));

        progress_circular = (view.findViewById(R.id.progress_circular));

        linearLayout = view.findViewById(R.id.linear_layout);
        pedidosResponseList = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference();


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mcontext);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        RvPedidos.setLayoutManager(mLayoutManager);
        RvPedidos.setItemAnimator(new DefaultItemAnimator());

        clickListener = new PedidosAdapter.ClickListener() {
            @Override
            public void onItemClick(int PositionHolder, PedidosResponse item) {
//                Toast.makeText(mcontext,  item.toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mcontext, PedidoSeguimientoActivity.class);
                intent.putExtra("pedid_key", item.getKey());
                intent.putExtra("pedid_lat", item.getLatitud());
                intent.putExtra("pedid_lon", item.getLongitud());

                startActivity(intent);

//                Intent intent = new Intent(mcontext, MapsActivity.class);
//                intent.putExtra(EXTRA_PEDIDO, (Serializable) item);
//
//                startActivity(intent);

            }
        };


        getData();
        return view;

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

                    if (pedido.getCodigoCliente().equals(CodUsuario+"") &&
                        (pedido.getEstado().equals("Registrado")  || pedido.getEstado().equals("En curso") )) {
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