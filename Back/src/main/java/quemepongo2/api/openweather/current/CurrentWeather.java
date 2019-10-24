package main.java.quemepongo2.api.openweather.current;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather {
	private CoordWeather coord;
	private ArrayList<Weather> weather;
	private String base;
	private MainWeather main;
	private String visibility;
	private WindWeather wind;
	private CloudsWeather clouds;
	private int dt;
	private SysWeather sys;
	private String timezone;
	private int id;
	private String name;
	private int cod;
	
	public CoordWeather getCoord() {
		return coord;
	}
	public ArrayList<Weather> getWeather() {
		return weather;
	}
	public String getBase() {
		return base;
	}
	public MainWeather getMain() {
		return main;
	}
	public String getVisibility() {
		return visibility;
	}
	public WindWeather getWind() {
		return wind;
	}
	public CloudsWeather getClouds() {
		return clouds;
	}
	public int getDt() {
		return dt;
	}
	public SysWeather getSys() {
		return sys;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getCod() {
		return cod;
	}
	public void setCoord(CoordWeather coord) {
		this.coord = coord;
	}
	public void setWeather(ArrayList<Weather> weather) {
		this.weather = weather;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public void setMain(MainWeather main) {
		this.main = main;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public void setWind(WindWeather wind) {
		this.wind = wind;
	}
	public void setClouds(CloudsWeather clouds) {
		this.clouds = clouds;
	}
	public void setDt(int dt) {
		this.dt = dt;
	}
	public void setSys(SysWeather sys) {
		this.sys = sys;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
}