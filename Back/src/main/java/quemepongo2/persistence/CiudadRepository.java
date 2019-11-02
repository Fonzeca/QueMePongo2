package main.java.quemepongo2.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.java.quemepongo2.model.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
	
	@Query("SELECT c FROM Ciudad c WHERE c.nombre LIKE :name%")
	List<Ciudad> searchCiudadLikeName(@Param("name")String name);
}
