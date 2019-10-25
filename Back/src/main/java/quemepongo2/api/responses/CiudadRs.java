package main.java.quemepongo2.api.responses;

import main.java.quemepongo2.model.Ciudad;

public class CiudadRs {
	private int id;
	private String nombre;
	private String pais;
	
	public CiudadRs() {
	}
	
	public CiudadRs(Ciudad ciudadBD) {
		id = ciudadBD.getId();
		nombre = ciudadBD.getNombre();
		pais = ciudadBD.getPais();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		id = id;
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
}
