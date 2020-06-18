package ar.edu.unlam.tallerweb1.repositorios;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.clases.Generico;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

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
			}else{
					listaPropiedad = crit.add(Example.create(propiedad)).list();	
				}		

		return listaPropiedad;
	}
	
	
	@Override
	public List<Propiedad> consultarNuevasPropiedades(){
		Calendar fechaActual = Calendar.getInstance();
		int dias = -20;
		fechaActual.add(Calendar.DAY_OF_YEAR, dias);
		Date fechaDesde = fechaActual.getTime();

		final Session session = sessionFactory.getCurrentSession();
		List<Propiedad> listaNuevasPropiedades = session.createCriteria(Propiedad.class)
		.add(Restrictions.gt("fechaPublicada",fechaDesde)).list();
		return listaNuevasPropiedades;

	}

	@Override
	public void guardarFavoritoSeleccionado(Generico favoritoSeleccionado) {	
		
		Usuario usuario = new Usuario();
		usuario.setId(favoritoSeleccionado.getIdUsuario());
		
		final Session session = sessionFactory.getCurrentSession();
		Propiedad propiedad = new Propiedad();
		Criteria crit = session.createCriteria(Propiedad.class);
		
		propiedad = (Propiedad)crit.add(Restrictions.eq("id",favoritoSeleccionado.getIdPropiedad())).uniqueResult();
		
		usuario.addPropiedad(propiedad);
		
		session.save(usuario);
		
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
	public List<Propiedad> propiedadesFavoritasDeUnUsuario(Usuario usuario) {
	
		//List<Propiedad> listaPropiedades = new ArrayList<Propiedad>();
		
		Set<Propiedad> listaPropiedades = new HashSet<>();
		
		final Session session = sessionFactory.getCurrentSession();
    	Criteria cri = session.createCriteria(Usuario.class);
    	    
    	Usuario usr = (Usuario)cri.add(Restrictions.eq("id", usuario.getId())).uniqueResult();
    	
    	listaPropiedades = usr.getPropiedades();
	
	return null;
	}
	
	

}
