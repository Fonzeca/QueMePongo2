package main.java.quemepongo2.api.responses;

import main.java.quemepongo2.model.Usuario;

public class LoginRs {
	private String token;
	private PreferenciaRs preferenciaRs;
	

	public LoginRs(Usuario user, String token) {
		this.token = token;
		this.preferenciaRs= new PreferenciaRs(user.isTieneBufanda(),user.isTieneLentes(),user.isTieneParaguas(),user.isTieneProtectorSolar(),user.isTieneGorra());
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
	
	
}
