package com.maquipuray.maquipuray_apk_transporte.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.maquipuray.maquipuray_apk_transporte.R;
import com.maquipuray.maquipuray_apk_transporte.data.remote.ApiEndPoint;
import com.maquipuray.maquipuray_apk_transporte.data.remote.model.PedidosResponse;

import java.util.List;

/**
 * Created by rogergcc on 23/07/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.ViewHolder> {
    private static PedidosAdapter.ClickListener clickListener;
    private List<PedidosResponse> promocionResponseList;
    private Context context;
    @NonNull
    @Override
    public PedidosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedidos, parent, false);
        return new ViewHolder(itemView);

    }


    public interface ClickListener {
        void onItemClick(int PositionHolder, PedidosResponse item);
//        void onItemClick(int position, Rela view);
    }
    @Override
    public void onBindViewHolder(@NonNull final PedidosAdapter.ViewHolder holder, final int position) {
        final PedidosResponse promocionBind = promocionResponseList.get(position);

        ApiEndPoint.setImageGlide(promocionBind.getImagen(),holder.imgv_promocion,context);

//        holder.imgv_promocion.setImageResource(promocionBind.getImageResource());

        holder.tv_promocion_nombre.setText(promocionBind.getNombre());

        holder.tv_promocion_codigo.setText(promocionBind.getCodigoPromocion());
        holder.tv_promocion_descuento.setText(promocionBind.getPrecio());

        holder.tv_descripcion.setText(promocionBind.getIdPedido()+"");


        holder.card_negocio_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(position,promocionBind);
//                    clickListener.onItemClick(getAdapterPosition(), imageViewFront, rl_offline_item);
            }
        });
    }

    public PedidosAdapter(List<PedidosResponse> promocionResponseList,
                          Context context,
                          PedidosAdapter.ClickListener cardClickListener) {
        this.promocionResponseList = promocionResponseList;
        this.context = context;
        clickListener = cardClickListener;
    }

    @Override
    public int getItemCount() {
        return promocionResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgv_promocion;
        TextView tv_promocion_nombre;
        TextView tv_descripcion;
        TextView tv_promocion_codigo;
        TextView tv_promocion_descuento;

        CardView card_negocio_producto;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            card_negocio_producto = itemView.findViewById(R.id.card_negocio_producto);
            imgv_promocion = itemView.findViewById(R.id.imgv_promocion);
            tv_promocion_nombre = itemView.findViewById(R.id.tv_promocion_nombre);
            tv_descripcion = itemView.findViewById(R.id.tv_descripcion);
            tv_promocion_codigo = itemView.findViewById(R.id.tv_promocion_codigo);
            tv_promocion_descuento = itemView.findViewById(R.id.tv_promocion_descuento);

        }
    }
}
