package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;


public interface RepositorioFavorito {


    List<Favorito> propiedadesFavoritas(Usuario usuario);

}
