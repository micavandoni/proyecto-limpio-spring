package ar.edu.unlam.tallerweb1.repositorios;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface RepositorioPropiedad {

    List<Propiedad> consultarPropiedad();
    List<Propiedad> consultarPropiedadFilter(Propiedad propiedad);
    List<Propiedad> consultarNuevasPropiedades();
    void favPropiedad(Favorito favorito);
    List listaContadores();
    List<Propiedad> propiedadesFavoritasDeUnUsuario(List<Favorito> listaFavoritos);
}
