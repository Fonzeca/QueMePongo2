package main.java.quemepongo2.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.java.quemepongo2.model.CiudadUsuario;
import main.java.quemepongo2.model.CiudadUsuarioId;

@Repository
public interface CiudadUsuarioRepository extends JpaRepository<CiudadUsuario, CiudadUsuarioId> {

}
