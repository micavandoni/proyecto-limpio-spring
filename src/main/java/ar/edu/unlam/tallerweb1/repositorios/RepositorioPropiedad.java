package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Propiedad;



import java.util.List;


public interface RepositorioPropiedad {

    List<Propiedad> consultarPropiedad();


    void crearPropiedad();
}
