package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPropiedad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioFavorito")
@Transactional
public class ServicioFavoritoImpl implements ServicioFavorito{

    private RepositorioPropiedad repositorioPropiedad;

	@Autowired
	public ServicioFavoritoImpl(RepositorioPropiedad repositorioPropiedad){
		this. repositorioPropiedad= repositorioPropiedad;
	}

	@Override
	public List<Propiedad> propiedadesFavoritas(Usuario usuario){
		return  repositorioPropiedad.propiedadesFavoritas(usuario);}


}
