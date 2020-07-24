package com.maquipuray.maquipuray_apk.data.remote.model;

import com.maquipuray.maquipuray_apk.data.remote.ApiEndPoint;

/**
 * Created by  on 26/06/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class PromocionResponse {
    private int idPromocion;
    private String idNegocio;
    private String codigoPromocion;
    private String slugPromocion;
    private String nombre;
    private String descripcion;
    private String imagen;
    private String precio;
    private String idUsuario;
    private String idCategoria;
    private String slugNegocio;
    private String nombreNegocio;
    private String descripcionNegocio;
    private String direccion;
    private String telefono;
    private String latitud;
    private String longitud;
    private String protocoloSeguridad;
    private String estado;

    private int imageResource;

    public PromocionResponse() {
    }

    public PromocionResponse(int idPromocion, String idNegocio, String codigoPromocion, String slugPromocion, String nombre, String descripcion, String imagen, String precio, String idUsuario, String idCategoria, String slugNegocio, String nombreNegocio, String descripcionNegocio, String direccion, String telefono, String latitud, String longitud, String protocoloSeguridad, String estado) {
        this.idPromocion = idPromocion;
        this.idNegocio = idNegocio;
        this.codigoPromocion = codigoPromocion;
        this.slugPromocion = slugPromocion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
        this.idUsuario = idUsuario;
        this.idCategoria = idCategoria;
        this.slugNegocio = slugNegocio;
        this.nombreNegocio = nombreNegocio;
        this.descripcionNegocio = descripcionNegocio;
        this.direccion = direccion;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
        this.protocoloSeguridad = protocoloSeguridad;
        this.estado = estado;
    }

    public PromocionResponse(int idPromocion,String nombre, int imageResource) {
        this.idPromocion = idPromocion;
        this.nombre = nombre;
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(String idNegocio) {
        this.idNegocio = idNegocio;
    }

    public String getCodigoPromocion() {
        return codigoPromocion;
    }

    public void setCodigoPromocion(String codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
    }

    public String getSlugPromocion() {
        return slugPromocion;
    }

    public void setSlugPromocion(String slugPromocion) {
        this.slugPromocion = slugPromocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return ApiEndPoint.API_MAQUIPURAY_URL_ASSETS_PROMOCIONES+imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getSlugNegocio() {
        return slugNegocio;
    }

    public void setSlugNegocio(String slugNegocio) {
        this.slugNegocio = slugNegocio;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public String getDescripcionNegocio() {
        return descripcionNegocio;
    }

    public void setDescripcionNegocio(String descripcionNegocio) {
        this.descripcionNegocio = descripcionNegocio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getProtocoloSeguridad() {
        return protocoloSeguridad;
    }

    public void setProtocoloSeguridad(String protocoloSeguridad) {
        this.protocoloSeguridad = protocoloSeguridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PromocionResponse{" +
                "idPromocion=" + idPromocion +
                ", idNegocio='" + idNegocio + '\'' +
                ", codigoPromocion='" + codigoPromocion + '\'' +
                ", slugPromocion='" + slugPromocion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", precio='" + precio + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", idCategoria='" + idCategoria + '\'' +
                ", slugNegocio='" + slugNegocio + '\'' +
                ", nombreNegocio='" + nombreNegocio + '\'' +
                ", descripcionNegocio='" + descripcionNegocio + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", protocoloSeguridad='" + protocoloSeguridad + '\'' +
                ", estado='" + estado + '\'' +
                ", imageResource=" + imageResource +
                '}';
    }
}
