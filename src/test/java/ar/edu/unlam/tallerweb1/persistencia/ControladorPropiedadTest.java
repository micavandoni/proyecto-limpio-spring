package ar.edu.unlam.tallerweb1.persistencia;
import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorPropiedad;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.servicios.ServicioPropiedad;
import static org.mockito.Mockito.*;

public class ControladorPropiedadTest{
	
    @Test
    public void consultarTodasLasPropiedades(){
        // preparacion
    	
		ServicioPropiedad servicioPropiedad = mock(ServicioPropiedad.class);
		ControladorPropiedad controladorPropiedad = new ControladorPropiedad(servicioPropiedad);
		
        Propiedad propiedad = new Propiedad();

        // ejecucion
        
        final ModelAndView modelAndView = controladorPropiedad.propiedades();

        // validacion
        assertThat(modelAndView.getViewName()).isEqualTo("propiedad");
    }
	
    @Test
    public void consultarFiltroPropiedades(){
        // preparacion
    	
		ServicioPropiedad servicioPropiedad = mock(ServicioPropiedad.class);
		ControladorPropiedad controladorPropiedad = new ControladorPropiedad(servicioPropiedad);
		
        Propiedad propiedad = new Propiedad();

        // ejecucion
        
        final ModelAndView modelAndView = controladorPropiedad.filtraPropiedades(propiedad, null);

        // validacion
        assertThat(modelAndView.getViewName()).isEqualTo("propiedad");
    }
    
	@Test(expected = Exception.class)
	public void validarCamposPropiedadFallaPrecio() throws Exception{
		
		ServicioPropiedad servicioPropiedad = mock(ServicioPropiedad.class);
				
		ControladorPropiedad controladorPropiedad = new ControladorPropiedad(servicioPropiedad);
		
		// preparacion
		Propiedad propiedad = new Propiedad();
		propiedad.setCondicion("venta");
		propiedad.setAmbiente("2");
		propiedad.setPrecioMin(1L);
		propiedad.setPrecioMax(2L);
				
		// ejecucion
		when(controladorPropiedad.condicionValida(propiedad)).thenReturn(true);
		doThrow(Exception.class).when(controladorPropiedad).validarRango(propiedad);	
	}
	
    @Test
    public void envioMail(){
        // preparacion
    	
		ServicioPropiedad servicioPropiedad = mock(ServicioPropiedad.class);
		ControladorPropiedad controladorPropiedad = new ControladorPropiedad(servicioPropiedad);
		
		String destinatario = "walter.guglielmetti@gmail.com";
		String asunto = "prueba mails house lala";
		String cuerpo = "algo para mandar en el mail";

		controladorPropiedad.enviarConGMail(destinatario, asunto, cuerpo);

    }

}
