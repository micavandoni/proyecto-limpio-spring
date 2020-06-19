package ar.edu.unlam.tallerweb1.persistencia;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;

public class PropiedadesTest extends SpringTest{
	
	private Propiedad prop1, prop2, prop3;
	
	private Session session;
	private List<Propiedad> listaPropiedades;
	private List<Propiedad> listaPropiedadesNuevas;
	private List<Propiedad> listaPropiedadesTipoCasa;
	
	@Before
	public void inicializacionTest() {
		
		prop1=new Propiedad();
		prop2=new Propiedad();
		prop3=new Propiedad();

		session = session();
		listaPropiedades = new ArrayList<Propiedad>();
		listaPropiedadesNuevas = new ArrayList<Propiedad>();
		listaPropiedadesTipoCasa = new ArrayList<Propiedad>();
		
	}
	
	@Test
    @Transactional @Rollback
    //TodasLasPropiedades
    public void testQueListeTodasLasPropiedades() {
		
		//prop1.setTipo("departamento");
		prop1.setPrecio(24000L);
		prop1.setDireccion("Bogado 556");
		prop1.setDetalle("sin detalle");
		prop1.setLocalidad("Rafael Castillo");
		//prop1.setProvincia("BS AS");
		prop1.setCondicion("completa");
		prop1.setImagenUrl("");
		prop1.setAmbiente("1");
		prop1.setPrecioMin(0L);
		prop1.setPrecioMax(0L);
		
		//prop2.setTipo("casa");
		prop2.setPrecio(26000L);
		prop2.setDireccion("Lacar 123");
		prop2.setDetalle("sin detalle");
		prop2.setLocalidad("Rafael Castillo");
		//prop2.setProvincia("BS AS");
		prop2.setCondicion("completa");
		prop2.setImagenUrl("");
		prop2.setAmbiente("3");
		prop2.setPrecioMin(0L);
		prop2.setPrecioMax(0L);
		
		//prop2.setTipo("chalet");
		prop2.setPrecio(23000L);
		prop2.setDireccion("abcr 123");
		prop2.setDetalle("sin detalle");
		prop2.setLocalidad("Moron");
		//prop2.setProvincia("BS AS");
		prop2.setCondicion("sin patio");
		prop2.setImagenUrl("");
		prop2.setAmbiente("4");
		prop2.setPrecioMin(0L);
		prop2.setPrecioMax(0L);
		
		session.save(prop1);
		session.save(prop2);
		session.save(prop3);
		
		listaPropiedades= session.createCriteria(Propiedad.class).list();
		assertThat(listaPropiedades).hasSize(3);
		
		for(Propiedad p : listaPropiedades) {
			p.getDetalle();
			p.getAmbiente();
			p.getDireccion();
		}
	}
	
	@Test
    @Transactional @Rollback
    //TodasLasPropiedades
    public void testQueListePropiedadesConValorMenorA20000() {
		
		//prop1.setTipo("departamento");
		prop1.setPrecio(24000L);
		prop1.setDireccion("Bogado 556");
		prop1.setDetalle("sin detalle");
		prop1.setLocalidad("Rafael Castillo");
		//prop1.setProvincia("BS AS");
		prop1.setCondicion("completa");
		prop1.setImagenUrl("");
		prop1.setAmbiente("1");
		prop1.setPrecioMin(0L);
		prop1.setPrecioMax(0L);
		
		//prop2.setTipo("casa");
		prop2.setPrecio(26000L);
		prop2.setDireccion("Lacar 123");
		prop2.setDetalle("sin detalle");
		prop2.setLocalidad("Rafael Castillo");
		//prop2.setProvincia("BS AS");
		prop2.setCondicion("completa");
		prop2.setImagenUrl("");
		prop2.setAmbiente("3");
		prop2.setPrecioMin(0L);
		prop2.setPrecioMax(0L);
		
		//prop2.setTipo("chalet");
		prop2.setPrecio(23000L);
		prop2.setDireccion("abcr 123");
		prop2.setDetalle("sin detalle");
		prop2.setLocalidad("Moron");
		//prop2.setProvincia("BS AS");
		prop2.setCondicion("sin patio");
		prop2.setImagenUrl("");
		prop2.setAmbiente("4");
		prop2.setPrecioMin(0L);
		prop2.setPrecioMax(0L);
		
		session.save(prop1);
		session.save(prop2);
		session.save(prop3);
		
		listaPropiedades= session.createCriteria(Propiedad.class)
				.add(Restrictions.lt("precio", 20000L))
				.list();
		assertThat(listaPropiedades).hasSize(0);
		
		for (Propiedad p : listaPropiedades) {
		
			assertTrue(20000L<p.getPrecio());
		}
	}
	
	@Test
    @Transactional @Rollback
    //TodasLasPropiedades
    public void testQueTraigaViviendasNuevas() throws ParseException {
		
		//prop1.setTipo("departamento");
		prop1.setPrecio(24000L);
		prop1.setDireccion("Bogado 556");
		prop1.setDetalle("sin detalle");
		prop1.setLocalidad("Rafael Castillo");
		//prop1.setProvincia("BS AS");
		prop1.setCondicion("completa");
		prop1.setImagenUrl("");
		prop1.setAmbiente("1");
		prop1.setPrecioMin(0L);
		prop1.setPrecioMax(0L);
		//prop1.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-28"));

		//prop2.setTipo("casa");
		prop2.setPrecio(26000L);
		prop2.setDireccion("Lacar 123");
		prop2.setDetalle("sin detalle");
		prop2.setLocalidad("Rafael Castillo");
		//prop2.setProvincia("BS AS");
		prop2.setCondicion("completa");
		prop2.setImagenUrl("");
		prop2.setAmbiente("3");
		prop2.setPrecioMin(0L);
		prop2.setPrecioMax(0L);
		//prop2.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-26"));

		//prop3.setTipo("chalet");
		prop3.setPrecio(23000L);
		prop3.setDireccion("abcr 123");
		prop3.setDetalle("sin detalle");
		prop3.setLocalidad("Moron");
		//prop3.setProvincia("BS AS");
		prop3.setCondicion("sin patio");
		prop3.setImagenUrl("");
		prop3.setAmbiente("4");
		prop3.setPrecioMin(0L);
		prop3.setPrecioMax(0L);
		//prop3.setFechaPublicada(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-02"));

		session.save(prop1);
		session.save(prop2);
		session.save(prop3);
		
		Calendar fechaActual = Calendar.getInstance();
		int dias = -15;
		fechaActual.add(Calendar.DAY_OF_YEAR, dias);
		Date fechaDesde = fechaActual.getTime();
	
		listaPropiedadesNuevas= session.createCriteria(Propiedad.class)
				.add(Restrictions.lt("fechaPublicada", fechaDesde))
				.list();
		assertThat(listaPropiedadesNuevas).hasSize(2);
		
	}
	
	@Test
    @Transactional @Rollback
    //TodasLasPropiedades
    public void testQuePropiedadesDeTipoCasa() {
		//prop1.setTipo("departamento");
		prop1.setPrecio(24000L);
		prop1.setDireccion("Bogado 556");
		prop1.setDetalle("sin detalle");
		prop1.setLocalidad("Rafael Castillo");
		//prop1.setProvincia("BS AS");
		prop1.setCondicion("completa");
		prop1.setImagenUrl("");
		prop1.setAmbiente("1");
		prop1.setPrecioMin(0L);
		prop1.setPrecioMax(0L);
		
		//prop2.setTipo("casa");
		prop2.setPrecio(26000L);
		prop2.setDireccion("Lacar 123");
		prop2.setDetalle("sin detalle");
		prop2.setLocalidad("Rafael Castillo");
		//prop2.setProvincia("BS AS");
		prop2.setCondicion("completa");
		prop2.setImagenUrl("");
		prop2.setAmbiente("3");
		prop2.setPrecioMin(0L);
		prop2.setPrecioMax(0L);
		
		//prop3.setTipo("casa");
		prop3.setPrecio(23000L);
		prop3.setDireccion("abcr 123");
		prop3.setDetalle("sin detalle");
		prop3.setLocalidad("Moron");
		//prop3.setProvincia("BS AS");
		prop3.setCondicion("sin patio");
		prop3.setImagenUrl("");
		prop3.setAmbiente("4");
		prop3.setPrecioMin(0L);
		prop3.setPrecioMax(0L);
		
		session.save(prop1);
		session.save(prop2);
		session.save(prop3);
		
		listaPropiedadesTipoCasa= session.createCriteria(Propiedad.class)
				.add(Restrictions.eq("tipo","casa"))
				.list();
				
		assertThat(listaPropiedadesTipoCasa).hasSize(2);
		
	//	for(Propiedad p: listaPropiedadesTipoCasa) {
	//		Assert.assertEquals(p.setTipoPropiedad(),"casa");
	//	}
		
	}
}
