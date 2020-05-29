package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Farmacia;

public interface RepositorioFarmacia {
	
	Farmacia consultarFarmaciaPorNombre(String nombre);
}
