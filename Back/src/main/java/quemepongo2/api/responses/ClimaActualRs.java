package main.java.quemepongo2.api.responses;

import main.java.quemepongo2.api.openweather.current.CurrentWeather;
import main.java.quemepongo2.api.openweather.current.WindWeather;

public class ClimaActualRs {
	
	private int ciudadId;
	private String ciudadNombre;
	private double temperatura, viento, humedad;
	
	
	public ClimaActualRs() {
	}
	
	public ClimaActualRs(CurrentWeather weather) {
		temperatura = weather.getMain().getTemp();
		ciudadId = weather.getId();
		ciudadNombre = weather.getName();
		viento=weather.getWind().getSpeed();
		humedad=weather.getMain().getHumidity();
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
}