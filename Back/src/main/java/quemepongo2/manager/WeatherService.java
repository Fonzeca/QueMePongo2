package main.java.quemepongo2.manager;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import main.java.quemepongo2.api.openweather.current.CurrentWeather;

@Component
public class WeatherService {
	
	public static String APIKEY = "b5e958929bfb6e6def01059adeeaf345";
	
	public CurrentWeather currentWeatherByCityId(int cityId) {
		String query = "http://api.openweathermap.org/data/2.5/weather?id=" + cityId + "&units=metric" + "&apikey=" + APIKEY;
		
		
		RestTemplate rest = new RestTemplate();
		CurrentWeather current = rest.getForObject(query, CurrentWeather.class);
		
		return current;
	}
	
}
