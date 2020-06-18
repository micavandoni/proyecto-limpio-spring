package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Inmobiliaria;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class InmobiliariaTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void TodasLasPropiedadesDeUnaInmobiliaria() {

        Inmobiliaria lezica = new Inmobiliaria();
        Inmobiliaria stella = new Inmobiliaria();

        lezica.setNombreInmobiliaria("Lezica");
        lezica.setEmail("lezica@email");

        stella.setNombreInmobiliaria("Stella");
        stella.setEmail("stella@email");

        session().save(lezica);
        session().save(stella);

        Propiedad propiedad1 = new Propiedad();
        Propiedad propiedad2 = new Propiedad();
        Propiedad propiedad3 = new Propiedad();

        propiedad1.setProvincia("buenos aires");
        propiedad2.setProvincia("santa fe");
        propiedad3.setProvincia("entre rios");


        session().save(propiedad1);
        session().save(propiedad2);
        session().save(propiedad3);


        Publicacion publi1 = new Publicacion();
        Publicacion publi2 = new Publicacion();
        Publicacion publi3 = new Publicacion();

        publi1.setInmobiliaria(lezica);
        publi1.setPropiedad(propiedad2);

        publi2.setInmobiliaria(stella);
        publi2.setPropiedad(propiedad2);

        publi3.setInmobiliaria(stella);
        publi3.setPropiedad(propiedad3);

        session().save(publi1);
        session().save(publi2);
        session().save(publi3);

        List<Publicacion> todasLasPublicaciones = session().createCriteria(Publicacion.class).list();
        assertThat(todasLasPublicaciones.size()).isEqualTo(3);

    }
}
