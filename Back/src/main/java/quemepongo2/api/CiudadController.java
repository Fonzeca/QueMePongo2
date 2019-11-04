package main.java.quemepongo2.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.java.quemepongo2.api.responses.CiudadRs;
import main.java.quemepongo2.manager.CiudadService;
import main.java.quemepongo2.manager.SecurityConfig;
import main.java.quemepongo2.model.Ciudad;

@RestController
public class CiudadController {
	
	@Autowired
	private CiudadService repo;
	
	@Autowired
	private SecurityConfig tokenGenerator;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	//TODO: Cambiar el response
	@GetMapping(params = "id", name = "/ObtenerCiudad")
	public String getCiduadById(@RequestParam int id, @RequestParam String token) {
		int userId = tokenGenerator.validarToken(token);
		log.info("API, " + Thread.currentThread().getStackTrace()[1].getMethodName() + ", userId: " + userId);
		
		
		Ciudad c = repo.getById(id);
		
		return c.getNombre() + " " + c.getPais() + " " + c.getLatitud() + " " + c.getLongitud();
	}
	
	
	@GetMapping(params = "q", name = "/ObtenerCiudad")
	public List<CiudadRs> getCiudadByName(@RequestParam String q, @RequestParam String token) {
		int userId = tokenGenerator.validarToken(token);
		log.info("API, " + Thread.currentThread().getStackTrace()[1].getMethodName() + ", userId: " + userId);
		
		return repo.getByLikeNombre(q);
	}
	
	@GetMapping(name="/ListarCiudades")
	public List<CiudadRs> getCiudades(@RequestParam String token){
		int userId = tokenGenerator.validarToken(token);
		log.info("API, " + Thread.currentThread().getStackTrace()[1].getMethodName() + ", userId: " + userId);
		
		return repo.findAll();
	}
}