package ar.edu.unlam.tallerweb1.repositorios;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @PostConstruct
    @Transactional
    public  void crearPropiedad(){

    }
}
