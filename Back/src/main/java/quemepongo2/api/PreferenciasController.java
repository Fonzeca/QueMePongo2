package main.java.quemepongo2.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.java.quemepongo2.api.requests.PreferenciaRq;
import main.java.quemepongo2.api.responses.PreferenciaRs;
import main.java.quemepongo2.manager.PreferenciaService;
import main.java.quemepongo2.manager.SecurityConfig;

@RestController
public class PreferenciasController {
	
	@Autowired
	private PreferenciaService service;
	
	@Autowired
	private SecurityConfig tokenGenerator;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/ActualizarPreferencias")
	public PreferenciaRs actualizarPreferencias(@RequestBody PreferenciaRq preferenciaRq, @RequestParam String token) {
		int userId = tokenGenerator.validarToken(token);
		log.info("API, " + Thread.currentThread().getStackTrace()[1].getMethodName() + ", userId: " + userId);
		return service.actualizarPreferencias(preferenciaRq, userId);
	}
	
	@GetMapping("/ObtenerPreferencias")
	public PreferenciaRs obtenerPreferencias(@RequestParam String token) {
		int userId = tokenGenerator.validarToken(token);
		log.info("API, " + Thread.currentThread().getStackTrace()[1].getMethodName() + ", userId: " + userId);
		return service.obtenerPreferncias(userId);
	}
}
