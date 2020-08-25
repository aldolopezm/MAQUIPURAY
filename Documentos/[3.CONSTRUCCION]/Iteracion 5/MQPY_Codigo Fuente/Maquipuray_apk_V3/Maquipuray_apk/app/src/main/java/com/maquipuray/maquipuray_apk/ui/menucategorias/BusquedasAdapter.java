package com.maquipuray.maquipuray_apk.ui.menucategorias;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.card.MaterialCardView;
import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.data.remote.ApiEndPoint;
import com.maquipuray.maquipuray_apk.data.remote.model.CategoriaResponse;

import java.util.List;

/**
 * Created by maquipuray on 8/06/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class BusquedasAdapter extends RecyclerView.Adapter<BusquedasAdapter.ViewHolder> {

    private static ClickListener clickListener;
    private List<CategoriaResponse> categoriaResponseList;
    private Context context;



    public interface ClickListener {
        void onItemClick(int PositionHolder, ImageView view,String categoria,CategoriaResponse categoriaBind);
//        void onItemClick(int position, Rela view);
    }

    public BusquedasAdapter(List<CategoriaResponse> cardCategoriaList,
                                    Context context, ClickListener cardClickListener) {
        this.context = context;
        this.categoriaResponseList = cardCategoriaList;

        clickListener = cardClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        int singleRvCardToUse = R.layout.card_categoria;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(singleRvCardToUse, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final CategoriaResponse categoriaBind = categoriaResponseList.get(position);

        ApiEndPoint.setImageGlide(categoriaBind.getImagen(),holder.img_categoria,context);

        holder.tv_categoria_name.setText(categoriaBind.getNombre());

        holder.mcard_categoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(position, holder.img_categoria,holder.tv_categoria_name.getText().toString(),categoriaBind);
//                    clickListener.onItemClick(getAdapterPosition(), imageViewFront, rl_offline_item);
            }
        });

    }

    public static boolean isValidContextForGlide(final Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            return !activity.isDestroyed() && !activity.isFinishing();
        }
        return true;
    }


    public void setImageGlide(int imageResourceGlide, ImageView imageView, Context mcontext) {
        if (isValidContextForGlide(mcontext)) {
            RequestOptions requestOptions = new RequestOptions()
                    .fitCenter()
//                .placeholder(R.drawable.film_reel)
                    .centerInside()
//                .error(R.drawable.cinema_filled_error)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .dontAnimate()
//                .centerCrop()

//                    .transform(new RoundedCorners(80))
                    .dontTransform();
            try {
                Glide.with(mcontext)
                        .load(imageResourceGlide)
//                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(80)))
                        .apply(requestOptions)
                        .into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public void setImageGlide(String imageNetworkGlide, ImageView imageView, Context mcontext) {
        if (isValidContextForGlide(mcontext)) {
            RequestOptions requestOptions = new RequestOptions()
                    .fitCenter()
//                .placeholder(R.drawable.film_reel)
                    .centerInside()
//                .error(R.drawable.cinema_filled_error)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .dontAnimate()
//                .centerCrop()

//                    .transform(new RoundedCorners(80))
                    .dontTransform();
            try {
                Glide.with(mcontext)
                        .load(imageNetworkGlide)
//                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(80)))
                        .apply(requestOptions)
                        .into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public int getItemCount() {
        return categoriaResponseList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_categoria;
        MaterialCardView mcard_categoria;
        TextView tv_categoria_name;
        public ViewHolder(View itemView) {
            super(itemView);
            img_categoria = itemView.findViewById(R.id.img_categoria);
            tv_categoria_name = itemView.findViewById(R.id.tv_categoria_name);
            mcard_categoria = itemView.findViewById(R.id.mcard_categoria);



        }
    }
}
