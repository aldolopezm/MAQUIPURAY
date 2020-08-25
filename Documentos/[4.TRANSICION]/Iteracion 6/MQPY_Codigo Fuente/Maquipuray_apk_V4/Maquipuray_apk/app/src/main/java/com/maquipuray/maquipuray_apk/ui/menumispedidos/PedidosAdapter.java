package com.maquipuray.maquipuray_apk.ui.menumispedidos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.data.remote.ApiEndPoint;
import com.maquipuray.maquipuray_apk.data.remote.model.PedidosResponse;

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

        holder.tv_promocion_decuento.setText(promocionBind.getCodigoPromocion());
        holder.tv_promocion_precio.setText("S./ "+promocionBind.getPrecio());

        holder.tv_promocion_fecha_valida.setText("promoción valida hasta\n"+promocionBind.getFechaRegistro());

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
        TextView tv_promocion_fecha_valida;
        TextView tv_promocion_decuento;
        TextView tv_promocion_precio;

        CardView card_negocio_producto;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            card_negocio_producto = itemView.findViewById(R.id.card_negocio_producto);
            imgv_promocion = itemView.findViewById(R.id.imgv_promocion);
            tv_promocion_nombre = itemView.findViewById(R.id.tv_promocion_nombre);
            tv_promocion_fecha_valida = itemView.findViewById(R.id.tv_promocion_fecha_valida);
            tv_promocion_decuento = itemView.findViewById(R.id.tv_promocion_decuento);
            tv_promocion_precio = itemView.findViewById(R.id.tv_promocion_precio);

        }
    }
}
