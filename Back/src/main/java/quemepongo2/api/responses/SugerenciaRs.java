package main.java.quemepongo2.api.responses;


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
