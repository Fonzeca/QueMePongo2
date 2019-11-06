package main.java.quemepongo2.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.quemepongo2.api.openweather.current.CurrentWeather;
import main.java.quemepongo2.api.responses.SugerenciaRs;
import main.java.quemepongo2.api.thermal.PMV;

@Service
public class SugerenciaService {

	@Autowired
	private WeatherService pronosticoService;
	
	public SugerenciaRs obtenerClo(int ciudadId) {
		CurrentWeather current =  pronosticoService.currentWeatherByCityId(ciudadId);
		
		PMV pmv = new PMV(current.getMain().getTemp(), current.getMain().getHumidity(), current.getWind().getSpeed());
		
		SugerenciaRs rs = new SugerenciaRs(pmv.getClo());
		
		return rs;
	}
	
}
