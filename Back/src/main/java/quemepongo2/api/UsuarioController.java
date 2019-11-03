package main.java.quemepongo2.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import main.java.quemepongo2.api.requests.LoginRq;
import main.java.quemepongo2.api.requests.UsuarioRq;
import main.java.quemepongo2.api.responses.LoginRs;
import main.java.quemepongo2.manager.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService repo;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/CrearUsuario")
	public void createUsuario(@RequestBody UsuarioRq user){
		repo.saveNewUser(user);
	}
	
	@PostMapping("/Login")
	public LoginRs login(@RequestBody LoginRq logBody) {
		return repo.validateLogin(logBody);
	}
	
	
}
