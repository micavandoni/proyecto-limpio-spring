package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;


import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class ControladorLoginTest extends SpringTest {

	@Test
	public void registrarUsuario() {
		
		//preparacion
		ServicioLogin servicioLogin = mock(ServicioLogin.class);
		ControladorLogin controladorLogin = new ControladorLogin(servicioLogin);
		
		Usuario usuario = new Usuario();
		usuario.setEmail("mica@gmail.com");
		usuario.setNombre("Mica");
		usuario.setPassword("usuario");
				
		//ejecucion
		final ModelAndView model = controladorLogin.registrarse(usuario);
		
		//validacion
		assertThat(model.getViewName()).isEqualTo("registrar");
	}
	

}
