package ar.edu.unlam.tallerweb1.persistencia;
import org.junit.Test;


import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPropiedad;
import ar.edu.unlam.tallerweb1.servicios.ServicioPropiedadImpl;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class ServicioPropiedadTest extends SpringTest{

	
	private ServicioPropiedadImpl servPropiedad;
	 
	@Test
	public void buscarPropiedadesPorCondicion() {
		
		// preparacion
		Propiedad propiedad = new Propiedad();
		propiedad.setCondicion("venta");
		RepositorioPropiedad repositorioPropiedad = mock(RepositorioPropiedad.class);
		when(repositorioPropiedad.consultarPropiedadFilter(propiedad)).thenReturn(null);
		
		
		servPropiedad = new ServicioPropiedadImpl(repositorioPropiedad);
		
		//ejecucion
		servPropiedad.consultarPropiedadFilter(propiedad);
		
		//validacion
		verify(repositorioPropiedad, times(1)).consultarPropiedadFilter(propiedad);
	}
	
	@Test
	public void buscarPropiedadesPorPrecio() {
		
		// preparacion
		Propiedad propiedadFiltro = new Propiedad();
		Propiedad proRes = new Propiedad();
		propiedadFiltro.setPrecioMin(1L);
		propiedadFiltro.setPrecioMax(15L);
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		
		proRes.setTipo("departamento");
		proRes.setPrecio(100L);
		proRes.setDireccion("arieta 2900");
		proRes.setDetalle("sin detalle");
		proRes.setLocalidad("San Justo");
		proRes.setProvincia("BS AS");
		proRes.setCondicion("venta");
		proRes.setAmbiente("4");

		propiedades.add(proRes);
		
		RepositorioPropiedad repositorioPropiedad = mock(RepositorioPropiedad.class);
		when(repositorioPropiedad.consultarPropiedadFilter(propiedadFiltro)).thenReturn(propiedades);
		
		
		servPropiedad = new ServicioPropiedadImpl(repositorioPropiedad);
		
		//ejecucion
		servPropiedad.consultarPropiedadFilter(propiedadFiltro);
		
		//validacion
		verify(repositorioPropiedad, times(1)).consultarPropiedadFilter(propiedadFiltro);
		
	}

}
