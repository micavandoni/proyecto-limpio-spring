package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@Repository("RepositorioPropiedad")

public class RepositorioPropiedadImpl implements RepositorioPropiedad {
    @Inject
    private SessionFactory sessionFactory;

    @Override
    public List<Propiedad> consultarPropiedad() {

        final Session session = sessionFactory.getCurrentSession();

        List<Propiedad> listaPropiedad = session.createCriteria(Propiedad.class)
                .list();

        return listaPropiedad;
    }

    @Override
    @PostConstruct
    @Transactional
    public  void crearPropiedad(){

        final Session session = sessionFactory.openSession();


        Propiedad propiedad1 = new Propiedad();

        propiedad1.setCondicion("venta");
        propiedad1.setTipo("Duplex");
        propiedad1.setPrecio(150000L);
        propiedad1.setProvincia("buenos aires");
        propiedad1.setLocalidad("San Justo");
        propiedad1.setDireccion("Arieta 1650");
        propiedad1.setDetalle("2 ambientes");
        propiedad1.setImagenUrl("https://i.pinimg.com/236x/7a/2a/c0/7a2ac002ac3bc8c541902f2c2781be98.jpg");

        Propiedad propiedad2 = new Propiedad();

        propiedad2.setCondicion("Alquiler");
        propiedad2.setTipo("Depto");
        propiedad2.setPrecio(300L);
        propiedad2.setProvincia("buenos aires");
        propiedad2.setLocalidad("Ramos Mejía");
        propiedad2.setDireccion("Av. de Mayo 460");
        propiedad2.setDetalle("2 ambientes");
        propiedad2.setImagenUrl("https://i.pinimg.com/236x/06/76/7e/06767efab6e1ea87afd841f05482513c.jpg");

        session.save (propiedad1);
        session.save (propiedad2);
    }
}
