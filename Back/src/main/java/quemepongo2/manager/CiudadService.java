package main.java.quemepongo2.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.quemepongo2.api.responses.CiudadRs;
import main.java.quemepongo2.model.Ciudad;
import main.java.quemepongo2.model.Usuario;
import main.java.quemepongo2.persistence.CiudadRepository;
import main.java.quemepongo2.persistence.UsuarioRepository;

@Service
public class CiudadService{

	@Autowired
	CiudadRepository repo;
	
	@Autowired
	UsuarioRepository repoUsuario;

	public List<CiudadRs> findAll() {
		List <CiudadRs> ciudadesRs = new ArrayList<CiudadRs>();
		List <Ciudad> ciudades=repo.findAll();
		for (Ciudad ciudad:ciudades) {
			ciudadesRs.add(new CiudadRs(ciudad));
		}
		return ciudadesRs;
	}

	public Ciudad getById(int id) {
		return repo.getOne(id);
	}
	
	public List<CiudadRs> getByLikeNombre(String q){
		List<CiudadRs> ciudadesRs = new ArrayList<>();
		
		List<Ciudad> ciudades = repo.searchCiudadLikeName(q); 
		
		for (Ciudad ciudad : ciudades) {
			ciudadesRs.add(new CiudadRs(ciudad));
		}
		
		return ciudadesRs;
	}
	
	public List<CiudadRs> getAllByUsuario(int userId){
		Usuario usuario = repoUsuario.getOne(userId);
		
		List <CiudadRs> ciudadesRs = new ArrayList<CiudadRs>();
		
		for (Ciudad ciudad : usuario.getCiudads()) {
			ciudadesRs.add(new CiudadRs(ciudad));
		}
		
		return ciudadesRs;
	}
}
