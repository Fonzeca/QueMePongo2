package main.java.quemepongo2.api.responses;

import main.java.quemepongo2.api.openweather.current.CurrentWeather;

public class ClimaActualRs {
	
	private int ciudadId;
	private String ciudadNombre,nombreClima, icono;
	private double temperatura, viento, humedad, latitud, longitud;
	
	
	public ClimaActualRs() {
	}
	
	public ClimaActualRs(CurrentWeather weather) {
		temperatura = weather.getMain().getTemp();
		ciudadId = weather.getId();
		ciudadNombre = weather.getName();
		
		viento = weather.getWind().getSpeed();
		humedad = weather.getMain().getHumidity();
		nombreClima = weather.getWeather().get(0).getMain();
		
		latitud = weather.getCoord().getLat();
		longitud = weather.getCoord().getLon();
		
		icono=weather.getWeather().get(0).getIcon();
	}
	
	
	public int getCiudadId() {
		return ciudadId;
	}
	public void setCiudadId(int ciudadId) {
		this.ciudadId = ciudadId;
	}
	public String getCiudadNombre() {
		return ciudadNombre;
	}
	public void setCiudadNombre(String ciudadNombre) {
		this.ciudadNombre = ciudadNombre;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public double getViento() {
		return viento;
	}

	public void setViento(double wind) {
		this.viento = wind;
	}

	public double getHumedad() {
		return humedad;
	}

	public void setHumedad(double humedad) {
		this.humedad = humedad;
	}

	public String getNombreClima() {
		return nombreClima;
	}

	public void setNombreClima(String nombreClima) {
		this.nombreClima = nombreClima;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}
}
