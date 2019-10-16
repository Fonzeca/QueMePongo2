package main.java.quemepongo2.model;
// Generated 16-oct-2019 12:27:16 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "Usuario", schema = "dbo", catalog = "QueMePongo2")
public class Usuario implements java.io.Serializable {

	private Integer id;
	private String clave;
	private String usuario;

	public Usuario() {
	}

	public Usuario(String clave, String usuario) {
		this.clave = clave;
		this.usuario = usuario;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Clave", nullable = false)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column(name = "Usuario", nullable = false)
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
