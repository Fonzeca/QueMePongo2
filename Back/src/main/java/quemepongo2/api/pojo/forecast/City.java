package main.java.quemepongo2.api.pojo.forecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import main.java.quemepongo2.api.pojo.current.CoordWeather;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
	private int id;
	private String name;
	private CoordWeather coord;
	private String country;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CoordWeather getCoord() {
		return coord;
	}
	public void setCoord(CoordWeather coord) {
		this.coord = coord;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	

}
