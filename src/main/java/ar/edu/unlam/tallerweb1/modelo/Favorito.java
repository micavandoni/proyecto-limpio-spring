package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Favorito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idPropiedad;
	

	private Long idUsuario;

	@ManyToOne
	private Usuario usuario = new Usuario();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Long getIdPropiedad() {
		return idPropiedad;
	}

	public void setIdPropiedad(Long idPropiedad) {
		this.idPropiedad = idPropiedad;
	}
}
