package com.maquipuray.maquipuray_apk.ui.destacados;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.data.remote.model.PromocionResponse;

import java.util.List;

/**
 * Created by maquipuray on 8/06/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class PromocionesDestacadaAdapter extends RecyclerView.Adapter<PromocionesDestacadaAdapter.ViewHolder> {

    private List<PromocionResponse> promocionResponseList;
    private Context context;
    @NonNull
    @Override
    public PromocionesDestacadaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promocion_destacado, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull PromocionesDestacadaAdapter.ViewHolder holder, int position) {
        final PromocionResponse promocionBind = promocionResponseList.get(position);

        holder.imgv_promocion_destacada.setImageResource(promocionBind.getImageResource());
        holder.tv_promocion_destacada_nombre.setText(promocionBind.getNombre());

    }

    public PromocionesDestacadaAdapter(List<PromocionResponse> promocionResponseList, Context context) {
        this.promocionResponseList = promocionResponseList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return promocionResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgv_promocion_destacada;
        TextView tv_promocion_destacada_nombre;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            imgv_promocion_destacada = itemView.findViewById(R.id.imgv_promocion_destacada);
            tv_promocion_destacada_nombre = itemView.findViewById(R.id.tv_promocion_destacada_nombre);

        }
    }
}
