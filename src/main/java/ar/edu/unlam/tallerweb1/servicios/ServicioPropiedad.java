package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Propiedad;

import java.util.List;

public interface ServicioPropiedad {

    List<Propiedad> consultarPropiedad ();

    boolean crearPropiedad();
}