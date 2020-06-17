package ar.edu.unlam.tallerweb1.repositorios;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ar.edu.unlam.tallerweb1.modelo.*;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
									.createAlias("tipoPropiedad","tipoPropiedad")
									.add(Restrictions.eq("tipoPropiedad.descripcion", "casa"))
					                .setProjection(Projections.rowCount())
					                .uniqueResult();		
		
		Number departamentos = (Number) session.createCriteria(Propiedad.class)
											.createAlias("tipoPropiedad","tipoPropiedad")
											.add(Restrictions.eq("tipoPropiedad.descripcion", "departamento"))
											.setProjection(Projections.rowCount())
											.uniqueResult();
		Number terrenos = (Number) session.createCriteria(Propiedad.class)
											.createAlias("tipoPropiedad","tipoPropiedad")
											.add(Restrictions.eq("tipoPropiedad.descripcion", "terreno"))
											.setProjection(Projections.rowCount())
											.uniqueResult();
		Number inmobiliarias = (Number) session.createCriteria(Inmobiliaria.class)
											.setProjection(Projections.rowCount())
											.uniqueResult();
		
		listaCounts.add(casas);
		listaCounts.add(departamentos);
		listaCounts.add(terrenos);
		listaCounts.add(inmobiliarias);


		return listaCounts;
	}

	@Override
	public List<Propiedad> propiedadesFavoritasDeUnUsuario(List<Favorito> listaFavoritos) {
		
		List<Propiedad> listaPropiedades = new ArrayList<Propiedad>();
		
		final Session session = sessionFactory.getCurrentSession();
    	Criteria cri = session.createCriteria(Propiedad.class);
    	
		for (Favorito favorito : listaFavoritos) {
			Propiedad propiedad = new Propiedad();
			propiedad = (Propiedad)cri.add(Restrictions.eq("id", favorito.getIdPropiedad())).uniqueResult();
			listaPropiedades.add(propiedad);
		}	
	
		
		return listaPropiedades;
	}

	@Override
//	@PostConstruct
	@Transactional
	public void crearEventos() {


		final Session session = sessionFactory.openSession();

		//USUARIOS

		Usuario usuario1 = new Usuario();
		Usuario usuario2 = new Usuario();
		Usuario usuario3 = new Usuario();

		usuario1.setNombre("Pepe");
		usuario1.setEmail("pepe@gmail.com");
		usuario1.setPassword("pepito");
		usuario1.setRol("ADMIN");

		usuario2.setNombre("Juan");
		usuario2.setEmail("juan@gmail.com");
		usuario2.setPassword("pepito");
		usuario2.setRol("USER");

		usuario3.setNombre("Julia");
		usuario3.setEmail("julia@gmail.com");
		usuario3.setPassword("pepito");
		usuario3.setRol("USER");

		session.save(usuario1);
		session.save(usuario2);
		session.save(usuario3);

		//TIPOS DE PROPIEDAD

		TipoPropiedad tipo1 = new TipoPropiedad();
		TipoPropiedad tipo2 = new TipoPropiedad();
		TipoPropiedad tipo3 = new TipoPropiedad();

		tipo1.setDescripcion("casa");
		tipo2.setDescripcion("departamento");
		tipo3.setDescripcion("terreno");

		session.save(tipo1);
		session.save(tipo2);
		session.save(tipo3);


		// INMOBILIARIAS

		Inmobiliaria lezica = new Inmobiliaria();
		Inmobiliaria stella = new Inmobiliaria();
		Inmobiliaria gilges = new Inmobiliaria();

		lezica.setNombreInmobiliaria("Lezica");
		lezica.setEmail("lezica@email.com");

		stella.setNombreInmobiliaria("Stella");
		stella.setEmail("stella@email.com");

		gilges.setNombreInmobiliaria("Gilges");
		gilges.setEmail("gilges@email.com");

		session.save(lezica);
		session.save(stella);
		session.save(gilges);

		//PROPIEDADES

		Propiedad prop1 = new Propiedad();
		Propiedad prop2 = new Propiedad();
		Propiedad prop3 = new Propiedad();
		Propiedad prop4 = new Propiedad();
		Propiedad prop5 = new Propiedad();
		Propiedad prop6 = new Propiedad();
		Propiedad prop7 = new Propiedad();
		Propiedad prop8 = new Propiedad();
		Propiedad prop9 = new Propiedad();
		Propiedad prop10 = new Propiedad();
		Propiedad prop11 = new Propiedad();
		Propiedad prop12 = new Propiedad();



		prop1.setTipoPropiedad(tipo2);
		prop1.setPrecio(20L);
		prop1.setDireccion("Miro 222");
		prop1.setLocalidad("luzuriaga");
		prop1.setProvincia("buenos aires");
		prop1.setCondicion("venta");
		prop1.setDetalle("estrenar");
		prop1.setImagenUrl("monoambiente.jpg");
		prop1.setAmbiente("monoambiente");
		prop1.setPrecioMin(0L);
		prop1.setPrecioMax(0L);
		//prop1.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-27"));

		prop2.setTipoPropiedad(tipo1);
		prop2.setPrecio(15L);
		prop2.setDireccion("arieta 100");
		prop2.setLocalidad("san justo");
		prop2.setProvincia("buenos aires");
		prop2.setCondicion("alquiler");
		prop2.setDetalle("alquiler");
		prop2.setImagenUrl("casa1.jpg");
		prop2.setAmbiente("dos ambientes");
		prop2.setPrecioMin(0L);
		prop2.setPrecioMax(0L);
		//prop2.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-23"));

		prop3.setTipoPropiedad(tipo1);
		prop3.setPrecio(12L);
		prop3.setDireccion("santander 44");
		prop3.setLocalidad("luzuriaga");
		prop3.setProvincia("buenos aires");
		prop3.setCondicion("venta");
		prop3.setDetalle("refaccionar");
		prop3.setImagenUrl("casa4.jpg");
		prop3.setAmbiente("dos ambientes");
		prop3.setPrecioMin(0L);
		prop3.setPrecioMax(0L);
		//prop3.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-21"));

		prop4.setTipoPropiedad(tipo1);
		prop4.setPrecio(22L);
		prop4.setDireccion("almafuerte 18");
		prop4.setLocalidad("san justo");
		prop4.setProvincia("buenos aires");
		prop4.setCondicion("alquiler");
		prop4.setDetalle("buen estado");
		prop4.setImagenUrl("casa2.jpg");
		prop4.setAmbiente("tres ambientes");
		prop4.setPrecioMin(0L);
		prop4.setPrecioMax(0L);
		//prop4.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-20"));

		prop5.setTipoPropiedad(tipo1);
		prop5.setPrecio(36L);
		prop5.setDireccion("peron 1234");
		prop5.setLocalidad("san justo");
		prop5.setProvincia("buenos aires");
		prop5.setCondicion("venta");
		prop5.setDetalle("inversion");
		prop5.setImagenUrl("casa5.jpg");
		prop5.setAmbiente("cuatro ambientes");
		prop5.setPrecioMin(0L);
		prop5.setPrecioMax(0L);
		//prop5.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-10"));


		prop6.setTipoPropiedad(tipo1);
		prop6.setPrecio(50L);
		prop6.setDireccion("Miro 345");
		prop6.setLocalidad("luzuriaga");
		prop6.setProvincia("buenos aires");
		prop6.setCondicion("venta");
		prop6.setDetalle("estrenar");
		prop6.setImagenUrl("casa3.jpg");
		prop6.setAmbiente("tres ambientes");
		prop6.setPrecioMin(0L);
		prop6.setPrecioMax(0L);
		//prop6.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-01"));

		prop7.setTipoPropiedad(tipo2);
		prop7.setPrecio(20L);
		prop7.setDireccion("Av.Rivadavia 18000");
		prop7.setLocalidad("Moron");
		prop7.setProvincia("buenos aires");
		prop7.setCondicion("venta");
		prop7.setDetalle("estrenar");
		prop7.setImagenUrl("monoambiente.jpg");
		prop7.setAmbiente("tmonoambiente");
		prop7.setPrecioMin(0L);
		prop7.setPrecioMax(0L);
		//prop7.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-01"));

		prop8.setTipoPropiedad(tipo1);
		prop8.setPrecio(20L);
		prop8.setDireccion("Brown 345");
		prop8.setLocalidad("Moron");
		prop8.setProvincia("buenos aires");
		prop8.setCondicion("alquiler");
		prop8.setDetalle("estrenar");
		prop8.setImagenUrl("casa1.jpg");
		prop8.setAmbiente("dos ambientes");
		prop8.setPrecioMin(0L);
		prop8.setPrecioMax(0L);
		//prop7.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-01"));

		prop9.setTipoPropiedad(tipo1);
		prop9.setPrecio(12L);
		prop9.setDireccion("San Juan 321");
		prop9.setLocalidad("Ramos Mejia");
		prop9.setProvincia("buenos aires");
		prop9.setCondicion("venta");
		prop9.setDetalle("refaccionar");
		prop9.setImagenUrl("casa4.jpg");
		prop9.setAmbiente("dos ambientes");
		prop9.setPrecioMin(0L);
		prop9.setPrecioMax(0L);
		//prop9.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-01"));

		prop10.setTipoPropiedad(tipo1);
		prop10.setPrecio(32L);
		prop10.setDireccion("Quiroga 81");
		prop10.setLocalidad("Liniers");
		prop10.setProvincia("CABA");
		prop10.setCondicion("venta");
		prop10.setDetalle("refaccionar");
		prop10.setImagenUrl("casa2.jpg");
		prop10.setAmbiente("tres ambientes");
		prop10.setPrecioMin(0L);
		prop10.setPrecioMax(0L);
		//prop10.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-01"));

		prop11.setTipoPropiedad(tipo1);
		prop11.setPrecio(36L);
		prop11.setDireccion("Dorrego 765");
		prop11.setLocalidad("Ciudadela");
		prop11.setProvincia("buenos aires");
		prop11.setCondicion("venta");
		prop11.setDetalle("inversion");
		prop11.setImagenUrl("casa5.jpg");
		prop11.setAmbiente("cuatro ambientes");
		prop11.setPrecioMin(0L);
		prop11.setPrecioMax(0L);
		//prop11.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-01"));

		prop12.setTipoPropiedad(tipo1);
		prop12.setPrecio(49L);
		prop12.setDireccion("Peron 900");
		prop12.setLocalidad("Castelar");
		prop12.setProvincia("buenos aires");
		prop12.setCondicion("venta");
		prop12.setDetalle("estrenar");
		prop12.setImagenUrl("casa3.jpg");
		prop12.setAmbiente("tres ambientes");
		prop12.setPrecioMin(0L);
		prop12.setPrecioMax(0L);
		//prop12.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-01"));

		session.save(prop1);
		session.save(prop2);
		session.save(prop3);
		session.save(prop4);
		session.save(prop5);
		session.save(prop6);
		session.save(prop7);
		session.save(prop8);
		session.save(prop9);
		session.save(prop10);
		session.save(prop11);
		session.save(prop12);

		//PUBLICACIONES

		Publicacion publi1 = new Publicacion();
		Publicacion publi2 = new Publicacion();
		Publicacion publi3 = new Publicacion();
		Publicacion publi4 = new Publicacion();
		Publicacion publi5 = new Publicacion();
		Publicacion publi6 = new Publicacion();

		publi1.setPropiedad(prop1);
		publi1.setInmobiliaria(lezica);

		publi2.setPropiedad(prop1);
		publi2.setInmobiliaria(stella);

		publi3.setPropiedad(prop1);
		publi3.setInmobiliaria(gilges);

		publi4.setPropiedad(prop2);
		publi4.setInmobiliaria(lezica);

		publi5.setPropiedad(prop2);
		publi5.setInmobiliaria(gilges);

		publi6.setPropiedad(prop3);
		publi6.setInmobiliaria(lezica);

		session.save(publi1);
		session.save(publi2);
		session.save(publi3);
		session.save(publi4);
		session.save(publi5);
		session.save(publi6);
	}

}
