package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Inmobiliaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idInmobiliaria", updatable = false, nullable = false)
	private Long id;
	private String nombreInmobiliaria;
	private String domicilio;
	private String email;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "misPropiedades",
            joinColumns = {@JoinColumn(name = "idInmobiliaria")},
            inverseJoinColumns = {@JoinColumn(name = "idPropiedad")})
	
    private List<Propiedad> propiedades = new ArrayList<Propiedad>();
	
	public List<Propiedad> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(List<Propiedad> propiedades) {
		this.propiedades = propiedades;
	}
	public void addPropiedad(Propiedad propiedad) {
        this.propiedades.add(propiedad);
        propiedad.getInmobiliarias().add(this);
    }
    
    public void removePropiedad(Propiedad propiedad) {
    	this.propiedades.remove(propiedad);
    	propiedad.getInmobiliarias().remove(this);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreInmobiliaria() {
		return nombreInmobiliaria;
	}

	public void setNombreInmobiliaria(String nombreInmobiliaria) {
		this.nombreInmobiliaria = nombreInmobiliaria;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
