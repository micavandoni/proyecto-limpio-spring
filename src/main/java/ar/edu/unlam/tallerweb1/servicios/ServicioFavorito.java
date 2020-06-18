package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioFavorito {


    List <Favorito> propiedadesFavoritas(Usuario usuario);

}
