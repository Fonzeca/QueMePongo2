package main.java.quemepongo2.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.quemepongo2.api.SugerenciaController;
import main.java.quemepongo2.api.openweather.current.CurrentWeather;
import main.java.quemepongo2.api.responses.SugerenciaRs;
import main.java.quemepongo2.api.thermal.PMV;

@Service
public class SugerenciaService {

	private HashMap<Double, String> sugerenciasOtro;
	private HashMap<Double, String> sugerenciasHombre;
	private HashMap<Double, String> sugerenciasMujer;
	
	@Autowired
	private WeatherService pronosticoService;
	@PostConstruct
	private void init() {
		//TODO: Agregar mas opciones y pasar a BD.
		//TODO: Cargar tambien las preferncias. Ej.: si tiene protector solar que lo use.
		//TODO: Agregar una función que utilice el cloth y una tabla de la medida de cloth que suma cada prenda para realizar un a sugerencia mas precisa.
		
		sugerenciasHombre = new HashMap();
		sugerenciasMujer = new HashMap();
		sugerenciasOtro = new HashMap();
		
		sugerenciasOtro.put(0.1, "Utilizar la mínima cantidad de ropa posible.");
		sugerenciasOtro.put(0.2, "Musculosa y shorts / Vestido sin mangas fino");
		sugerenciasOtro.put(0.27, "Musculosa y pantalón fino / Vestido sin mangas grueso");
		sugerenciasOtro.put(0.3, "Remera corta y pantalón fino / Vestido mangas cortas fino");
		sugerenciasOtro.put(0.5, "Remera manga corta y pantalón corto / Vestido mangas largas grueso");
		sugerenciasOtro.put(0.7, "Remera simple, sweater fino y pantalón grueso");
		sugerenciasOtro.put(0.9, "Remera gruesa, sweater grueso y pantalón grueso");
		//BUFANDA y GORRO
		sugerenciasOtro.put(1.3, "Campera abrigada");
		sugerenciasOtro.put(1.5, "Traje militar de invierno.");
		
		
		sugerenciasHombre.put(0.1, "Utilizar la mínima cantidad de ropa posible.");
		sugerenciasHombre.put(0.2, "Musculosa y shorts ");
		sugerenciasHombre.put(0.27, "Musculosa y pantalón fino ");
		sugerenciasHombre.put(0.3, "Remera corta y pantalón fino ");
		sugerenciasHombre.put(0.5, "Remera manga corta y pantalón corto ");
		sugerenciasHombre.put(0.7, "Remera simple, sweater fino y pantalón grueso");
		sugerenciasHombre.put(0.9, "Remera gruesa, sweater grueso y pantalón grueso");
		sugerenciasHombre.put(1.0, "Remera gruesa, buzo grueso y pantalón grueso");
		//BUFANDA y GORRO
		sugerenciasHombre.put(1.3, "Campera abrigada");
		sugerenciasHombre.put(1.5, "Traje militar de invierno.");
		
		sugerenciasMujer.put(0.1, "Utilizar la mínima cantidad de ropa posible.");
		sugerenciasMujer.put(0.2, "Vestido sin mangas fino");
		sugerenciasMujer.put(0.27,"Vestido sin mangas grueso");
		sugerenciasMujer.put(0.3, "Musculosa y short ");
		sugerenciasMujer.put(0.5, "Remera manga larga y pantalor de jean");
		sugerenciasMujer.put(0.7, "Remera simple, sweater fino y pantalón grueso");
		sugerenciasMujer.put(0.9, "Remera gruesa, sweater grueso y pantalón grueso");
		//BUFANDA y GORRO
		sugerenciasMujer.put(1.3, "Campera abrigada");
		sugerenciasMujer.put(1.5, "Traje militar de invierno.");
		
	}
	
	public SugerenciaRs obtenerClo(int ciudadId, int genero) {
		
		CurrentWeather current =  pronosticoService.currentWeatherByCityId(ciudadId);
		
		PMV pmv = new PMV(current.getMain().getTemp(), current.getMain().getHumidity(), current.getWind().getSpeed());
		
		
		SugerenciaRs rs = new SugerenciaRs(pmv.getClo());
		rs.setSugerencia(obtenerRopaDeVestir(pmv.getClo(), genero));
		
		return rs;
	}
	
	private String obtenerRopaDeVestir(double clo, int genero) {
		double key = clo;
		switch (genero) {
		case 0:
			key = elMasCercano(clo, new ArrayList(sugerenciasHombre.keySet()));
			return sugerenciasHombre.get(key);
		case 1:
			key = elMasCercano(clo, new ArrayList(sugerenciasMujer.keySet()));
			return sugerenciasMujer.get(key);
		case 2:
			key = elMasCercano(clo, new ArrayList(sugerenciasOtro.keySet()));
			return sugerenciasOtro.get(key);
		}
			
		return null;
		
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
