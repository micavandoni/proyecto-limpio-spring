package ar.edu.unlam.tallerweb1.persistencia;


import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class TestFav extends SpringTest{
	@Test
	@Transactional
	public void elegirPropiedadFavorita() {
		
//		Propiedad prop = new Propiedad();
//		
//		session().save(prop);
//		
//		Usuario usuario = new Usuario();
//		
//		usuario.agregarFavorita(prop);
//		session().save(usuario);
//		
//		Usuario usuarioBuscado = session().get(Usuario.class, usuario.getId());
//		assertThat(usuarioBuscado.getPropFav()).hasSize(1);
	}

}
