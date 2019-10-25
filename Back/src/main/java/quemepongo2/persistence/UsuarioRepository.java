package main.java.quemepongo2.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.java.quemepongo2.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("SELECT u FROM Usuario u WHERE u.usuario = :usuarioP")
	Usuario searchByUsuario(@Param("usuarioP") String usuario );
}
