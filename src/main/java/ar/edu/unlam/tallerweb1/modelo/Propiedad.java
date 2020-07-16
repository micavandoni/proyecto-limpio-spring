package ar.edu.unlam.tallerweb1.modelo;


import java.util.*;

import javax.persistence.*;

@Entity(name = "Propiedad")
@Table(name = "Propiedad")
public class Propiedad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPropiedad", updatable = false, nullable = false)
	private Long id;
	@ManyToOne
	private TipoPropiedad tipoPropiedad;
	private String titulo;
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
	private Double latitud;
	private Double longitud;
	private String imagenUrl2;
	private String imagenUrl3;
	private String imagenUrl4;
	

	@Basic
	private Date fechaPublicada;

    @ManyToMany(mappedBy = "propiedades")
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    @ManyToMany(mappedBy = "propiedades")
    private List<Inmobiliaria> inmobiliarias = new ArrayList<Inmobiliaria>();
    
	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Propiedad)) return false;
        return id != null && id.equals(((Propiedad) obj).getId());
	}

	

	public Propiedad() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPropiedad getTipoPropiedad() {
		return tipoPropiedad;
	}

	public void setTipoPropiedad(TipoPropiedad tipoPropiedad) {
		this.tipoPropiedad = tipoPropiedad;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	

	public Date getFechaPublicada() {
		return fechaPublicada;
	}

	public void setFechaPublicada(Date date) {
		this.fechaPublicada = date;
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
	public String getImagenUrl3() {
		return imagenUrl3;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Inmobiliaria> getInmobiliarias() {
		return inmobiliarias;
	}

	public void setInmobiliarias(List<Inmobiliaria> inmobiliarias) {
		this.inmobiliarias = inmobiliarias;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public String getImagenUrl2() {
		return imagenUrl2;
	}

	public void setImagenUrl2(String imagenUrl2) {
		this.imagenUrl2 = imagenUrl2;
	}

	public String getImagenUrl4() {
		return imagenUrl4;
	}

	public void setImagenUrl4(String imagenUrl4) {
		this.imagenUrl4 = imagenUrl4;
	}

	public void setImagenUrl3(String imagenUrl3) {
		this.imagenUrl3 = imagenUrl3;
	}

	
}
