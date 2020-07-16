package com.maquipuray.maquipuray_apk.remote;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.maquipuray.maquipuray_apk.R;

/**
 * Created by  on 25/06/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public final class ApiEndPoint {

    public static final String TMDB_CLIENT_KEY = "516..";
    public static final String TMDB_CLIENT_LANGUAGE = "en-US";
    public static final String FCM_AUTORIZATION_KEY = "key=AAAADXD-...";
    public static final String API_MAQUIPURAY_URL_ASSETS = "http://35.184.20.226:2233/assets/img/categoria/";
    private static final String fcmUrl = "https://fcm.googleapis.com/";
    private static final String TestMoviesClient = "http://192.168.0.23/MovieTicketsServer/";
    private static final String TestClient = "http://192.168.0.23/excelAJS/";
    //    private static final String TestRestaurantMysqlClient="http://192.168.0.23/api_restaurantes/"; //fork project
    private static final String TestRestaurantMysqlClient = "http://192.168.0.23/api_locations_restaurant/";
    private static final String API_THEMOVIEDB_ORG = "https://api.themoviedb.org/";
    private static final String API_MAQUIPURAY = "http://35.184.20.226:2233/api/";
    private static final String imagestmdbposter_path = "https://image.tmdb.org/t/p/original/5LrI4GiCSrChgkdskVZiwv643Kg.jpg";
    public static String PHONE_TEXT = "userPhone";


    public static WebServicesAPIService getTestTokensClient() {
        return RetrofitClient.getClient(TestClient).create(WebServicesAPIService.class);
    }

    public static WebServicesAPIService GET_CLIENT_MAQUIPURAY() {
        return RetrofitClient.getGsonClient(API_MAQUIPURAY).create(WebServicesAPIService.class);
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


//    public void setImageGlide(int imageResourceGlide, ImageView imageView, Context mcontext) {
//        if (isValidContextForGlide(mcontext)) {
//            RequestOptions requestOptions = new RequestOptions()
//                    .fitCenter()
////                .placeholder(R.drawable.film_reel)
//                    .centerInside()
////                .error(R.drawable.cinema_filled_error)
////                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .priority(Priority.HIGH)
//                    .dontAnimate()
////                .centerCrop()
//
////                    .transform(new RoundedCorners(80))
//                    .dontTransform();
//            try {
//                Glide.with(mcontext)
//                        .load(imageResourceGlide)
////                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(80)))
//                        .apply(requestOptions)
//                        .into(imageView);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//
//    }

    public static void setImageGlide(String imageNetworkGlide, ImageView imageView, Context mcontext) {
        if (isValidContextForGlide(mcontext)) {
            RequestOptions requestOptions = new RequestOptions()
                    .fitCenter()
                    .placeholder(R.drawable.ic_room_service_black_24dp)
                    .centerInside()
//                .error(R.drawable.cinema_filled_error)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .dontAnimate()
                    .centerCrop()
//                    .override(120,120)
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

}
