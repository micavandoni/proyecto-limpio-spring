package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public interface ServicioPropiedad {

    List<Propiedad> consultarPropiedad();
    List<Propiedad> consultarPropiedadFilter(Propiedad propiedad);
    List<Propiedad> consultarNuevasPropiedades();
    void favPropiedad(Favorito favorito);
    List listaContadores();
    List <Propiedad> propiedadesFavoritas(Usuario usuario);

}
