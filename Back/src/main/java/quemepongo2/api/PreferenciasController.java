package main.java.quemepongo2.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import main.java.quemepongo2.api.requests.PreferenciaRq;
import main.java.quemepongo2.manager.PreferenciaService;

@RestController
public class PreferenciasController {

	
	@Autowired
	PreferenciaService repo;
	
	@PostMapping(name = "/ActualizarPreferencias")
	public void actualizarPreferencias(@RequestBody PreferenciaRq preferenciaRq) {
		repo.actualizarPreferencias(preferenciaRq);
	}
}
