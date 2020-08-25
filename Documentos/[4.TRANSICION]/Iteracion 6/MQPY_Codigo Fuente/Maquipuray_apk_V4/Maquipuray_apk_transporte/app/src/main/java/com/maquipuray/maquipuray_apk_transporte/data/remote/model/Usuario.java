package com.maquipuray.maquipuray_apk_transporte.data.remote.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rogergcc on 25/07/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class Usuario {

    @SerializedName("password")
    private String password;
    @SerializedName("verificado")
    private int verificado;
    @SerializedName("estado")
    private int estado;
    @SerializedName("UltimaFechaSesion")
    private String UltimaFechaSesion;
    @SerializedName("idTipoUsuario")
    private int idTipoUsuario;
    @SerializedName("nombreCompletoPersona")
    private String nombreCompletoPersona;
    @SerializedName("email")
    private String email;

    @SerializedName("fechaRegistro")
    private String fechaRegistro;

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVerificado() {
        return verificado;
    }

    public void setVerificado(int verificado) {
        this.verificado = verificado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getUltimaFechaSesion() {
        return UltimaFechaSesion;
    }

    public void setUltimaFechaSesion(String UltimaFechaSesion) {
        this.UltimaFechaSesion = UltimaFechaSesion;
    }


    public String getNombreCompletoPersona() {
        return nombreCompletoPersona;
    }

    public void setNombreCompletoPersona(String nombreCompletoPersona) {
        this.nombreCompletoPersona = nombreCompletoPersona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
