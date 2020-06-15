package ar.edu.unlam.tallerweb1.persistencia;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPropiedadImpl;

public class RepositorioPropiedadTest extends SpringTest{

	@Inject
	private RepositorioPropiedadImpl repoPropiedad;
	
	@Test
	@Transactional
	@Rollback(true)
	public void  buscarTodasLasPropiedades() {
		
		//Preparacion
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		
		//Ejecucion
		propiedades = repoPropiedad.consultarPropiedad();
		
		//Comprobacion
		System.out.println("tamaño lista de propiedades: " + propiedades.size());
		assertFalse(propiedades.isEmpty()); 
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void  buscarPropiedadesPorPrecioDesdeHasta() {
		
		//Preparacion
		Propiedad propiedad = new Propiedad();
		propiedad.setPrecioMin(1L);
		propiedad.setPrecioMax(15L);
		
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		
		//Ejecucion
		propiedades = repoPropiedad.consultarPropiedadFilter(propiedad);
		
		//Comprobacion
		System.out.println("Lista de propiedades filtradas por precio cant Propiedades encontradas ="+ propiedades.size());
		assertFalse(propiedades.isEmpty()); 
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void  buscarPropiedadesPorPrecioMayorA() {
		
		//Preparacion
		Propiedad propiedad = new Propiedad();
		propiedad.setPrecioMin(20L);
		
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		
		//Ejecucion
		propiedades = repoPropiedad.consultarPropiedadFilter(propiedad);
		
		//Comprobacion
		System.out.println("Lista de propiedades filtradas por precio mayor a="+ propiedades.size());
		assertFalse(propiedades.isEmpty()); 
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void  buscarPropiedadesPorPrecioMenorA() {
		
		//Preparacion
		Propiedad propiedad = new Propiedad();
		propiedad.setPrecioMax(1L);
		
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		
		//Ejecucion
		propiedades = repoPropiedad.consultarPropiedadFilter(propiedad);
		
		//Comprobacion
		System.out.println("Lista de propiedades filtradas por precio menor  a="+ propiedades.size());
		assertFalse(propiedades.isEmpty()); 
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void  buscarPropiedadesPorCondicionVenta() {
		
		//Preparacion
		Propiedad propiedad = new Propiedad();
		propiedad.setCondicion("venta");
		
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		
		//Ejecucion
		propiedades = repoPropiedad.consultarPropiedadFilter(propiedad);
		
		//Comprobacion
		System.out.println("Lista de propiedades filtradas por condicion venta ="+ propiedades.size());
		assertFalse(propiedades.isEmpty()); 
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void  buscarPropiedadesPorCondicionAlquiler() {
		
		//Preparacion
		Propiedad propiedad = new Propiedad();
		propiedad.setCondicion("alquiler");
		
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		
		//Ejecucion
		propiedades = repoPropiedad.consultarPropiedadFilter(propiedad);
		
		//Comprobacion
		System.out.println("Lista de propiedades filtradas por condicion venta ="+ propiedades.size());
		assertFalse(propiedades.isEmpty()); 
	}
}
