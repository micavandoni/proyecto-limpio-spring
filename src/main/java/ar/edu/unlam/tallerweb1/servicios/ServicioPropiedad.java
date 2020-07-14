package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Set;

import ar.edu.unlam.tallerweb1.clases.Generico;
import ar.edu.unlam.tallerweb1.modelo.Inmobiliaria;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioPropiedad {

    List<Propiedad> consultarPropiedad();
    List<Propiedad> consultarPropiedadFilter(Propiedad propiedad);
    List<Propiedad> consultarNuevasPropiedades();
    List listaContadores();
    List<Propiedad> propiedadesFavoritasDeUnUsuario(Usuario usuario);
    boolean crearEventos();
	void guardarFavoritoSeleccionado(Generico favoritoSeleccionado, Usuario usuario);
	List<Publicacion> listaPublicacion(Propiedad propiedad);
	List<Inmobiliaria> consultarInmobiliarias(List<Publicacion> listaPublicaciones);
	Propiedad detallePropiedad(Propiedad propiedad);
}
