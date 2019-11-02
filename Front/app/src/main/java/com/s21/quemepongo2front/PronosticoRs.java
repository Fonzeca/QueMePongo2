
package com.s21.quemepongo2front;

//POJO del response de la API Pronostico
public class PronosticoRs {

    private Integer ciudadId;
    private String ciudadNombre;
    private Float temperatura;
    private Float viento;
    private Float humedad;

    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getCiudadNombre() {
        return ciudadNombre;
    }

    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }

    public Float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Float temperatura) {
        this.temperatura = temperatura;
    }

    public Float getViento() {
        return viento;
    }

    public void setViento(Float viento) {
        this.viento = viento;
    }

    public Float getHumedad() {
        return humedad;
    }

    public void setHumedad(Float humedad) {
        this.humedad = humedad;
    }

}
