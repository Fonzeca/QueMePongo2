package main.java.quemepongo2.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.quemepongo2.model.Ciudad;
import main.java.quemepongo2.persistence.CiudadRepository;
import main.java.quemepongo2.persistence.IDaoInterface;

@Service
public class CiudadService implements IDaoInterface<Ciudad>{

	@Autowired
	CiudadRepository repo;

	public List<Ciudad> findAll() {
		return repo.findAll();
	}

	public void save(Ciudad entity) {
		repo.save(entity);
	}
	
	public Ciudad getById(int id) {
		return repo.getOne(id);
	}
	
//	public List<Ciudad> getByLikeNombre(String q){
//		repo.
//		
//	}
//	
}
