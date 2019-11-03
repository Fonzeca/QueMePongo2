package com.s21.quemepongo2front;

import main.java.quemepongo2.model.Usuario;

public class LoginRs {
	private int id;
	private PreferenciaRs preferenciaRs;
	

	public LoginRs(Usuario user) {
		this.id = user.getId();
		this.preferenciaRs= new PreferenciaRs(user.isTieneBufanda(),user.isTieneLentes(),user.isTieneParaguas(),user.isTieneProtectorSolar(),user.isTieneGorra());
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
