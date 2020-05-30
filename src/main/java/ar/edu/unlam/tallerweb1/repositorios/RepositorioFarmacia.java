package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Farmacia;

public interface RepositorioFarmacia {
	
	List<Farmacia> consultarFarmaciaPorNombre(String nombre);
}
