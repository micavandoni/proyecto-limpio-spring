package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioFavorito;

@Service("servicioFavorito")
@Transactional
public class ServicioFavoritoImpl implements ServicioFavorito{

    private RepositorioFavorito repositorioFavorito;

	@Autowired
	public ServicioFavoritoImpl(RepositorioFavorito repositorioFavorito){
		this. repositorioFavorito = repositorioFavorito;
	}

	@Override
	public List<Favorito> propiedadesFavoritas(Usuario usuario){
		return  repositorioFavorito.propiedadesFavoritas(usuario);}


}
