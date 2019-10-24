package main.java.quemepongo2.api.openweather.forecast;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {

	private int cod;
	private Double message;
	private int cnt;
	private ArrayList<List> list;
	private City city;
	private int[] indice;
	
	private ArrayList<String> valoresSeteadosPorMi;
	private ArrayList<List> temperaturaDias;
	
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public Double getMessage() {
		return message;
	}
	public void setMessage(Double message) {
		this.message = message;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public ArrayList<String> getValoresSeteadosPorMi() {
		return valoresSeteadosPorMi;
	}
	public void setValoresSeteadosPorMi(ArrayList<String> valoresSeteadosPorMi) {
		this.valoresSeteadosPorMi = valoresSeteadosPorMi;
	}
	public ArrayList<List> getList() {
		return list;
	}
	public void setList(ArrayList<List> list) {
		this.list = list;
	}
	public ArrayList<List> getTemperaturaDias() {
		return temperaturaDias;
	}
	public void setTemperaturaDias(ArrayList<List> temperaturaDias) {
		this.temperaturaDias = temperaturaDias;
	}
	public double temperaturaDiaUno() {
		return this.temperaturaDias.get(0).getMain().getTemp();
	}
	public double temperaturaDiaDos() {
		return this.temperaturaDias.get(1).getMain().getTemp();
	}
	public double temperaturaDiaTres() {
		return this.temperaturaDias.get(2).getMain().getTemp();
	}
	public String nombreDiaUno() {
		return getValoresSeteadosPorMi().get(0);
	}
	public String nombreDiaDos() {
		return getValoresSeteadosPorMi().get(1);
	}
	public String nombreDiaTres() {
		return getValoresSeteadosPorMi().get(2);
	}
	public int getIconoUno() {
		return list.get(indice[0]).getWeather().get(0).getId();
	}
	public int getIconoDos() {
		return list.get(indice[1]).getWeather().get(0).getId();
	}
	public int getIconoTres() {
		return list.get(indice[2]).getWeather().get(0).getId();
	}
	public void setIndice(int[] indice) {
		this.indice = indice;
	}
	
	
}
