package main.java.quemepongo2.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import main.java.quemepongo2.api.requests.LoginRq;
import main.java.quemepongo2.api.requests.UsuarioRq;
import main.java.quemepongo2.api.responses.LoginRs;
import main.java.quemepongo2.manager.SecurityConfig;
import main.java.quemepongo2.manager.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService repo;
	
	@Autowired
	private SecurityConfig tokenGenerator;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/CrearUsuario")
	public void createUsuario(@RequestBody UsuarioRq user){
		repo.saveNewUser(user);
	}
	
	@PostMapping("/Login")
	public LoginRs login(@RequestBody LoginRq logBody) {
		return repo.validateLogin(logBody);
	}
	
	@PostMapping("/AgregarCiudad")
	public void addCiudadToUsuario(int ciudadId, String token) {
		int userId = tokenGenerator.validarToken(token);
		log.info("API, " + Thread.currentThread().getStackTrace()[1].getMethodName() + ", userId: " + userId);
		
		repo.addCiudad(ciudadId, userId);
		
	}
	
	@DeleteMapping("/QuitarCiudad")
	public void deleteCiudadOfUsuario(int ciudadId, String token) {
		int userId = tokenGenerator.validarToken(token);
		log.info("API, " + Thread.currentThread().getStackTrace()[1].getMethodName() + ", userId: " + userId);
		
		repo.deleteCiudad(ciudadId, userId);
	}
}
