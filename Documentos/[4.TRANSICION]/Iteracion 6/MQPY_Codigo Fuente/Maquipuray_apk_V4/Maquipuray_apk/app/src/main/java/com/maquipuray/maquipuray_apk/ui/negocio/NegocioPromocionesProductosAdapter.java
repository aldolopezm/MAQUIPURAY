package com.maquipuray.maquipuray_apk.ui.negocio;

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
import com.maquipuray.maquipuray_apk.data.remote.model.PromocionResponse;

import java.util.List;

/**
 * Created by maquipuray on 8/06/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class NegocioPromocionesProductosAdapter extends RecyclerView.Adapter<NegocioPromocionesProductosAdapter.ViewHolder> {
    private static NegocioPromocionesProductosAdapter.ClickListener clickListener;
    private List<PromocionResponse> promocionResponseList;
    private Context context;
    @NonNull
    @Override
    public NegocioPromocionesProductosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promocion_producto, parent, false);
        return new ViewHolder(itemView);

    }


    public interface ClickListener {
        void onItemClick(int PositionHolder, PromocionResponse item);
//        void onItemClick(int position, Rela view);
    }
    @Override
    public void onBindViewHolder(@NonNull final NegocioPromocionesProductosAdapter.ViewHolder holder, final int position) {
        final PromocionResponse promocionBind = promocionResponseList.get(position);

        ApiEndPoint.setImageGlide(promocionBind.getImagen(),holder.imgv_promocion,context);

//        holder.imgv_promocion.setImageResource(promocionBind.getImageResource());

        holder.tv_promocion_nombre.setText(promocionBind.getNombre());

        holder.tv_promocion_codigo.setText(promocionBind.getCodigoPromocion());
        holder.tv_promocion_precio.setText("S./ "+promocionBind.getPrecio());
        holder.tv_promocion_fecha_valida.setText("promoción valida hasta\n"+promocionBind.getFechaValidaPromocion());
//        holder.tv_promocion_fecha_valida.setText(promocionBind.getDescripcion());


        holder.card_negocio_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(position,promocionBind);
//                    clickListener.onItemClick(getAdapterPosition(), imageViewFront, rl_offline_item);
            }
        });
    }

    public NegocioPromocionesProductosAdapter(List<PromocionResponse> promocionResponseList,
                                              Context context,
                                              NegocioPromocionesProductosAdapter.ClickListener cardClickListener) {
        this.promocionResponseList = promocionResponseList;
        this.context = context;
        clickListener = cardClickListener;
    }

    @Override
    public int getItemCount() {
        return promocionResponseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgv_promocion;
        TextView tv_promocion_nombre;
        TextView tv_promocion_fecha_valida;
        TextView tv_promocion_codigo;
        TextView tv_promocion_precio;

        CardView card_negocio_producto;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            card_negocio_producto = itemView.findViewById(R.id.card_negocio_producto);
            imgv_promocion = itemView.findViewById(R.id.imgv_promocion);
            tv_promocion_nombre = itemView.findViewById(R.id.tv_promocion_nombre);
            tv_promocion_fecha_valida = itemView.findViewById(R.id.tv_promocion_fecha_valida);
//            tv_descripcion = itemView.findViewById(R.id.tv_promocion_fecha_valida);
            tv_promocion_codigo = itemView.findViewById(R.id.tv_promocion_decuento);
            tv_promocion_precio = itemView.findViewById(R.id.tv_promocion_precio);

        }
    }
}
