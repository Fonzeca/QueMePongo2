package main.java.quemepongo2.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.quemepongo2.api.openweather.current.CurrentWeather;
import main.java.quemepongo2.api.responses.SugerenciaRs;
import main.java.quemepongo2.api.thermal.PMV;

@Service
public class SugerenciaService {

	private HashMap<Double, String> sugerencias;
	
	@Autowired
	private WeatherService pronosticoService;
	
	@PostConstruct
	private void init() {
		//TODO: Agregar mas opciones y pasar a BD.
		//TODO: Cargar tambien las preferncias. Ej.: si tiene protector solar que lo use.
		//TODO: Agregar una función que utilice el cloth y una tabla de la medida de cloth que suma cada prenda para realizar un a sugerencia mas precisa.
		sugerencias = new HashMap();
		sugerencias.put(0.1, "Utilizar la mínima cantidad de ropa posible.");
		sugerencias.put(0.2, "Musculosa y shorts / Vestido sin mangas fino");
		sugerencias.put(0.27, "Musculosa y pantalón fino / Vestido sin mangas grueso");
		sugerencias.put(0.3, "Remera corta y pantalón fino / Vestido mangas cortas fino");
		sugerencias.put(0.5, "Remera manga corta y pantalón corto / Vestido mangas largas grueso");
		sugerencias.put(0.7, "Remera simple, sweater fino y pantalón grueso");
		sugerencias.put(0.9, "Remera gruesa, sweater grueso y pantalón grueso");
		//BUFANDA y GORRO
		sugerencias.put(1.3, "Campera abrigada");
		sugerencias.put(1.5, "Traje militar de invierno.");
		
	}
	
	public SugerenciaRs obtenerClo(int ciudadId) {
		CurrentWeather current =  pronosticoService.currentWeatherByCityId(ciudadId);
		
		PMV pmv = new PMV(current.getMain().getTemp(), current.getMain().getHumidity(), current.getWind().getSpeed());
		
		
		SugerenciaRs rs = new SugerenciaRs(pmv.getClo());
		rs.setSugerencia(obtenerRopaDeVestir(pmv.getClo()));
		
		return rs;
	}
	
	private String obtenerRopaDeVestir(double clo) {
		double key = elMasCercano(clo, new ArrayList(sugerencias.keySet()));
		return sugerencias.get(key);
	}
	
	public double elMasCercano(double clo, List<Double> keys) {
		double minDif = Integer.MAX_VALUE;
	    double masCercano = clo;

	    for (double i : keys) {
	        final double dif = Math.abs(clo - i);

	        if (dif < minDif) {
	            minDif = dif;
	            masCercano = i;
	        }
	    }

	    return masCercano;
	}
	
}
