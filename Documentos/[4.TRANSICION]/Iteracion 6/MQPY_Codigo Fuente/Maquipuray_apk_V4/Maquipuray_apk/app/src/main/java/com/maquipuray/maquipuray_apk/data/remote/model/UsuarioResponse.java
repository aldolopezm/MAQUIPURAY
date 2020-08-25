package com.maquipuray.maquipuray_apk.data.remote.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rogergcc on 25/07/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public  class UsuarioResponse {


    @SerializedName("data")
    private Data data;
    @SerializedName("token")
    private String token;
    @SerializedName("mensaje")
    private String mensaje;
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
        @SerializedName("persona")
        private Persona persona;
        @SerializedName("idUsuario")
        private int idUsuario;
        @SerializedName("estado")
        private int estado;
        @SerializedName("fechaRegistro")
        private String fechaRegistro;
        @SerializedName("verificado")
        private int verificado;
        @SerializedName("password")
        private String password;
        @SerializedName("email")
        private String email;
        @SerializedName("idTipoUsuario")
        private int idTipoUsuario;
        @SerializedName("idPersona")
        private int idPersona;

        public Persona getPersona() {
            return persona;
        }

        public void setPersona(Persona persona) {
            this.persona = persona;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
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
    }

    public static class Persona {
        @SerializedName("email")
        private String email;
        @SerializedName("idPersona")
        private int idPersona;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getIdPersona() {
            return idPersona;
        }

        public void setIdPersona(int idPersona) {
            this.idPersona = idPersona;
        }
    }
}
