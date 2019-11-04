package main.java.quemepongo2.api.responses;

import java.util.ArrayList;
import java.util.List;

import main.java.quemepongo2.model.Ciudad;
import main.java.quemepongo2.model.Usuario;

public class LoginRs {
	private String token;
	private PreferenciaRs preferenciaRs;
	private List<CiudadRs> ciudades;
	

	public LoginRs(Usuario user, String token) {
		this.token = token;
		this.preferenciaRs= new PreferenciaRs(user.isTieneBufanda(),user.isTieneLentes(),user.isTieneParaguas(),user.isTieneProtectorSolar(),user.isTieneGorra());
		this.ciudades = new ArrayList<CiudadRs>();
		
		for (Ciudad ciudad : user.getCiudads()) {
			ciudades.add(new CiudadRs(ciudad));
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
	
	
}
