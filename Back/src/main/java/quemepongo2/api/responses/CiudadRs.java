package main.java.quemepongo2.api.responses;

import main.java.quemepongo2.model.Ciudad;

public class CiudadRs {
	private int id;
	private String nombre;
	
	public CiudadRs() {
	}
	
	public CiudadRs(Ciudad ciudadBD) {
		id = ciudadBD.getId();
		nombre = ciudadBD.getNombre();
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
}
