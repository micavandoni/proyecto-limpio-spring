package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private Long precio;
    private String direccion;
    private String detalle;
    private String localidad;
    private String provincia;
    private String condicion;
    private String imagenUrl;
    private String ambiente;
    private Long precioMin;
    private Long precioMax;

    public Propiedad(String tipo, Long precio, String direccion, String detalle, String localidad, String provincia, String condicion, String imagenUrl, String ambiente) {
        this.tipo = tipo;
        this.precio = precio;
        this.direccion = direccion;
        this.detalle = detalle;
        this.localidad = localidad;
        this.provincia = provincia;
        this.condicion = condicion;
        this.imagenUrl = imagenUrl;
        this.ambiente = ambiente;
    }


    
    public Propiedad(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
    
    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }



	public Long getPrecioMin() {
		return precioMin;
	}



	public void setPrecioMin(Long precioMin) {
		this.precioMin = precioMin;
	}



	public Long getPrecioMax() {
		return precioMax;
	}



	public void setPrecioMax(Long precioMax) {
		this.precioMax = precioMax;
	}
    
    
}
