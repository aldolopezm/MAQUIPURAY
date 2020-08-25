package com.maquipuray.maquipuray_apk.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by  on 29/06/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class LoginResponse {


    @Expose
    @SerializedName("data")
    private Data data;
    @Expose
    @SerializedName("token")
    private String token;
    @Expose
    @SerializedName("mensaje")
    private String mensaje;
    @Expose
    @SerializedName("respuesta")
    private boolean respuesta;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public static class Data {
        @Expose
        @SerializedName("persona")
        private Persona persona;
        @Expose
        @SerializedName("estado")
        private int estado;
        @Expose
        @SerializedName("fechaRegistro")
        private String fechaRegistro;
        @Expose
        @SerializedName("verificado")
        private int verificado;
        @Expose
        @SerializedName("UltimaFechaSesion")
        private String UltimaFechaSesion;
        @Expose
        @SerializedName("token")
        private String token;
        @Expose
        @SerializedName("password")
        private String password;
        @Expose
        @SerializedName("email")
        private String email;
        @Expose
        @SerializedName("idTipoUsuario")
        private int idTipoUsuario;
        @Expose
        @SerializedName("idPersona")
        private int idPersona;
        @Expose
        @SerializedName("idUsuario")
        private int idUsuario;

        public Persona getPersona() {
            return persona;
        }

        public void setPersona(Persona persona) {
            this.persona = persona;
        }

        public int getEstado() {
            return estado;
        }

        public void setEstado(int estado) {
            this.estado = estado;
        }

        public String getFechaRegistro() {
            return fechaRegistro;
        }

        public void setFechaRegistro(String fechaRegistro) {
            this.fechaRegistro = fechaRegistro;
        }

        public int getVerificado() {
            return verificado;
        }

        public void setVerificado(int verificado) {
            this.verificado = verificado;
        }

        public String getUltimaFechaSesion() {
            return UltimaFechaSesion;
        }

        public void setUltimaFechaSesion(String UltimaFechaSesion) {
            this.UltimaFechaSesion = UltimaFechaSesion;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getIdTipoUsuario() {
            return idTipoUsuario;
        }

        public void setIdTipoUsuario(int idTipoUsuario) {
            this.idTipoUsuario = idTipoUsuario;
        }

        public int getIdPersona() {
            return idPersona;
        }

        public void setIdPersona(int idPersona) {
            this.idPersona = idPersona;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }
    }

    public static class Persona {
        @Expose
        @SerializedName("email")
        private String email;
        @Expose
        @SerializedName("telefono")
        private String telefono;
        @Expose
        @SerializedName("apellido")
        private String apellido;
        @Expose
        @SerializedName("nombre")
        private String nombre;
        @Expose
        @SerializedName("idPersona")
        private int idPersona;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getIdPersona() {
            return idPersona;
        }

        public void setIdPersona(int idPersona) {
            this.idPersona = idPersona;
        }
    }
}
