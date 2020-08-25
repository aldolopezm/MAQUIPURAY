package com.maquipuray.maquipuray_apk.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Persona {
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
