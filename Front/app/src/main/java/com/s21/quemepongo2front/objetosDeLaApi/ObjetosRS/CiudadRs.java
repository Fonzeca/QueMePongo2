package com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS;


import androidx.annotation.NonNull;

public class CiudadRs {
	private int id;
	private String nombre;
	private String pais;
	
	public CiudadRs() {
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


	public String toString() {
		return nombre + ", " + pais;
	}
}