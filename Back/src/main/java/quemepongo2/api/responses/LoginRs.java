package main.java.quemepongo2.api.responses;

import main.java.quemepongo2.model.Usuario;

public class LoginRs {
	private int id;
	private PreferenciaRs preferenciaRs;
	

	public LoginRs(Usuario user) {
		this.id = user.getId();
		this.preferenciaRs= new PreferenciaRs(user.getTieneBufanda(),user.getTieneLentes(),user.getTieneParaguas(),user.getTieneProtectorSolar(),user.getTieneGorra());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PreferenciaRs getPreferenciaRs() {
		return preferenciaRs;
	}

	public void setPreferenciaRs(PreferenciaRs preferenciaRs) {
		this.preferenciaRs = preferenciaRs;
	}
	
	
}
