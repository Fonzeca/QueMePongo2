package main.java.quemepongo2.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.java.quemepongo2.api.responses.CiudadRs;
import main.java.quemepongo2.manager.CiudadService;
import main.java.quemepongo2.model.Ciudad;

@RestController
public class CiudadController {
	
	@Autowired
	private CiudadService repo;
	
	@GetMapping(params = "id", name = "/ObtenerCiudad")
	public String getCiduadById(@RequestParam int id) {
		Ciudad c = repo.getById(id);
		
		return c.getNombre() + " " + c.getPais() + " " + c.getLatitud() + " " + c.getLongitud();
	}
	
	
	@GetMapping(params = "q", name = "/ObtenerCiudad")
	public List<CiudadRs> getCiudadByName(@RequestParam String q) {
		return repo.getByLikeNombre(q);
	}
	
}
