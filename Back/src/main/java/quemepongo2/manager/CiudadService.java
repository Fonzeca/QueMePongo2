package main.java.quemepongo2.manager;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import main.java.quemepongo2.api.openweather.forecast.City;
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
	
}
