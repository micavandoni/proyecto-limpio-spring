package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Direccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String calle;
	private String altura;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Barrio barrio;

	public Direccion(String calle, String altura, Barrio barrio) {
		this.calle = calle;
		this.altura = altura;
		this.barrio = barrio;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}	

	public Barrio getBarrio() {
		return barrio;
	}


	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}
	
	
}
