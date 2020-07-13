package ar.edu.unlam.tallerweb1.repositorios;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import ar.edu.unlam.tallerweb1.clases.Generico;

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

		}
		else
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
	public void guardarFavoritoSeleccionado(Generico favoritoSeleccionado, Usuario usuario) {


		final Session session = sessionFactory.getCurrentSession();
		Propiedad propiedad = new Propiedad();
		Criteria crit = session.createCriteria(Propiedad.class);

		propiedad = (Propiedad)crit.add(Restrictions.eq("id",favoritoSeleccionado.getIdPropiedad())).uniqueResult();

		Criteria cri = session.createCriteria(Usuario.class);

		Usuario usuarioActualizar = (Usuario)cri.add(Restrictions.eq("id", usuario.getId())).uniqueResult();

        usuarioActualizar.addPropiedad(propiedad);

		session.update( usuarioActualizar);
		
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
	public List<Propiedad> propiedadesFavoritasDeUnUsuario(Usuario usuario) {
	
		List<Propiedad> listaPropiedades = new ArrayList<Propiedad>();
		
		final Session session = sessionFactory.getCurrentSession();
    	Criteria cri = session.createCriteria(Usuario.class);
    	    
    	Usuario usr = (Usuario)cri.add(Restrictions.eq("id", usuario.getId())).uniqueResult();
    	
    	listaPropiedades = usr.getPropiedades();
	
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

		tipo1.setDescripcion("Casa");
		tipo2.setDescripcion("Departamento");
		tipo3.setDescripcion("Terreno");

		session.save(tipo1);
		session.save(tipo2);
		session.save(tipo3);


		// INMOBILIARIAS

		Inmobiliaria lezica = new Inmobiliaria();
		Inmobiliaria stella = new Inmobiliaria();
		Inmobiliaria gilges = new Inmobiliaria();

		lezica.setNombreInmobiliaria("Lezica");
		lezica.setEmail("lezica@gmail.com");

		stella.setNombreInmobiliaria("Stella");
		stella.setEmail("stella@gmail.com");

		gilges.setNombreInmobiliaria("Gilges");
		gilges.setEmail("gilges@gmail.com");

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
		prop1.setPrecio(4500000L);
		prop1.setDireccion("Miro 2492");
		prop1.setLocalidad("Luzuriaga");
		prop1.setProvincia("Buenos Aires");
		prop1.setCondicion("Venta");
		prop1.setDetalle("Estrenar");
		prop1.setImagenUrl("monoambiente.jpg");
		prop1.setAmbiente("Monoambiente");
		prop1.setPrecioMin(0L);
		prop1.setPrecioMax(0L);
		prop1.setLatitud(-34.66489521);
		prop1.setLongitud(-58.57834846);
		//prop1.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-27"));

		prop2.setTipoPropiedad(tipo1);
		prop2.setPrecio(40000L);
		prop2.setDireccion("Doctor Ignacio Arieta 1050");
		prop2.setLocalidad("San Justo");
		prop2.setProvincia("Buenos Aires");
		prop2.setCondicion("Alquiler");
		prop2.setDetalle("5 años de antiguedad");
		prop2.setImagenUrl("casa1.jpg");
		prop2.setAmbiente("Dos Ambientes");
		prop2.setPrecioMin(0L);
		prop2.setPrecioMax(0L);
		prop2.setLatitud(-34.66350482);
		prop2.setLongitud(-58.58040333);
		//prop2.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-23"));

		prop3.setTipoPropiedad(tipo1);
		prop3.setPrecio(6000000L);
		prop3.setDireccion("Mariano Santamaria 1147");
		prop3.setLocalidad("Luzuriaga");
		prop3.setProvincia("Buenos Aires");
		prop3.setCondicion("Venta");
		prop3.setDetalle("Refaccionar");
		prop3.setImagenUrl("casa4.jpg");
		prop3.setAmbiente("Dos Ambientes");
		prop3.setPrecioMin(0L);
		prop3.setPrecioMax(0L);
		prop3.setLatitud(-34.6827521);
		prop3.setLongitud(-58.5616369);
		//prop3.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-21"));

		prop4.setTipoPropiedad(tipo1);
		prop4.setPrecio(60000L);
		prop4.setDireccion("Almafuerte 3534");
		prop4.setLocalidad("San Justo");
		prop4.setProvincia("Buenos Aires");
		prop4.setCondicion("Alquiler");
		prop4.setDetalle("Buen Estado");
		prop4.setImagenUrl("casa2.jpg");
		prop4.setAmbiente("Tres Ambientes");
		prop4.setPrecioMin(0L);
		prop4.setPrecioMax(0L);
		prop4.setLatitud(-34.6812321);
		prop4.setLongitud(-58.5573211);
		//prop4.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-20"));

		prop5.setTipoPropiedad(tipo1);
		prop5.setPrecio(6500000L);
		prop5.setDireccion("Peron 1234");
		prop5.setLocalidad("San Justo");
		prop5.setProvincia("Buenos Aires");
		prop5.setCondicion("Venta");
		prop5.setDetalle("Inversion");
		prop5.setImagenUrl("casa5.jpg");
		prop5.setAmbiente("Cuatro Ambientes");
		prop5.setPrecioMin(0L);
		prop5.setPrecioMax(0L);
		prop5.setLatitud(-34.662309);
		prop5.setLongitud(-58.5774059);
		//prop5.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-10"));


		prop6.setTipoPropiedad(tipo1);
		prop6.setPrecio(8000000L);
		prop6.setDireccion("Miro 345");
		prop6.setLocalidad("Luzuriaga");
		prop6.setProvincia("Buenos Aires");
		prop6.setCondicion("Venta");
		prop6.setDetalle("Estrenar");
		prop6.setImagenUrl("casa3.jpg");
		prop6.setAmbiente("Tres Ambientes");
		prop6.setPrecioMin(0L);
		prop6.setPrecioMax(0L);
		prop6.setLatitud(-34.6657519);
		prop6.setLongitud(-58.5813591);
		//prop6.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-01"));

		prop7.setTipoPropiedad(tipo2);
		prop7.setPrecio(4200000L);
		prop7.setDireccion("Av.Rivadavia 18000");
		prop7.setLocalidad("Moron");
		prop7.setProvincia("Buenos Aires");
		prop7.setCondicion("Venta");
		prop7.setDetalle("Estrenar");
		prop7.setImagenUrl("monoambiente.jpg");
		prop7.setAmbiente("Monoambiente");
		prop7.setPrecioMin(0L);
		prop7.setPrecioMax(0L);
		prop7.setLatitud(-34.6494009);
		prop7.setLongitud(-58.6196954);
		//prop7.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-01"));

		prop8.setTipoPropiedad(tipo1);
		prop8.setPrecio(45000L);
		prop8.setDireccion("Brown 345");
		prop8.setLocalidad("Moron");
		prop8.setProvincia("Buenos Aires");
		prop8.setCondicion("Alquiler");
		prop8.setDetalle("Estrenar");
		prop8.setImagenUrl("casa1.jpg");
		prop8.setAmbiente("Dos Ambientes");
		prop8.setPrecioMin(0L);
		prop8.setPrecioMax(0L);
		prop8.setLatitud(-34.6505146);
		prop8.setLongitud(-58.6166245);
		//prop7.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-01"));

		prop9.setTipoPropiedad(tipo1);
		prop9.setPrecio(62000000L);
		prop9.setDireccion("Av. de Mayo 47");
		prop9.setLocalidad("Ramos Mejia");
		prop9.setProvincia("Buenos Aires");
		prop9.setCondicion("Venta");
		prop9.setDetalle("Refaccionar");
		prop9.setImagenUrl("casa4.jpg");
		prop9.setAmbiente("Dos Ambientes");
		prop9.setPrecioMin(0L);
		prop9.setPrecioMax(0L);
		prop9.setLatitud(-34.6419575);
		prop9.setLongitud(-58.5677618);
		//prop9.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-01"));

		prop10.setTipoPropiedad(tipo1);
		prop10.setPrecio(32000000L);
		prop10.setDireccion("Ibarrola 7201");
		prop10.setLocalidad("Liniers");
		prop10.setProvincia("CABA");
		prop10.setCondicion("Venta");
		prop10.setDetalle("Refaccionar");
		prop10.setImagenUrl("casa2.jpg");
		prop10.setAmbiente("Tres Ambientes");
		prop10.setPrecioMin(0L);
		prop10.setPrecioMax(0L);
		prop10.setLatitud(-34.641661);
		prop10.setLongitud(-58.5297261);
		//prop10.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-01"));

		prop11.setTipoPropiedad(tipo1);
		prop11.setPrecio(12000000L);
		prop11.setDireccion("Alianza 200");
		prop11.setLocalidad("Ciudadela");
		prop11.setProvincia("Buenos Aires");
		prop11.setCondicion("Venta");
		prop11.setDetalle("Inversion");
		prop11.setImagenUrl("casa5.jpg");
		prop11.setAmbiente("Cuatro Ambientes");
		prop11.setPrecioMin(0L);
		prop11.setPrecioMax(0L);
		prop11.setLatitud(-34.6424375);
		prop11.setLongitud(-58.540298);
		//prop11.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-01"));

		prop12.setTipoPropiedad(tipo1);
		prop12.setPrecio(29000000L);
		prop12.setDireccion("Peron 900");
		prop12.setLocalidad("Castelar");
		prop12.setProvincia("Buenos Aires");
		prop12.setCondicion("Venta");
		prop12.setDetalle("Estrenar");
		prop12.setImagenUrl("casa3.jpg");
		prop12.setAmbiente("Tres Ambientes");
		prop12.setPrecioMin(0L);
		prop12.setPrecioMax(0L);
		prop12.setLatitud(-34.6597759);
		prop12.setLongitud(-58.5807792);
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
		Publicacion publi7 = new Publicacion();
		Publicacion publi8 = new Publicacion();
		Publicacion publi9 = new Publicacion();
		Publicacion publi10 = new Publicacion();
		Publicacion publi11 = new Publicacion();
		Publicacion publi12 = new Publicacion();
		Publicacion publi13 = new Publicacion();
		Publicacion publi14 = new Publicacion();
		Publicacion publi15 = new Publicacion();
		Publicacion publi16 = new Publicacion();
		Publicacion publi17 = new Publicacion();
		Publicacion publi18 = new Publicacion();
		Publicacion publi19 = new Publicacion();
		Publicacion publi20 = new Publicacion();
		Publicacion publi21 = new Publicacion();
		Publicacion publi22 = new Publicacion();
		Publicacion publi23 = new Publicacion();
		Publicacion publi24 = new Publicacion();

		publi1.setPropiedad(prop1);
		publi1.setInmobiliaria(lezica);
		publi1.setPrecio(45000000L);

		publi2.setPropiedad(prop1);
		publi2.setInmobiliaria(stella);
		publi2.setPrecio(44050000L);

		publi3.setPropiedad(prop1);
		publi3.setInmobiliaria(gilges);
		publi3.setPrecio(42000000L);

		publi4.setPropiedad(prop2);
		publi4.setInmobiliaria(lezica);
		publi4.setPrecio(40000L);

		publi5.setPropiedad(prop2);
		publi5.setInmobiliaria(gilges);
		publi5.setPrecio(42000L);

		publi6.setPropiedad(prop3);
		publi6.setInmobiliaria(lezica);
		publi6.setPrecio(6000000L);
		
		publi7.setPropiedad(prop4);
		publi7.setInmobiliaria(lezica);
		publi7.setPrecio(60000L);

		publi8.setPropiedad(prop4);
		publi8.setInmobiliaria(stella);
		publi8.setPrecio(55000L);

		publi9.setPropiedad(prop4);
		publi9.setInmobiliaria(gilges);
		publi9.setPrecio(61000L);

		publi10.setPropiedad(prop5);
		publi10.setInmobiliaria(gilges);
		publi10.setPrecio(6500000L);

		publi11.setPropiedad(prop5);
		publi11.setInmobiliaria(stella);
		publi11.setPrecio(6460000L);

		publi12.setPropiedad(prop6);
		publi12.setInmobiliaria(lezica);
		publi12.setPrecio(8000000L);
		
		publi13.setPropiedad(prop7);
		publi13.setInmobiliaria(lezica);
		publi1.setPrecio(4200000L);

		publi14.setPropiedad(prop7);
		publi14.setInmobiliaria(stella);
		publi14.setPrecio(4100000L);

		publi15.setPropiedad(prop7);
		publi15.setInmobiliaria(gilges);
		publi15.setPrecio(4205000L);

		publi16.setPropiedad(prop8);
		publi16.setInmobiliaria(lezica);
		publi16.setPrecio(45000L);

		publi17.setPropiedad(prop8);
		publi17.setInmobiliaria(gilges);
		publi17.setPrecio(45900L);

		publi18.setPropiedad(prop9);
		publi18.setInmobiliaria(lezica);
		publi18.setPrecio(62000000L);
		
		publi19.setPropiedad(prop10);
		publi19.setInmobiliaria(lezica);
		publi19.setPrecio(45000000L);

		publi20.setPropiedad(prop10);
		publi20.setInmobiliaria(stella);
		publi20.setPrecio(32500000L);

		publi21.setPropiedad(prop10);
		publi21.setInmobiliaria(gilges);
		publi21.setPrecio(32000000L);

		publi22.setPropiedad(prop11);
		publi22.setInmobiliaria(gilges);
		publi22.setPrecio(12000000L);

		publi23.setPropiedad(prop11);
		publi23.setInmobiliaria(gilges);
		publi23.setPrecio(11990000L);

		publi24.setPropiedad(prop12);
		publi24.setInmobiliaria(stella);
		publi24.setPrecio(29000000L);

		session.save(publi1);
		session.save(publi2);
		session.save(publi3);
		session.save(publi4);
		session.save(publi5);
		session.save(publi6);
		session.save(publi7);
		session.save(publi8);
		session.save(publi9);
		session.save(publi10);
		session.save(publi11);
		session.save(publi12);
		session.save(publi13);
		session.save(publi14);
		session.save(publi15);
		session.save(publi16);
		session.save(publi17);
		session.save(publi18);
		session.save(publi19);
		session.save(publi20);
		session.save(publi21);
		session.save(publi22);
		session.save(publi23);
		session.save(publi24);
	}

	@Override
	public List<Publicacion> listaPublicacion(Propiedad propiedad) {
		final Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(Publicacion.class)
				.createAlias("propiedad", "propiedad")
				.add(Restrictions.eq("propiedad.id", propiedad.getId())).list();
	}

	@Override
	public List<Inmobiliaria> consultarInmobiliarias(List<Publicacion> listaPublicaciones) {
		
		List<Inmobiliaria> listaInmobiliarias = new ArrayList<Inmobiliaria>();
		final Session session = sessionFactory.getCurrentSession();

		Criteria cri = session.createCriteria(Inmobiliaria.class);
		for (Publicacion publicacion : listaPublicaciones) {
			
			Inmobiliaria inmobiliaria = new Inmobiliaria();
			inmobiliaria = (Inmobiliaria) cri.add(Restrictions.eq("id", publicacion.getInmobiliaria().getId())).uniqueResult();

			listaInmobiliarias.add(inmobiliaria);
		}
		
		return listaInmobiliarias;		
	}

	@Override
	public Propiedad detallePropiedad(Propiedad propiedad) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		return (Propiedad) session.createCriteria(Propiedad.class)
				.add(Restrictions.eq("id",  propiedad.getId())).uniqueResult();
	
	}

}
