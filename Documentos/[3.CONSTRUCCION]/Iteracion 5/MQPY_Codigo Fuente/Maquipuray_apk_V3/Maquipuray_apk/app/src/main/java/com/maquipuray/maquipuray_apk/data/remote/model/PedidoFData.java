package com.maquipuray.maquipuray_apk.data.remote.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rogergcc on 4/08/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public  class PedidoFData {

    @SerializedName("usuarioNombre")
    private String usuarioNombre;
    @SerializedName("precio")
    private String precio;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("longitud")
    private String longitud;
    @SerializedName("latitud")
    private String latitud;
    @SerializedName("key")
    private String key;
    @SerializedName("imagen")
    private String imagen;
    @SerializedName("fechaRegistro")
    private String fechaRegistro;
    @SerializedName("fechaAsignado")
    private String fechaAsignado;
    @SerializedName("estado")
    private String estado;
    @SerializedName("direccion")
    private String direccion;
    @SerializedName("conductor")
    private String conductor;
    @SerializedName("codigoPromocion")
    private String codigoPromocion;

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaAsignado() {
        return fechaAsignado;
    }

    public void setFechaAsignado(String fechaAsignado) {
        this.fechaAsignado = fechaAsignado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getCodigoPromocion() {
        return codigoPromocion;
    }

    public void setCodigoPromocion(String codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
    }
}
