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
		lezica.setEmail("householdstw1@gmail.com");

		stella.setNombreInmobiliaria("Stella");
		stella.setEmail("householdstw1@gmail.com");

		gilges.setNombreInmobiliaria("Gilges");
		gilges.setEmail("householdstw1@gmail.com");

		session.save(lezica);
		session.save(stella);
		session.save(gilges);
		List<Inmobiliaria> inmobiliarias = new ArrayList<Inmobiliaria>();
		inmobiliarias.add(lezica);
		inmobiliarias.add(stella);
		inmobiliarias.add(gilges);

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
		prop1.setPrecio(7500000L);
		prop1.setTitulo("Departamento en Venta");
		prop1.setDireccion("ENTRE RIOS 3567");
		prop1.setLocalidad("San Justo");
		prop1.setProvincia("Buenos Aires");
		prop1.setCondicion("Venta");
		prop1.setDetalle("Es un departamento monoambiente a estrenar que cuenta con cocina equipada con muebles bajo mesada y alacenas, amplio ambiente divisible , baño y balcón al contrafrente. Se entrega con artefacto de cocina y aire acondicionado por equipos splits instalados.");
		prop1.setImagenUrl("mono1.jpg");
		prop1.setImagenUrl2("mono2.jpg");
		prop1.setImagenUrl3("mono3.jpg");
		prop1.setImagenUrl4("mono4.jpg");
		prop1.setAmbiente("Monoambiente");
		prop1.setPrecioMin(0L);
		prop1.setPrecioMax(0L);
		prop1.setLatitud(-34.7229893877551);
		prop1.setLongitud(-58.5816926122449);
		//prop1.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-27"));
//		prop1.setInmobiliaria(lezica);
		prop1.setInmobiliarias(inmobiliarias);


		prop2.setTipoPropiedad(tipo2);
		prop2.setPrecio(6500000L);
		prop2.setTitulo("Departamento en Venta");
		prop2.setDireccion("Federico Garcia Lorca 350");
		prop2.setLocalidad("Caballito");
		prop2.setProvincia("Ciudad de Buenos Aires");
		prop2.setCondicion("Venta");
		prop2.setDetalle("El departamento actualmente esta abierto el 2do dormitorio por ende tiene 1 dormitorio y un living amplio con estar y comedor. 2 Baños completos, Dormitorio en suite. Cocina americana . Cortinas electricas");
		prop2.setImagenUrl("20.jpg");
		prop2.setImagenUrl2("21.jpg");
		prop2.setImagenUrl3("22.jpg");
		prop2.setImagenUrl4("23.jpg");
		prop2.setAmbiente("Dos ambientes");
		prop2.setPrecioMin(0L);
		prop2.setPrecioMax(0L);
		prop2.setLatitud(-34.618094);
		prop2.setLongitud(-58.445440);
		//prop20.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-07-14"));
		//prop20.setInmobiliaria(lezica);
		prop2.setInmobiliarias(inmobiliarias);

		prop3.setTipoPropiedad(tipo1);
		prop3.setPrecio(8000000L);
		prop3.setTitulo("Casa en Venta");
		prop3.setDireccion("Gral. Gelly y Obes 459");
		prop3.setLocalidad("Ramos Mejia");
		prop3.setProvincia("Buenos Aires");
		prop3.setCondicion("Venta");
		prop3.setDetalle("CASA A ESTRENAR 4 AMBIENTES EN VENTA EN RAMOS MEJÍA - PRIMERA CATEGORÍA - 3 DORMITORIOS  - LIVING COMEDOR - COCINA EQUIPADA - 2 BAÑOS COMPLETOS - GARAGE - PATIO C/ PARRILLA - LAVADERO - TERRAZA - ALTILLO - PISOS DE PORCELANATO ");
		prop3.setImagenUrl("30.jpg");
		prop3.setImagenUrl2("31.jpg");
		prop3.setImagenUrl3("32.jpg");
		prop3.setImagenUrl4("33.jpg");
		prop3.setAmbiente("Cuatro Ambientes");
		prop3.setPrecioMin(0L);
		prop3.setPrecioMax(0L);
		prop3.setLatitud(-34.636273);
		prop3.setLongitud(-58.573478);
		//prop30.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-07-14"));
		//prop30.setInmobiliaria(lezica);
		prop3.setInmobiliarias(inmobiliarias);

		prop4.setTipoPropiedad(tipo1);
		prop4.setPrecio(30000L);
		prop4.setTitulo("Dpto en alquiler");
		prop4.setDireccion("Av. Pueyrredón 1739");
		prop4.setLocalidad("Barrio Norte");
		prop4.setProvincia("Ciudad de Buenos Aires");
		prop4.setCondicion("Alquiler");
		prop4.setDetalle("El departamento actualmente esta abierto el 2do dormitorio por ende tiene 1 dormitorio y un living amplio con estar y comedor. 2 Baños completos, Dormitorio en suite. Cocina americana . Cortinas electricas");
		prop4.setImagenUrl("40.jpg");
		prop4.setImagenUrl2("41.jpg");
		prop4.setImagenUrl3("42.jpg");
		prop4.setImagenUrl4("43.jpg");
		prop4.setAmbiente("Tres Ambientes");
		prop4.setPrecioMin(0L);
		prop4.setPrecioMax(0L);
		prop4.setLatitud(-34.590768);
		prop4.setLongitud(-58.400646);
		//prop40.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-07-14"));
		//prop40.setInmobiliaria(lezica);
		prop4.setInmobiliarias(inmobiliarias);

		prop5.setTipoPropiedad(tipo1);
		prop5.setPrecio(50000L);
		prop5.setTitulo("casa en alquiler");
		prop5.setDireccion("Sgto Cabral 2750");
		prop5.setLocalidad("Canning");
		prop5.setProvincia("Buenos Aires");
		prop5.setCondicion("alquiler");
		prop5.setDetalle("CASA A ESTRENAR 4 AMBIENTES EN VENTA EN CANNING - 3 DORMITORIOS  - LIVING COMEDOR - COCINA EQUIPADA - 2 BAÑOS COMPLETOS - GARAGE - PATIO C/ PARRILLA - LAVADERO - TERRAZA - ALTILLO - PISOS DE PORCELANATO ");
		prop5.setImagenUrl("50.jpg");
		prop5.setImagenUrl2("51.jpg");
		prop5.setImagenUrl3("52.jpg");
		prop5.setImagenUrl4("53.jpg");
		prop5.setAmbiente("Cuatro Ambientes");
		prop5.setPrecioMin(0L);
		prop5.setPrecioMax(0L);
		prop5.setLatitud(-34.862915);
		prop5.setLongitud(-58.497543);
		//prop50.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-07-14"));
		//prop50.setInmobiliaria(lezica);
		prop5.setInmobiliarias(inmobiliarias);

		prop6.setTipoPropiedad(tipo1);
		prop6.setPrecio(7000000L);
		prop6.setTitulo("casa en venta");
		prop6.setDireccion("Ruta25 km 7,5");
		prop6.setLocalidad("Francisco Alvarez Moreno");
		prop6.setProvincia("Buenos Aires");
		prop6.setCondicion("Venta");
		prop6.setDetalle("CHALET 5 AMBIENTES- 4 DORMITORIOS- 3 BAÑOS COMPLETOS- LIVING COMEDOR- COMEDOR DIARIO- COCINA EQUIPADA - LAVADERO CUBIERTO- JARDIN DE INVIERNO- GALERIA- QUINCHO - TERRAZA- PARQUE- PISCINA");
		prop6.setImagenUrl("60.jpg");
		prop6.setImagenUrl2("61.jpg");
		prop6.setImagenUrl3("62.jpg");
		prop6.setImagenUrl4("63.jpg");
		prop6.setAmbiente("Cinco Ambientes");
		prop6.setPrecioMin(0L);
		prop6.setPrecioMax(0L);
		prop6.setLatitud(-34.584308);
		prop6.setLongitud(-58.838698);
		//prop60.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-07-14"));
		//prop60.setInmobiliaria(lezica);
		prop6.setInmobiliarias(inmobiliarias);

		prop7.setTipoPropiedad(tipo1);
		prop7.setPrecio(4000000L);
		prop7.setTitulo("casa en venta");
		prop7.setDireccion("De los Reseros 1699");
		prop7.setLocalidad("Ituzaingo");
		prop7.setProvincia("Buenos Aires");
		prop7.setCondicion("venta");
		prop7.setDetalle("CASA 2 AMBIENTES- 4 DORMITORIOS- 3 BAÑOS COMPLETOS- LIVING COMEDOR- COMEDOR DIARIO- COCINA EQUIPADA - LAVADERO CUBIERTO- JARDIN DE INVIERNO- GALERIA- QUINCHO - TERRAZA- PARQUE- PISCINA");
		prop7.setImagenUrl("70.jpg");
		prop7.setImagenUrl2("71.jpg");
		prop7.setImagenUrl3("72.jpg");
		prop7.setImagenUrl4("73.jpg");
		prop7.setAmbiente("Dos Ambientes");
		prop7.setPrecioMin(0L);
		prop7.setPrecioMax(0L);
		prop7.setLatitud(-34.625129);
		prop7.setLongitud(-58.690868);
		//prop70.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-07-14"));
		//prop70.setInmobiliaria(lezica);
		prop7.setInmobiliarias(inmobiliarias);

		prop8.setTipoPropiedad(tipo2);
		prop8.setPrecio(8000000L);
		prop8.setTitulo("casa en venta");
		prop8.setDireccion("De los Reseros 1699");
		prop8.setLocalidad("Ituzaingo");
		prop8.setProvincia("Buenos Aires");
		prop8.setCondicion("Venta");
		prop8.setDetalle("CHALET 4 AMBIENTES- 4 DORMITORIOS- 3 BAÑOS COMPLETOS- LIVING COMEDOR- COMEDOR DIARIO- COCINA EQUIPADA - LAVADERO CUBIERTO- JARDIN DE INVIERNO- GALERIA- QUINCHO - TERRAZA- PARQUE- PISCINA\"");
		prop8.setImagenUrl("80.jpg");
		prop8.setImagenUrl2("81.jpg");
		prop8.setImagenUrl3("82.jpg");
		prop8.setImagenUrl4("83.jpg");
		prop8.setAmbiente("4 Ambientes");
		prop8.setPrecioMin(0L);
		prop8.setPrecioMax(0L);
		prop8.setLatitud(-34.625129);
		prop8.setLongitud(-58.690868);
		//prop80.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-07-14"));
		//prop80.setInmobiliaria(lezica);
		prop8.setInmobiliarias(inmobiliarias);

		prop9.setTipoPropiedad(tipo2);
		prop9.setPrecio(20000L);
		prop9.setTitulo("Depto en alquiler");
		prop9.setDireccion("Cazadores 256");
		prop9.setLocalidad("Haedo");
		prop9.setProvincia("Buenos Aires");
		prop9.setCondicion("Alquiler");
		prop9.setDetalle("El departamento actualmente esta abierto el 2do dormitorio por ende tiene 1 dormitorio y un living amplio con estar y comedor. 2 Baños completos, Dormitorio en suite. Cocina americana . Cortinas electricas");
		prop9.setImagenUrl("90.jpg");
		prop9.setImagenUrl2("91.jpg");
		prop9.setImagenUrl3("92.jpg");
		prop9.setImagenUrl4("93.jpg");
		prop9.setAmbiente("Dos Ambientes");
		prop9.setPrecioMin(0L);
		prop9.setPrecioMax(0L);
		prop9.setLatitud(-34.647293);
		prop9.setLongitud(-58.591779);
		//prop90.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-07-14"));
		//prop90.setInmobiliaria(lezica);
		prop9.setInmobiliarias(inmobiliarias);

		prop10.setTipoPropiedad(tipo2);
		prop10.setPrecio(12000000L);
		prop10.setTitulo("Depto en venta");
		prop10.setDireccion("Juana Manso 1780");
		prop10.setLocalidad("Puerto Madero");
		prop10.setProvincia("Ciudad de Buenos Aires");
		prop10.setCondicion("Venta");
		prop10.setDetalle("El departamento actualmente esta abierto el 2do dormitorio por ende tiene 1 dormitorio y un living amplio con estar y comedor. 2 Baños completos, Dormitorio en suite. Cocina americana . Cortinas electricas");
		prop10.setImagenUrl("100.jpg");
		prop10.setImagenUrl2("101.jpg");
		prop10.setImagenUrl3("102.jpg");
		prop10.setImagenUrl4("103.jpg");
		prop10.setAmbiente("cuatro Ambientes");
		prop10.setPrecioMin(0L);
		prop10.setPrecioMax(0L);
		prop10.setLatitud(-34.618399);
		prop10.setLongitud(-58.361591);
		//prop100.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-07-14"));
		//prop100.setInmobiliaria(lezica);
		prop10.setInmobiliarias(inmobiliarias);


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


		publi1.setPropiedad(prop1);
		publi1.setInmobiliaria(lezica);
		publi1.setPrecio(7500000L);

		publi2.setPropiedad(prop1);
		publi2.setInmobiliaria(stella);
		publi2.setPrecio(7700000L);

		publi3.setPropiedad(prop1);
		publi3.setInmobiliaria(gilges);
		publi3.setPrecio(7600000L);

		publi4.setPropiedad(prop2);
		publi4.setInmobiliaria(lezica);
		publi4.setPrecio(6400000L);

		publi5.setPropiedad(prop2);
		publi5.setInmobiliaria(gilges);
		publi5.setPrecio(6550000L);

		publi6.setPropiedad(prop3);
		publi6.setInmobiliaria(lezica);
		publi6.setPrecio(8000000L);
		
		publi7.setPropiedad(prop4);
		publi7.setInmobiliaria(lezica);
		publi7.setPrecio(30000L);

		publi8.setPropiedad(prop4);
		publi8.setInmobiliaria(stella);
		publi8.setPrecio(35000L);

		publi9.setPropiedad(prop4);
		publi9.setInmobiliaria(gilges);
		publi9.setPrecio(32000L);

		publi10.setPropiedad(prop5);
		publi10.setInmobiliaria(gilges);
		publi10.setPrecio(50000L);

		publi11.setPropiedad(prop5);
		publi11.setInmobiliaria(stella);
		publi11.setPrecio(49000L);

		publi12.setPropiedad(prop6);
		publi12.setInmobiliaria(lezica);
		publi12.setPrecio(7000000L);
		
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
		publi16.setPrecio(8100000L);

		publi17.setPropiedad(prop8);
		publi17.setInmobiliaria(gilges);
		publi17.setPrecio(8150000L);

		publi18.setPropiedad(prop9);
		publi18.setInmobiliaria(lezica);
		publi18.setPrecio(4000000L);
		
		publi19.setPropiedad(prop10);
		publi19.setInmobiliaria(lezica);
		publi19.setPrecio(12000000L);

		publi20.setPropiedad(prop10);
		publi20.setInmobiliaria(stella);
		publi20.setPrecio(11900000L);

		publi21.setPropiedad(prop10);
		publi21.setInmobiliaria(gilges);
		publi21.setPrecio(12500000L);


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
	
	@Override
	public Propiedad ObtenerPropiedadPorId(Long id) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		return (Propiedad) session.createCriteria(Propiedad.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
	
	}
	

}
