package main.java.quemepongo2.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import main.java.quemepongo2.api.requests.LoginModel;
import main.java.quemepongo2.api.requests.UsuarioModel;
import main.java.quemepongo2.manager.UsuarioService;
import main.java.quemepongo2.model.Usuario;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService repo;
	
	@PostMapping("/usuario")
	public void createUsuario(@RequestBody UsuarioModel user) {
		Usuario us = new Usuario(user.getUsuario(), user.getClave());
		
		repo.save(us);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Integer> login(@RequestBody LoginModel logBody) {
		
		
		return null;
	}
}
