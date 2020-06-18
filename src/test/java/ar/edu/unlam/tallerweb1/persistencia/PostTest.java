package ar.edu.unlam.tallerweb1.persistencia;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Post;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Tag;

public class PostTest extends SpringTest{
	
	@Test
    @Transactional @Rollback
    public void testPost() {
		
	Session session = session();
    
	Tag tag1 = new Tag("Java");
    Tag tag2 = new Tag("Hibernate");
    
    session.save(tag1);
    session.save(tag2);
		
	Post post1 = new Post("JPA with Hibernate");
	Post post2 = new Post("Native Hibernate");
	
	Post post3 = new Post("Native PEPE");
 
 
    post1.addTag(tag1);
    post1.addTag(tag2);
 
    post2.addTag(tag1);
    
    session.save(post1);
    session.save(post2);
    session.save(post3);
    
	  List<Tag> listTag = session.createCriteria(Tag.class).list();
	  System.out.println("tam ListTag =" + listTag.size());
	  
	  List<Post> list = session.createCriteria(Post.class).list();
	  System.out.println("tam ListPost =" + list.size());

	}

	@Test
    @Transactional @Rollback
    public void guardarEnTag() {
		
		Session session = session();
		
		Tag tagPrueba = new Tag("Java");
		 session.save(tagPrueba);
		  List<Tag> list = session.createCriteria(Tag.class).list();
		  System.out.println("tam List =" + list.size());
		
	}
	
	@Test
    @Transactional
    public void guardarPropiedad() {
		
		Session session = session();
		
		Propiedad prop1 = new Propiedad();
		
		//prop1.setTipo("1");
		prop1.setPrecio(24000L);
		prop1.setDireccion("oscuro 666");
		prop1.setDetalle("sin detalle");
		prop1.setLocalidad("Rafael Castillo");
		prop1.setProvincia("BS AS");
		prop1.setCondicion("venta");
		prop1.setImagenUrl("");
		prop1.setAmbiente("1");
		prop1.setPrecioMin(0L);
		prop1.setPrecioMax(0L);

		session.save(prop1);
		session.getTransaction().commit();
		
	}
	
	@Test
    @Transactional @Rollback
    public void buscarPropiedad() {
		
		Session session = session();
		Criteria crit = session.createCriteria(Propiedad.class);
		
		List<Propiedad> list = crit.list();	
		
		  //List<Propiedad> list = session.createCriteria(Propiedad.class);
		  System.out.println("tam List =" + list.size());
		
	}
}