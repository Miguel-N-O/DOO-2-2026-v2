package co.edu.uco.libreriauco.negocio.negocio.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.libreriauco.dao.factoria.DAOFactory;
import co.edu.uco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.negocio.negocio.PaisNegocio;
import co.edu.uco.libreriauco.negocio.negocio.asembler.Impl.PaisEntidadAssembler;
import co.edu.uco.libreriauco.negocio.negocio.reglas.impl.pais.AsegurarNombreNuevoPaisNoExistaRule;
import co.edu.uco.libreriauco.negocio.negocio.reglas.impl.pais.ValidarDatosRegistrarInformacionNuevoPaisRule;


public class PaisNegocioImpl implements PaisNegocio {
	
	private DAOFactory daoFactory;
	
	
	protected PaisNegocioImpl(DAOFactory daoFactory) {
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
		return UUID.randomUUID();
	}



	//RULEPATTERN / VALIDATOR PATTERNS / SPECIFICATIONPATTERN
	
	@Override
	public void modificarInformacionPaisExistente(UUID id, PaisDominio datos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void darbajaPaisExistente(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PaisDominio> ConsultarPorFiltro(PaisDominio filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaisDominio> ConsultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaisDominio> ConsultarPorID(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
