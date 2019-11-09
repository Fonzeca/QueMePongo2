package com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS;


public class SugerenciaRs {
	private double clo;
	private String sugerencia;
	
	public SugerenciaRs(double clo) {
		this.clo = clo;
	}
	
	public double getClo() {
		return clo;
	}

	public void setClo(double clo) {
		this.clo = clo;
	}

	public String getSugerencia() {
		return sugerencia;
	}

	public void setSugerencia(String sugerencia) {
		this.sugerencia = sugerencia;
	}
	
}
