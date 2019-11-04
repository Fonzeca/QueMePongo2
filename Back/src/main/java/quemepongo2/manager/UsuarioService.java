package main.java.quemepongo2.manager;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.quemepongo2.api.requests.LoginRq;
import main.java.quemepongo2.api.requests.UsuarioRq;
import main.java.quemepongo2.api.responses.LoginRs;
import main.java.quemepongo2.model.Ciudad;
import main.java.quemepongo2.model.Usuario;
import main.java.quemepongo2.persistence.CiudadRepository;
import main.java.quemepongo2.persistence.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repo;
	
	@Autowired
	CiudadRepository repoCiudad;
	
	@Autowired
	SecurityConfig tokenGenerator;
	
	public LoginRs validateLogin(LoginRq loginRq) {
		Usuario user = repo.searchByUsuario(loginRq.getUsuario());
		if(user == null || !user.getClave().equals(cifrarClave(loginRq.getClave())) ) {
			throw new RuntimeException("Usuario y/o clave incorrectos.");
		}
		
		//Creamos el token y se lo damos al RS
		String token = tokenGenerator.crearToken(user.getId());
		
		LoginRs log = new LoginRs(user, token);
		return log;
	}
	
	public void saveNewUser(UsuarioRq userRq) {
		Usuario usuario = new Usuario();
		
		usuario.setUsuario(userRq.getUsuario());
		
		String cifrado = cifrarClave(userRq.getClave());
		usuario.setClave(cifrado);
		
		repo.save(usuario);
	}
	
	@Transactional
	public void addCiudad(int ciudadId, int userId) {
		//Obtenemos el usuario a agregarle la ciudad
		Usuario usuario = repo.findById(userId).get();
		
		Ciudad ciudad = repoCiudad.findById(ciudadId).get();

		//Le agregamos la ciudad a la lista de ciudades del usuario
		usuario.getCiudads().add(ciudad);
		
		//Se guarda el usuario con la nueva ciudad
		repo.save(usuario);
	}
	
	public void deleteCiudad(int ciudadId, int userId) {
		//Obtenemos el usuario a agregarle la ciudad
		Usuario usuario = repo.findById(userId).get();
		
		for (Ciudad ciudad : usuario.getCiudads()) {
			if(ciudad.getId() == ciudadId) {
				usuario.getCiudads().remove(ciudad);
				
				//Se guarda el usuario sin la ciudad
				repo.save(usuario);
				return;
			}
		}
		
		throw new RuntimeException("No se encontro la ciudad que se quiere remover del usuario : "+ userId);
	}
	
	private String cifrarClave(String clave) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodeBytes = digest.digest(clave.getBytes(StandardCharsets.UTF_8));
			encodeBytes = Base64.getEncoder().encode(encodeBytes);
			return new String(encodeBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
