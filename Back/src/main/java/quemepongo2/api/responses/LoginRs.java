package main.java.quemepongo2.api.responses;

import java.util.ArrayList;
import java.util.List;

import main.java.quemepongo2.model.CiudadUsuario;
import main.java.quemepongo2.model.Usuario;

public class LoginRs {
	private String token;
	private PreferenciaRs preferenciaRs;
	private List<CiudadRs> ciudades;
	private String nombreUsuario;
	private int genero;
	

	public LoginRs(Usuario user, String token) {
		this.token = token;
		this.preferenciaRs= new PreferenciaRs(user.isTieneBufanda(),user.isTieneLentes(),user.isTieneParaguas(),user.isTieneProtectorSolar(),user.isTieneGorra());
		this.ciudades = new ArrayList<CiudadRs>();
		this.nombreUsuario = user.getUsuario();
		this.genero = user.getGenero();
		
		for (CiudadUsuario ciudadUsuario : user.getCiudadUsuarios()) {
			ciudades.add(new CiudadRs(ciudadUsuario.getCiudad()));
		}
	}

	public PreferenciaRs getPreferenciaRs() {
		return preferenciaRs;
	}

	public void setPreferenciaRs(PreferenciaRs preferenciaRs) {
		this.preferenciaRs = preferenciaRs;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}

	public List<CiudadRs> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<CiudadRs> ciudades) {
		this.ciudades = ciudades;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}
	
	
}
