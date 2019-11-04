package main.java.quemepongo2.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.quemepongo2.api.responses.CiudadRs;
import main.java.quemepongo2.model.Ciudad;
import main.java.quemepongo2.persistence.CiudadRepository;

@Service
public class CiudadService{

	@Autowired
	CiudadRepository repo;

	public List<CiudadRs> findAll() {
		List <CiudadRs> ciudadesRs = new ArrayList<CiudadRs>();
		List <Ciudad> ciudades=repo.findAll();
		for (Ciudad ciudad:ciudades) {
			ciudadesRs.add(new CiudadRs(ciudad));
		}
		return ciudadesRs;
		
	}

	public void save(Ciudad entity) {
		repo.save(entity);
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
	
}
