package com.maquipuray.maquipuray_apk_transporte.data.remote.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rogergcc on 7/08/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class ConductorFData {


    @SerializedName("telefono")
    private String telefono;
    @SerializedName("situacion")
    private String situacion;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("longitud")
    private String longitud;
    @SerializedName("login")
    private String login;
    @SerializedName("latitud")
    private String latitud;
    @SerializedName("key")
    private String key;
    @SerializedName("idTipoUsuario")
    private String idTipoUsuario;
    @SerializedName("fechaRegistro")
    private String fechaRegistro;
    @SerializedName("email")
    private String email;
    @SerializedName("codigo")
    private String codigo;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(String idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
