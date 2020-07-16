package com.maquipuray.maquipuray_apk.remote.model;

import java.util.List;

/**
 * Created by  on 26/06/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class ApiResponse<T> {

//    $data = "";
//    $mensaje ="";
//        try {
//        $data = Categoria::CategoriaListar();
//    } catch (QueryException $ex) {
//        $mensaje = $ex->errorInfo;
//    }
//        return response()->json(['data'=>$data,'mensaje'=>$mensaje]);

//    private <T> T getRandomItem(List<T> list) {
//        Random random = new Random();
//        int listSize = list.size();
//        int randomIndex = random.nextInt(listSize);
//        return list.get(randomIndex);
//    }

    private List<T> data;
    private String mensaje;

    public ApiResponse() {
    }

    public ApiResponse(List<T> data, String mensaje) {
        this.data = data;
        this.mensaje = mensaje;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
