package com.maquipuray.maquipuray_apk_transporte.data.remote.model;

/**
 * Created by  on 26/06/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class PedidoRegistrar {
    private int idPromocion;
    private int idUsuario;
    private String direccion;

    private String latitud;
    private String longitud;

    public PedidoRegistrar() {

    }

    public PedidoRegistrar(int idPromocion, int idUsuario, String direccion, String latitud, String longitud) {
        this.idPromocion = idPromocion;
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
