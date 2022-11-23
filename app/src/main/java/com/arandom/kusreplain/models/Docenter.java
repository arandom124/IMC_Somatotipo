package com.arandom.kusreplain.models;

public class Docenter {

    String id;
    String nombre;
    String correo;
    String numTargeProfe;
    String numExpediente;

    public Docenter() {

    }

    public Docenter(String id, String nombre, String correo, String numTargeProfe, String numExpediente) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.numTargeProfe = numTargeProfe;
        this.numExpediente = numExpediente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumTargeProfe() {
        return numTargeProfe;
    }

    public void setNumTargeProfe(String numTargeProfe) {
        this.numTargeProfe = numTargeProfe;
    }

    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }
}
