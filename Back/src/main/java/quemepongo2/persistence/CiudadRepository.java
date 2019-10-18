package main.java.quemepongo2.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.java.quemepongo2.model.Ciudad;
import main.java.quemepongo2.model.Usuario;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
	
}
