package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPropiedad;

@Service("servicioPropiedad")
@Transactional
public class ServicioPropiedadImpl implements ServicioPropiedad{
	
    private RepositorioPropiedad repositorioPropiedad;
    
	@Autowired
	public ServicioPropiedadImpl(RepositorioPropiedad repositorioPropiedad){
		this. repositorioPropiedad= repositorioPropiedad;
	}

    @Override
    public List<Propiedad> consultarPropiedad () {
    	return repositorioPropiedad.consultarPropiedad();
    	}

    @Override
    public boolean crearPropiedad(){
        try{
            repositorioPropiedad.crearPropiedad();
        return true;
        } catch (Exception e){
            return false;
        }
    }

	@Override
	public List<Propiedad> consultarPropiedadFilter(Propiedad propiedad) {
    	return repositorioPropiedad.consultarPropiedadFilter(propiedad);
    	}
	
	@Override
	public List<Propiedad> consultarNuevasPropiedades(){
		return repositorioPropiedad.consultarNuevasPropiedades();
	}
}
