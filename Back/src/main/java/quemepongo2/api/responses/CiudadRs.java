package main.java.quemepongo2.api.responses;

import java.math.BigDecimal;

import main.java.quemepongo2.model.Ciudad;

public class CiudadRs {
	private int id;
	private String nombre;
	private String pais;
	private BigDecimal latitud;
	private BigDecimal longitud;
	
	public CiudadRs() {
	}
	
	public CiudadRs(Ciudad ciudadBD) {
		id = ciudadBD.getId();
		nombre = ciudadBD.getNombre();
		pais = ciudadBD.getPais();
		latitud = ciudadBD.getLatitud();
		longitud = ciudadBD.getLongitud();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public BigDecimal getLatitud() {
		return latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}
}
