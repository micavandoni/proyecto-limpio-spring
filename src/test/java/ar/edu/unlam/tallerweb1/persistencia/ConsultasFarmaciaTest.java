package ar.edu.unlam.tallerweb1.persistencia;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Comuna;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Farmacia;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioFarmacia;

import static org.assertj.core.api.Assertions.*;
// import static org.junit.Assert.assertThat;

import java.util.List;

public class ConsultasFarmaciaTest extends SpringTest {
	
	 @Autowired
	 private RepositorioFarmacia repositorioFarmacia;
		
	
	public void crearFarmacia() {
		
		
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void consultarFarmaciasConNombreFarmacityTest() {
		Comuna com1 = new Comuna("COMUNA 1");
		Comuna com2 = new Comuna("COMUNA 2");
		Comuna com3 = new Comuna("COMUNA 3");
		
		Barrio bar1 = new Barrio("Ramos Mejia", com3);
		Barrio bar2 = new Barrio("Congreso", com1);
		Barrio bar3 = new Barrio("San Justo", com2);
		
		Direccion dir1 = new Direccion("Argentina", "654", bar1);
		Direccion dir2 = new Direccion("Rivadavia", "100", bar2);
		Direccion dir3 = new Direccion("Almafuerte", "1000", bar3);
		
		Farmacia far1 = new Farmacia("farmacity", "martes", 10, dir1);
		Farmacia far2 = new Farmacia("farmacity", "miercoles", 4, dir2);
		Farmacia far3 = new Farmacia("farmacity", "domingo", 2, dir3);
		Farmacia far4 = new Farmacia("central oeste", "martes", 10, dir3);
		Farmacia far5 = new Farmacia("central oeste", "lunes", 9, dir2);
		Farmacia far6 = new Farmacia("central oeste", "martes", 2, dir1);
		Farmacia far7 = new Farmacia("no se", "jueves", 10, dir1);
		
		session().save(far1);
		session().save(far2);
		session().save(far3);
		session().save(far4);
		session().save(far4);
		session().save(far5);
		session().save(far6);
		session().save(far7);
		
		List<Farmacia> listarFarmaciasConNombreFarmacity = repositorioFarmacia.consultarFarmaciaPorNombre("farmacity");
		assertThat(listarFarmaciasConNombreFarmacity).hasSize(3);
	}
	

}
