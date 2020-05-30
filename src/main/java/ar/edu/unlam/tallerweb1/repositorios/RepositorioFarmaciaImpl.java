package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import ar.edu.unlam.tallerweb1.modelo.Farmacia;

@Repository("repositorioFarmacia")
public class RepositorioFarmaciaImpl implements RepositorioFarmacia{
	
	@Inject
	private SessionFactory sessionFactory;
	
	//@Autowired
	//public RepositorioFarmaciaImpl(SessionFactory sessionFactory) {
		//this.sessionFactory = sessionFactory;
	//}

	@Override
	public List<Farmacia> consultarFarmaciaPorNombre(String nombre) {
		final Session session = sessionFactory.getCurrentSession();
		
		List<Farmacia> farmaciaPorNombre = session.createCriteria(Farmacia.class)
				.add(Restrictions.eq("nombre", nombre))
				.list();
		
		return farmaciaPorNombre;
	}

}
