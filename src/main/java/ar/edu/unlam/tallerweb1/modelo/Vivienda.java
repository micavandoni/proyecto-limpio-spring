package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Vivienda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer unidadFuncional;
	private String Domicilio;
	private String caracteristicas;
	private Long precio;
	
	
	public Integer getUnidadFuncional() {
		return unidadFuncional;
	}
	public void setUnidadFuncional(Integer unidadFuncional) {
		this.unidadFuncional = unidadFuncional;
	}
	public String getDomicilio() {
		return Domicilio;
	}
	public void setDomicilio(String domicilio) {
		Domicilio = domicilio;
	}
	public String getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public Long getPrecio() {
		return precio;
	}
	public void setPrecio(Long precio) {
		this.precio = precio;
	}
	
	
	
	

}
