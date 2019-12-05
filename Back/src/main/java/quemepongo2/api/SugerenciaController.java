package main.java.quemepongo2.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.java.quemepongo2.api.responses.SugerenciaRs;
import main.java.quemepongo2.manager.SecurityConfig;
import main.java.quemepongo2.manager.SugerenciaService;
import main.java.quemepongo2.manager.UsuarioService;

@RestController
public class SugerenciaController {

	@Autowired
	private SugerenciaService service;
	
	@Autowired
	private UsuarioService serviceUsuario;
	
	@Autowired
	private SecurityConfig tokenGenerator;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/ObtenerSugerencia")
	public SugerenciaRs obtenerSugerencia(@RequestParam int ciudadId, @RequestParam String token) {
		int userId = tokenGenerator.validarToken(token);
		log.info("API, " + Thread.currentThread().getStackTrace()[1].getMethodName() + ", userId: " + userId + ", ciudadId: " + ciudadId);
		int genero = serviceUsuario.getGenero(userId);
		return service.obtenerClo(ciudadId,genero);
		
		
	}
}
