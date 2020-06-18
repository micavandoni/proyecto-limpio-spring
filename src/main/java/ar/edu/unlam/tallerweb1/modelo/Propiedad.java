package ar.edu.unlam.tallerweb1.modelo;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	private Long precio;
	private String direccion;
	private String detalle;
	private String localidad;
	private String provincia;
	private String condicion;
	private String imagenUrl;
	private String imagenUrl2;
	private String imagenUrl3;
	private String imagenUrl4;
	public String getImagenUrl3() {
		return imagenUrl3;
	}

	public void setImagenUrl3(String imagenUrl3) {
		this.imagenUrl3 = imagenUrl3;
	}

	public String getImagenUrl4() {
		return imagenUrl4;
	}

	public void setImagenUrl4(String imagenUrl4) {
		this.imagenUrl4 = imagenUrl4;
	}

	public String getImagenUrl2() {
		return imagenUrl2;
	}

	public void setImagenUrl2(String imagenUrl2) {
		this.imagenUrl2 = imagenUrl2;
	}

	private String ambiente;
	private Long precioMin;
	private Long precioMax;
	@ManyToOne
	private Favorito favorito;
	

	public Favorito getFavorito() {
		return favorito;
	}

	public void setFavorito(Favorito favorito) {
		this.favorito = favorito;
	}
	@Basic
	private Date fechaPublicada;

    @ManyToMany(mappedBy = "propiedades")
    //private List<Usuario> usuarios = new ArrayList<Usuario>();
    private Set<Usuario> usuarios = new HashSet<>();
    

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

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
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

	

//	public List<Usuario> getFans() {
//		return fans;
//	}
//
//	public void setFans(List<Usuario> fans) {
//		this.fans = fans;
//	}
//
//	public void agregarFan(Usuario usuario) {
//		this.fans.add(usuario);
//	}
}
