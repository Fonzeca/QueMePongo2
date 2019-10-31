package com.s21;

public class climadehoy {
    private int idCiudad;
    private String ciudadNombre;
    private float temperatura;
    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getCiudadNombre() {
        return ciudadNombre;
    }

    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }


    public climadehoy(int idCiudad, String ciudadNombre, float temperatura) {
        this.idCiudad = idCiudad;
        this.ciudadNombre
                =ciudadNombre;
        this.temperatura=temperatura;
    }
}
