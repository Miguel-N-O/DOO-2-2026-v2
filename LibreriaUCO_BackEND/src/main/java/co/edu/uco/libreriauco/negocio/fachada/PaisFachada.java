package co.edu.uco.libreriauco.negocio.fachada;
//open lay close simple

import java.util.List;
import java.util.UUID;

import co.edu.uco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.dto.PaisDTO;

public interface PaisFachada {

	
	void registrarInformacionNuevoPais(PaisDTO datos);
	
	void modificarInformacionPaisExistente(UUID id, PaisDTO datos);
	
	void darbajaPaisExistente(UUID id);
	
	List<PaisDTO> ConsultarPorFiltro(PaisDTO filtro);
	
	List<PaisDTO> ConsultarTodos();
	
	List<PaisDTO> ConsultarPorID(UUID id);
	
	
}
