package ar.edu.unlam.tallerweb1.repositorios;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		
		if(propiedad.getCondicion().equalsIgnoreCase("todo")) {
			listaPropiedad = session.createCriteria(Propiedad.class).list();
			
		}else {
			// if (propiedad.getAmbiente().equalsIgnoreCase("1") || propiedad.getAmbiente().equalsIgnoreCase("2") || propiedad.getAmbiente().equalsIgnoreCase("3")
			//		|| propiedad.getAmbiente().equalsIgnoreCase("4")) {
				//listaPropiedad = session.createCriteria(Propiedad.class)
					//	.add(Restrictions.eq("ambiente", propiedad.getAmbiente())).list();
			//}
			listaPropiedad = session.createCriteria(Propiedad.class)
					.add(Restrictions.eq("condicion", propiedad.getCondicion())).list();
		}
		return listaPropiedad;
	}
	@Override
	public List<Propiedad> consultarNuevasPropiedades(){
		Calendar fechaActual = Calendar.getInstance();
		int dias = -5;
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
	public Integer listaContadores() {
		//ArrayList<Integer> listaContadores = new ArrayList<Integer>();
		
		final Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Propiedad.class)
				.add(Restrictions.eq("tipo", 1))
				.setProjection(Projections.rowCount());
		Integer casas = (Integer)criteria.uniqueResult();
		return casas;
		//Criteria critCasas = session.createCriteria(Propiedad.class);
		//critCasas.add(Restrictions.eq("tipo", 1));
		//critCasas.setProjection(Projections.rowCount());
		//List casas = critCasas.list();
		//Integer countCasas = (Integer)casas.get(0);
		//listaContadores.add(countCasas);

		//return countCasas;
	}

}
