package main.java.quemepongo2.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.java.quemepongo2.api.openweather.current.CurrentWeather;
import main.java.quemepongo2.api.responses.ClimaActualRs;
import main.java.quemepongo2.manager.SecurityConfig;
import main.java.quemepongo2.manager.WeatherService;

@RestController
public class WeatherController {
	
	@Autowired
	private WeatherService service;
	
	@Autowired
	private SecurityConfig tokenGenerator;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/Pronostico")
	public ClimaActualRs getWeather(@RequestParam int idCiudad, @RequestParam String token) {
		int userId = tokenGenerator.validarToken(token);
		log.info("API, " + Thread.currentThread().getStackTrace()[1].getMethodName() + ", userId: " + userId);
		
		CurrentWeather weather = service.currentWeatherByCityId(idCiudad);
		ClimaActualRs clima = new ClimaActualRs(weather);
		
		return clima;
	}
	
}
