package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Set;

import ar.edu.unlam.tallerweb1.clases.Generico;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioPropiedad {

    List<Propiedad> consultarPropiedad();
    List<Propiedad> consultarPropiedadFilter(Propiedad propiedad);
    List<Propiedad> consultarNuevasPropiedades();
    List listaContadores();
    Set<Propiedad> propiedadesFavoritasDeUnUsuario(Usuario usuario);
    boolean crearEventos();
	void guardarFavoritoSeleccionado(Generico favoritoSeleccionado, Usuario usuario);
}
