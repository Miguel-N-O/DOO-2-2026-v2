package co.edu.uco.libreriauco.negocio.negocio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.libreriauco.dao.factoria.DAOFactory;
import co.edu.uco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.entidad.PaisEntidad;
import co.edu.uco.libreriauco.negocio.negocio.PaisNegocio;
import co.edu.uco.libreriauco.negocio.negocio.asembler.Impl.PaisEntidadAssembler;
import co.edu.uco.libreriauco.negocio.negocio.reglas.impl.pais.AsegurarNombreNuevoPaisNoExistaRule;
import co.edu.uco.libreriauco.negocio.negocio.reglas.impl.pais.AsegurarNombrePaisNoExistaEnOtroPaisRule;
import co.edu.uco.libreriauco.negocio.negocio.reglas.impl.pais.AsegurarPaisExistaRule;
import co.edu.uco.libreriauco.negocio.negocio.reglas.impl.pais.ValidarDatosModificarInformacionPaisExistenteRule;
import co.edu.uco.libreriauco.negocio.negocio.reglas.impl.pais.ValidarDatosRegistrarInformacionNuevoPaisRule;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilUUID;


public class PaisNegocioImpl implements PaisNegocio {
	
	private DAOFactory daoFactory;
	
	
	public PaisNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void registrarInformacionNuevoPais(PaisDominio datos) {
		ValidarDatosRegistrarInformacionNuevoPaisRule.obtenerInstancia().ejecutar(datos);
		AsegurarNombreNuevoPaisNoExistaRule.obtenerInstancia().ejecutar(datos.getNombre(), daoFactory);

		
		var paisEntidad = PaisEntidadAssembler.getInstance().convertirAEntidad(datos);
		paisEntidad.setId(generarIdDePaisUnico());

		daoFactory.obtenerPaisDAO().crear(paisEntidad);

		// RulePattern, Validator Pattern, Specification Pattern
		// Cómo valido con Rule Pattern y Specification Pattern
	}
	
	
	
	private UUID generarIdDePaisUnico() {
		var id = UtilUUID.generar();
		var paisExistente = daoFactory.obtenerPaisDAO().consultarPorId(id);
		
		while (!UtilUUID.obtenerUUIDDefecto().equals(paisExistente.getId())) {
			id = UtilUUID.generar();
			paisExistente = daoFactory.obtenerPaisDAO().consultarPorId(id);
		}
		
		return id;
	}



	//RULEPATTERN / VALIDATOR PATTERNS / SPECIFICATIONPATTERN
	
	@Override
	public void modificarInformacionPaisExistente(UUID id, PaisDominio datos) {
		AsegurarPaisExistaRule.obtenerInstancia().ejecutar(id, daoFactory);
		ValidarDatosModificarInformacionPaisExistenteRule.obtenerInstancia().ejecutar(datos);
		AsegurarNombrePaisNoExistaEnOtroPaisRule.obtenerInstancia().ejecutar(id, datos.getNombre(), daoFactory);
		
		var paisEntidad = PaisEntidadAssembler.getInstance().convertirAEntidad(datos);
		paisEntidad.setId(id);
		
		daoFactory.obtenerPaisDAO().actualizar(id, paisEntidad);
	}

	@Override
	public void darbajaPaisExistente(UUID id) {
		AsegurarPaisExistaRule.obtenerInstancia().ejecutar(id, daoFactory);
		
		daoFactory.obtenerPaisDAO().eliminar(id);
	}

	@Override
	public List<PaisDominio> ConsultarPorFiltro(PaisDominio filtro) {
		var entidadFiltro = PaisEntidadAssembler.getInstance().convertirAEntidad(filtro);
		var listaEntidades = daoFactory.obtenerPaisDAO().consultarPorFiltro(entidadFiltro);
		
		return convertirListaADominio(listaEntidades);
	}

	@Override
	public List<PaisDominio> ConsultarTodos() {
		var listaEntidades = daoFactory.obtenerPaisDAO().consultarTodos();
		
		return convertirListaADominio(listaEntidades);
	}

	@Override
	public List<PaisDominio> ConsultarPorID(UUID id) {
		var resultados = new ArrayList<PaisDominio>();
		var paisEntidad = daoFactory.obtenerPaisDAO().consultarPorId(id);
		
		if (!UtilUUID.obtenerUUIDDefecto().equals(paisEntidad.getId())) {
			resultados.add(PaisEntidadAssembler.getInstance().convertirADominio(paisEntidad));
		}
		
		return resultados;
	}
	
	private List<PaisDominio> convertirListaADominio(List<PaisEntidad> listaEntidades) {
		var resultados = new ArrayList<PaisDominio>();
		
		for (PaisEntidad paisEntidad : listaEntidades) {
			resultados.add(PaisEntidadAssembler.getInstance().convertirADominio(paisEntidad));
		}
		
		return resultados;
	}

}
