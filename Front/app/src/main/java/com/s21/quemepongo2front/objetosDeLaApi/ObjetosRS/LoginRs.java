package com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS;

import java.util.List;


public class LoginRs {
	private String token;
	private PreferenciaRs preferenciaRs;
	private List<CiudadRs> ciudades;
	

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
