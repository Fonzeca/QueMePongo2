package main.java.quemepongo2.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.java.quemepongo2.api.openweather.current.CurrentWeather;
import main.java.quemepongo2.api.responses.ClimaActualRs;
import main.java.quemepongo2.manager.WeatherService;

@RestController
public class WeatherController {
	
	@Autowired
	private WeatherService service;
	
	@GetMapping("/Pronostico")
	public ClimaActualRs getWeather(@RequestParam int IdCiudad) {
		CurrentWeather weather = service.currentWeatherByCityId(IdCiudad);
		ClimaActualRs clima = new ClimaActualRs(weather);
		
		return clima;
	}
	
}
