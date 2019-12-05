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
import main.java.quemepongo2.model.CiudadUsuario;
import main.java.quemepongo2.model.CiudadUsuarioId;
import main.java.quemepongo2.model.Usuario;
import main.java.quemepongo2.persistence.CiudadRepository;
import main.java.quemepongo2.persistence.CiudadUsuarioRepository;
import main.java.quemepongo2.persistence.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repo;
	
	@Autowired
	CiudadRepository repoCiudad;
	
	@Autowired
	CiudadUsuarioRepository repoCiudadUsuario;
	
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
	
	public LoginRs saveNewUser(UsuarioRq userRq) {
		Usuario usuario = new Usuario();
		
		usuario.setUsuario(userRq.getUsuario());
		usuario.setGenero(userRq.getGenero());
		
		String cifrado = cifrarClave(userRq.getClave());
		usuario.setClave(cifrado);
		
		repo.save(usuario);
		
		//Creamos el token y se lo damos al RS
		String token = tokenGenerator.crearToken(usuario.getId());
		
		LoginRs log = new LoginRs(usuario, token);
		return log;
	}
	
	@Transactional
	public void addCiudad(int ciudadId, int userId) {
		//Obtenemos el usuario a agregarle la ciudad
		Usuario usuario = repo.findById(userId).get();
		
		Ciudad ciudad = repoCiudad.findById(ciudadId).get();

		//Agregamos una nueva entidad a la tabla many to many
		CiudadUsuario ciudadUsuario = new CiudadUsuario();
		ciudadUsuario.setId(new CiudadUsuarioId(ciudadId, userId));
		repoCiudadUsuario.save(ciudadUsuario);
	}
	
	@Transactional
	public void deleteCiudad(int ciudadId, int userId) {
		//Obtenemos el usuario a agregarle la ciudad
		Usuario usuario = repo.findById(userId).get();
		
		//Buscamos entre las CiudadesUsuario del usuario el que tenga el mismo ciudadId
		for (CiudadUsuario ciudadUsuario : usuario.getCiudadUsuarios()) {
			if(ciudadUsuario.getId().getCiudadId() == ciudadId) {
				
				//Al encontrarlo lo borramos de base de datos
				repoCiudadUsuario.deleteById(new CiudadUsuarioId(ciudadId, userId));
				return;
			}
		}
		
		throw new RuntimeException("No se encontro la ciudad que se quiere remover del usuario : "+ userId);
	}
	
	public int getGenero(int userId) {
		int genero;
		Usuario usuario = repo.findById(userId).get();
		genero = usuario.getGenero();
		return genero;
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
