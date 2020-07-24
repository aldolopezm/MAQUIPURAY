package com.maquipuray.maquipuray_apk_transporte.data.remote.model;

//import com.maquipuray.maquipuray_apk.data.remote.ApiEndPoint;

import com.maquipuray.maquipuray_apk_transporte.data.remote.ApiEndPoint;

/**
 * Created by  on 26/06/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class CategoriaResponse {

    private int idCategoria;
    private String slug;
    private String nombre;
    private String descripcion;
    private String imagen;
    private String fechaRegistro;
    private int estado;

    public CategoriaResponse() {
    }

    public CategoriaResponse(int idCategoria, String slug, String nombre, String descripcion, String imagen, String fechaRegistro, int estado) {
        this.idCategoria = idCategoria;
        this.slug = slug;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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
        return ApiEndPoint.API_MAQUIPURAY_URL_ASSETS+imagen;
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

    public int isEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "CategoriaResponse{" +
                "idCategoria=" + idCategoria +
                ", slug='" + slug + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                ", estado=" + estado +
                '}';
    }
}
