package main.java.quemepongo2.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.quemepongo2.model.Usuario;
import main.java.quemepongo2.persistence.IDaoInterface;
import main.java.quemepongo2.persistence.UsuarioRepository;

@Service
public class UsuarioService implements IDaoInterface<Usuario>{

	@Autowired
	UsuarioRepository repo;

	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public void save(Usuario entity) {
		repo.save(entity);
	}
	
	
}
