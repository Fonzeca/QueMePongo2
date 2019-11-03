package main.java.quemepongo2.persistence;

import java.util.List;

import main.java.quemepongo2.api.responses.CiudadRs;

public interface IDaoInterface<T> {
	List<CiudadRs> findAll(); // List<Ciudad> 
	void save(T entity);
}
