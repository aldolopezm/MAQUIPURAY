package com.maquipuray.maquipuray_apk_transporte.data.remote.model;

import com.google.gson.annotations.SerializedName;
import com.maquipuray.maquipuray_apk_transporte.data.remote.ApiEndPoint;

import java.util.Date;

/**
 * Created by rogergcc on 23/07/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class PedidosResponse
{



    @SerializedName("precio")
    private String precio;
    @SerializedName("usuarioNombre")
    private String usuarioNombre;
    @SerializedName("fechaRegistro")
    private String fechaRegistro;
    @SerializedName("codigoPromocion")
    private String codigoPromocion;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("imagen")
    private String imagen;
    @SerializedName("idPedido")
    private int idPedido;

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCodigoPromocion() {
        return codigoPromocion;
    }

    public void setCodigoPromocion(String codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return ApiEndPoint.API_MAQUIPURAY_URL_ASSETS_PROMOCIONES+imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

//    public Date getCreatedOn() {
//        return createdOn;
//    }
//
//    public void setCreatedOn(Date createdOn) {
//        this.createdOn = createdOn;
//    }

//    @Override
//    public int compareTo(PedidosResponse pedido) {
////        return getIdPedido().compareTo(o.getIdPedido());
////        int idPedido=((PedidosResponse)o).getIdPedido();
//        return this.getIdPedido()-pedido.idPedido;
//    }

//    @Override
//    public int compareTo(PedidosResponse u) {
//        if (getIdPedido() == 0 || u.getIdPedido() ==0) {
//            return 0;
//        }
//        return getIdPedido().compareTo(u.getIdPedido());
//    }

    @Override
    public String toString() {
        return "PedidosResponse{" +
                "precio='" + precio + '\'' +
                ", usuarioNombre='" + usuarioNombre + '\'' +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                ", codigoPromocion='" + codigoPromocion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", idPedido=" + idPedido +
                '}';
    }
}
