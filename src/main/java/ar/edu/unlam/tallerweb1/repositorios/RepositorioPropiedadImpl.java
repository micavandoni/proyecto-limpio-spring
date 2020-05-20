package ar.edu.unlam.tallerweb1.repositorios;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    @PostConstruct
    @Transactional
    public  void crearPropiedad(){

//        final Session session = sessionFactory.openSession();
//
//
//        Propiedad propiedad1 = new Propiedad();
//
//        propiedad1.setCondicion("venta");
//        propiedad1.setPrecio(5000L);
//        propiedad1.setProvincia("buenos aires");
//        propiedad1.setLocalidad("san justo");
//        propiedad1.setDireccion("arieta");
//        propiedad1.setDetalle("detalle 1");
//        propiedad1.setImagenUrl("url");
//
//        Propiedad propiedad2 = new Propiedad();
//
//        propiedad2.setCondicion("venta");
//        propiedad2.setPrecio(5000L);
//        propiedad2.setProvincia("buenos aires");
//        propiedad2.setLocalidad("san justo");
//        propiedad2.setDireccion("arieta");
//        propiedad2.setDetalle("detalle 1");
//        propiedad2.setImagenUrl("url");
//
//        session.save (propiedad1);
//        session.save (propiedad2);
    }
}
