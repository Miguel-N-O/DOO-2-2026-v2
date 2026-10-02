package co.edu.uco.libreriauco.negocio.negocio;

import java.util.List;
import java.util.UUID;

import co.edu.uco.libreriauco.dominio.PaisDominio;

public interface PaisNegocio {
	
	void registrarInformacionNuevoPais(PaisDominio datos);
	void modificarInformacionPaisExistente(UUID id, PaisDominio datos);
	void darbajaPaisExistente(UUID id);
	List<PaisDominio> ConsultarPorFiltro(PaisDominio filtro);
	List<PaisDominio> ConsultarTodos();
	List<PaisDominio> ConsultarPorID(UUID id);
	
}

