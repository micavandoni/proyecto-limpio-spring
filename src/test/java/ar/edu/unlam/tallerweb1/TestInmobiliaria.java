package ar.edu.unlam.tallerweb1;

import javax.transaction.Transactional;
import ar.edu.unlam.tallerweb1.SpringTest;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import ar.edu.unlam.tallerweb1.modelo.Inmobiliaria;
import static org.assertj.core.api.Assertions.*;


public class TestInmobiliaria extends SpringTest {
	
	@Test @Transactional @Rollback
	public void insertarUnaInmobiliaria() {
		
		//preparacion
		
		Inmobiliaria inmobiliaria1 = new Inmobiliaria();
		inmobiliaria1.setNombreInmobiliaria("Remax CABA");
		inmobiliaria1.setDomicilio("Av. del Libertador 1200");
		
		//ejecución
		
		final Session session = session();
		session.save(inmobiliaria1);
				
		//comprobacion
		
		Inmobiliaria buscada = session.get(Inmobiliaria.class, inmobiliaria1.getId());
		assertThat(buscada).isNotNull();
		
	}
	

}