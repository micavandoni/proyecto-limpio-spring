package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPropiedad;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service("servicioPropiedad")
@Transactional
public class ServicioPropiedadImpl implements ServicioPropiedad{
    @Inject
    private RepositorioPropiedad repositorioPropiedad;

    @Override
    public List<Propiedad> consultarPropiedad () {return repositorioPropiedad.consultarPropiedad();}

    @Override
    public boolean crearPropiedad(){
        try{
            repositorioPropiedad.crearPropiedad();
        return true;
        } catch (Exception e){
            return false;
        }
    }
}
