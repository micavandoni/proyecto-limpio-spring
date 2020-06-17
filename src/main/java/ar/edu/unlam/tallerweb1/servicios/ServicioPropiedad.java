package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;

public interface ServicioPropiedad {

    List<Propiedad> consultarPropiedad();
    List<Propiedad> consultarPropiedadFilter(Propiedad propiedad);
    List<Propiedad> consultarNuevasPropiedades();
    void favPropiedad(Favorito favorito);
    List listaContadores();
    List<Propiedad> propiedadesFavoritasDeUnUsuario(List<Favorito> listaFavoritos);
    boolean crearEventos();
}
