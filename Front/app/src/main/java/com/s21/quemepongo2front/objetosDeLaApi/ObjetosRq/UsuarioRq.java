package com.s21.quemepongo2front.objetosDeLaApi.ObjetosRq;

public class UsuarioRq{
	private String usuario, clave;
	private int genero; //0 = hombre, 1 = mujer, 2 = otro;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

}
