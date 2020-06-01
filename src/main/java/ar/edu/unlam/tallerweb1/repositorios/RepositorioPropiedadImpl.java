package ar.edu.unlam.tallerweb1.repositorios;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;

@Repository("RepositorioPropiedad")

public class RepositorioPropiedadImpl implements RepositorioPropiedad {
	@Inject
    private SessionFactory sessionFactory;

    @Override
    public List<Propiedad> consultarPropiedad() {

        final Session session = sessionFactory.getCurrentSession();

        List<Propiedad> listaPropiedad = session.createCriteria(Propiedad.class).list();

        return listaPropiedad;
    }
    
	@Override
		
	public List<Propiedad> consultarPropiedadFilter(Propiedad propiedad) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		List<Propiedad> listaPropiedad = new ArrayList<Propiedad>();
		
		Criteria crit = session.createCriteria(Propiedad.class);
		
		Long min=0L;
		Long max=0L;
		
		if(propiedad.getCondicion().equalsIgnoreCase("null")) {
			propiedad.setCondicion(null);	
		}
		if(propiedad.getAmbiente().equalsIgnoreCase("null")) {
			propiedad.setAmbiente(null);
			
		}
		if(propiedad.getPrecioMin()!=null && propiedad.getPrecioMax()!=null) {
			min = propiedad.getPrecioMin();
			propiedad.setPrecioMin(null);
			
			max = propiedad.getPrecioMax();
			propiedad.setPrecioMax(null);
			
			listaPropiedad = crit.add(Example.create(propiedad))
								.add(Restrictions.between("precio", min, max)).list();	
		}
		else if(propiedad.getPrecioMin()==null && propiedad.getPrecioMax()!=null) {
					min = propiedad.getPrecioMax();
					propiedad.setPrecioMax(null);
					
					listaPropiedad = crit.add(Example.create(propiedad))
							.add(Restrictions.gt("precio",max)).list();	
					
				}
		else if(propiedad.getPrecioMin()!=null && propiedad.getPrecioMax()==null) {
			min = propiedad.getPrecioMin();
			propiedad.setPrecioMin(null);
			
			listaPropiedad = crit.add(Example.create(propiedad))
					.add(Restrictions.lt("precio",min)).list();	
			
		}else
		{
			listaPropiedad = crit.add(Example.create(propiedad)).list();	
		}		

		return listaPropiedad;
	}
	
	
	@Override
	public List<Propiedad> consultarNuevasPropiedades(){
		Calendar fechaActual = Calendar.getInstance();
		int dias = -10;
		fechaActual.add(Calendar.DAY_OF_YEAR, dias);
		Date fechaDesde = fechaActual.getTime();

		final Session session = sessionFactory.getCurrentSession();
		List<Propiedad> listaNuevasPropiedades = session.createCriteria(Propiedad.class)
		.add(Restrictions.gt("fechaPublicada",fechaDesde)).list();
		return listaNuevasPropiedades;

	}

	@Override
	public void favPropiedad(Favorito favorito) {		
		Session session = sessionFactory.openSession();		
		long id = (Long) session.save(favorito);		
	}

	@Override
	public List listaContadores() {
		final Session session = sessionFactory.getCurrentSession();
		
		List listaCounts = new ArrayList();
		
		Number casas = (Number) session.createCriteria(Propiedad.class)
									.add(Restrictions.eq("tipo", "1"))
					                .setProjection(Projections.rowCount())
					                .uniqueResult();		
		
		Number departamentos = (Number) session.createCriteria(Propiedad.class)
											.add(Restrictions.eq("tipo", "2"))
											.setProjection(Projections.rowCount())
											.uniqueResult();
		Number terrenos = (Number) session.createCriteria(Propiedad.class)
											.add(Restrictions.eq("tipo", "6"))
											.setProjection(Projections.rowCount())
											.uniqueResult();
		
		listaCounts.add(casas);
		listaCounts.add(departamentos);
		listaCounts.add(terrenos);

		return listaCounts;
	}

	@Override
	public List<Propiedad> propiedadesFavoritas (Usuario usuario){

    	final Session session = sessionFactory.getCurrentSession();
    	Criteria cri = session.createCriteria(Favorito.class,"favorito");
    	cri.add(Restrictions.eq("idUsuario", usuario.getId()));
    	List<Favorito> listaFavorito = cri.list();

		List<Propiedad> listaFavoritos = session.createCriteria(Propiedad.class)

				.createAlias("favorito.idPropiedad","fav")
				.add(Restrictions.eq("fav.idUsuario", usuario.getId())).list();

			return listaFavoritos;
	}
}
