package com.maquipuray.maquipuray_apk.helpers;

import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.data.remote.model.PromocionResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  on 5/07/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class PromocionesData {

    private PromocionesData() {
    }

    public static List<PromocionResponse> promocionResponsesDemo = new ArrayList<>();


    static {
        promocionResponsesDemo.add(new PromocionResponse(1,"Negocio 1", R.drawable.img_destacado_1));
        promocionResponsesDemo.add(new PromocionResponse(2,"Negocio 2", R.drawable.img_destacado_2));
        promocionResponsesDemo.add(new PromocionResponse(3,"Negocio 3", R.drawable.img_destacado_3));
        promocionResponsesDemo.add(new PromocionResponse(4,"Negocio 4", R.drawable.img_destacado_1));
        promocionResponsesDemo.add(new PromocionResponse(5,"Negocio 5", R.drawable.img_destacado_2));
        promocionResponsesDemo.add(new PromocionResponse(6,"Negocio 6", R.drawable.img_destacado_3));
        promocionResponsesDemo.add(new PromocionResponse(7,"Negocio 7", R.drawable.img_destacado_3));
        promocionResponsesDemo.add(new PromocionResponse(8,"Negocio 8", R.drawable.img_destacado_3));
    }

    public static List<PromocionResponse> getPromocionResponsesDemo() {
        return promocionResponsesDemo;
    }

}
