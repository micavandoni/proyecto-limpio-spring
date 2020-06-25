package ar.edu.unlam.tallerweb1.modelo;

import java.util.*;

import javax.persistence.*;

// Clase que modela el concepto de Usuario, la anotacion @Entity le avisa a hibernate que esta clase es persistible
// el paquete ar.edu.unlam.tallerweb1.modelo esta indicado en el archivo hibernateCOntext.xml para que hibernate
// busque entities en él
@Entity(name = "Usurio")
@Table(name = "Usuario")
public class Usuario {

	// La anotacion id indica que este atributo es el utilizado como clave primaria
	// de la entity, se indica que el valor es autogenerado.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuario", updatable = false, nullable = false)
	private Long id;
	// para el resto de los atributo no se usan anotaciones entonces se usa el
	// default de hibernate: la columna se llama igual que
	// el atributo, la misma admite nulos, y el tipo de dato se deduce del tipo de
	// dato de java.
	public String email;
	public String password;
	public String rol;
	public String nombre;
	
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "favorito",
            joinColumns = {@JoinColumn(name = "idUsuario")},
            inverseJoinColumns = {@JoinColumn(name = "idPropiedad")})
	
    private List<Propiedad> propiedades = new ArrayList<Propiedad>();

    
    public void addPropiedad(Propiedad propiedad) {
        this.propiedades.add(propiedad);
        propiedad.getUsuarios().add(this);
    }
    
    public void removePropiedad(Propiedad propiedad) {
    	this.propiedades.remove(propiedad);
    	propiedad.getUsuarios().remove(this);
    }

        
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return Objects.equals(id, usuario.id);
		
	}

	public List<Propiedad> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(List<Propiedad> propiedades) {
		this.propiedades = propiedades;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}
