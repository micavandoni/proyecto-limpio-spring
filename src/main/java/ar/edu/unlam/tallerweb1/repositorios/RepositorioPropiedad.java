package ar.edu.unlam.tallerweb1.repositorios;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import ar.edu.unlam.tallerweb1.clases.Generico;
import ar.edu.unlam.tallerweb1.modelo.Inmobiliaria;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface RepositorioPropiedad {

    List<Propiedad> consultarPropiedad();
    List<Propiedad> consultarPropiedadFilter(Propiedad propiedad);
    List<Propiedad> consultarNuevasPropiedades();
    void guardarFavoritoSeleccionado(Generico favoritoSeleccionado, Usuario usuario);
    List listaContadores();
    List<Propiedad> propiedadesFavoritasDeUnUsuario(Usuario usuario);
    void crearEventos();
    List<Publicacion> listaPublicacion(Propiedad propiedad);
    List<Inmobiliaria> consultarInmobiliarias(List<Publicacion> listaPublicaciones);
    Propiedad detallePropiedad(Propiedad propiedad);
    Propiedad ObtenerPropiedadPorId(Long id);
}
