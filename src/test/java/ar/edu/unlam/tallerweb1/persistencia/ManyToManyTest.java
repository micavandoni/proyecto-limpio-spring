package ar.edu.unlam.tallerweb1.persistencia;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class ManyToManyTest extends SpringTest{
	
	@Test
    @Transactional 
    @Rollback
    public void testPropiedad() {
		
	Session session = session();
	
	Propiedad prop1 = new Propiedad();
	
	prop1.setTipo("1");
	prop1.setPrecio(24000L);
	prop1.setDireccion("Bogado 556");
	prop1.setDetalle("sin detalle");
	prop1.setLocalidad("Rafael Castillo");
	prop1.setProvincia("BS AS");
	prop1.setCondicion("venta");
	prop1.setImagenUrl("");
	prop1.setAmbiente("1");
	prop1.setPrecioMin(0L);
	prop1.setPrecioMax(0L);
	
	Propiedad prop2 = new Propiedad();
	
	prop2.setTipo("1");
	prop2.setPrecio(24000L);
	prop2.setDireccion("oscuro 556");
	prop2.setDetalle("sin detalle");
	prop2.setLocalidad("casanova");
	prop2.setProvincia("BS AS");
	prop2.setCondicion("alquiler");
	prop2.setImagenUrl("");
	prop2.setAmbiente("1");
	prop2.setPrecioMin(0L);
	prop2.setPrecioMax(0L);
	
	session.save(prop2);
	
	 Usuario usuario = new Usuario();
	 usuario.setEmail("diego@gmail.com");
	 usuario.setNombre("Diego L");
	 usuario.setPassword("111111");
	 usuario.setRol("USER");
	 
	 usuario.addPropiedad(prop1);
	 usuario.addPropiedad(prop2);
	 
	 session.save(usuario);
	 
    
	  List<Usuario> listTag = session.createCriteria(Usuario.class).list();
	  System.out.println("tam ListUsuario =" + listTag.size());
	  
	  List<Propiedad> list = session.createCriteria(Propiedad.class).list();
	  System.out.println("tam ListPropiedad =" + list.size());
	  
	  Usuario usu = (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("nombre", "Diego L")).uniqueResult();
	  Integer canPropiedades = usu.getPropiedades().size();
	  
	  System.out.println("tam canPropiedades =" + canPropiedades);
	  

	}
	
	@Test
    @Transactional 
    @Rollback
    public void testUsuario1() {
		
	Session session = session();
	  
	  Usuario usu = (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("id", "2")).uniqueResult();
	  Integer canPropiedades = usu.getPropiedades().size();
	  
	  System.out.println("tam canPropiedades =" + canPropiedades);
	  

	}

}