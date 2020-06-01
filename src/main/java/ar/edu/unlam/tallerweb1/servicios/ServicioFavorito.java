package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioFavorito {


    List <Propiedad> propiedadesFavoritas(Usuario usuario);

}
