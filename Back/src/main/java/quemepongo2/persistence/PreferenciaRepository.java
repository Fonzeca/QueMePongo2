package main.java.quemepongo2.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.java.quemepongo2.model.Preferencias;

@Repository
public interface PreferenciaRepository extends JpaRepository<Preferencias, Integer> {
	
}
