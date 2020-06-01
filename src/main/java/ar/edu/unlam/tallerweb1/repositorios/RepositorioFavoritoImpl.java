package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

import java.util.List;

@Repository("RepositorioFavorito")

public class RepositorioFavoritoImpl implements RepositorioFavorito {
	@Inject
    private SessionFactory sessionFactory;


	@Override
	public List<Propiedad> propiedadesFavoritas (Usuario usuario){

    	final Session session = sessionFactory.getCurrentSession();
    	Criteria cri = session.createCriteria(Favorito.class,"favorito");
    	cri.add(Restrictions.eq("idUsuario", usuario.getId()));
    	List<Favorito> listaFavorito = cri.list();

		List<Favorito> listaFavoritos = session.createCriteria(Favorito.class)

				.createAlias("favorito.idPropiedad","fav")
				.add(Restrictions.eq("fav.idUsuario", usuario.getId())).list();

			return null;
	}
}
