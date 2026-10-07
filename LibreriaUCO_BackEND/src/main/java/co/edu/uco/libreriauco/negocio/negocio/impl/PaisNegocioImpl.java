package co.edu.uco.libreriauco.negocio.negocio.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.libreriauco.dao.factoria.DAOFactory;
import co.edu.uco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.entidad.PaisEntidad;
import co.edu.uco.libreriauco.negocio.negocio.PaisNegocio;
import co.edu.uco.libreriauco.negocio.negocio.asembler.Impl.PaisEntidadAssembler;
import co.edu.uco.libreriauco.negocio.negocio.reglas.impl.pais.AsegurarNombrePaisNoExistaRule;
import co.edu.uco.libreriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCONegocioException;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCOTransversalExeption;

public class PaisNegocioImpl implements PaisNegocio {
	
	private DAOFactory daoFactory;
	
	
	protected PaisNegocioImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void registrarInformacionNuevoPais(PaisDominio datos) {
		asegurarDatosRegistroPaisValidos(datos);
		asegurarNombreRegistroPaisNoExista(datos.getNombre());

		
		AsegurarNombrePaisNoExistaRule.obtenerInstancia().ejecutar(datos.getNombre(), daoFactory);
		
		var paisEntidad = PaisEntidadAssembler.getInstance().convertirAEntidad(datos);
		paisEntidad.setId(generarIdDePaisUnico());
		
		daoFactory.obtenerPaisDAO().crear(paisEntidad);
	}
	
	private void asegurarDatosRegistroPaisValidos(PaisDominio datos) {

	}
	
	private void asegurarNombreRegistroPaisNoExista(String nombrePais) {
		var entidadFiltro = new PaisEntidad();
		entidadFiltro.setNombre(nombrePais);
		
		var resultados = daoFactory.obtenerPaisDAO().consultarPorFiltro(entidadFiltro);
		
		if(!resultados.isEmpty()) {
			var mensajeUsuario = CatalogoMensajes.PaisNegocioImpl.PAIS_EXISTE_CON_EL_MISMO_NOMBRE_DE_PAIS_A_CREAR;
			throw LibreriaUCONegocioException.crear(mensajeUsuario);
		}
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
