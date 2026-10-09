package co.edu.uco.libreriauco.negocio.fachada.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.libreriauco.dao.factoria.DAOFactory;
import co.edu.uco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.dto.PaisDTO;
import co.edu.uco.libreriauco.negocio.fachada.PaisFachada;
import co.edu.uco.libreriauco.negocio.fachada.assembler.impl.PaisDTOAssembler;
import co.edu.uco.libreriauco.negocio.negocio.PaisNegocio;
import co.edu.uco.libreriauco.negocio.negocio.impl.PaisNegocioImpl;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCOExcepcion;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCOFachadaException;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCONegocioException;

public class PaisFachadaImpl implements PaisFachada {
	
	private DAOFactory daoFactory;
	private PaisNegocio paisNegocio;
	
	public PaisFachadaImpl() {
		daoFactory = DAOFactory.obtenerFactoria();
		paisNegocio = new PaisNegocioImpl(daoFactory);
			
		}
		

	@Override
	public void registrarInformacionNuevoPais(PaisDTO datos) {
		daoFactory.iniciarTransaccion();
		
		try {
			var paisDominio = PaisDTOAssembler.getInstance().convertirADominio(datos);
			paisNegocio.registrarInformacionNuevoPais(paisDominio);
			daoFactory.confirmarTransaccion();
		} catch (LibreriaUCOExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		}catch (Exception exeption) {
			daoFactory.cancelarTransaccion();
			
			var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registar la inforamcion del nuevo pais deseado. Por Favot intente de nuevo y si el problema persiste contante al administrador de la apliacion";
			throw LibreriaUCOFachadaException.crear(mensajeUsuario, exeption.getMessage(), exeption);
		}finally {
			daoFactory.cerrarConexion();
		}
		
	}

	@Override
	public void modificarInformacionPaisExistente(UUID id, PaisDTO datos) {
		daoFactory.iniciarTransaccion();
		
		try {
			var paisDominio = PaisDTOAssembler.getInstance().convertirADominio(datos);
			paisNegocio.modificarInformacionPaisExistente(id , paisDominio);
			daoFactory.confirmarTransaccion();
		} catch (LibreriaUCOExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		}catch (Exception exeption) {
			daoFactory.cancelarTransaccion();
			
			var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la inforamcion del nuevo pais deseado. Por Favot intente de nuevo y si el problema persiste contante al administrador de la apliacion";
			throw LibreriaUCOFachadaException.crear(mensajeUsuario, exeption.getMessage(), exeption);
		}finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public void darbajaPaisExistente(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PaisDTO> ConsultarPorFiltro(PaisDTO filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaisDTO> ConsultarTodos() {
		try {
			var listaPaisesDominio = paisNegocio.ConsultarTodos();
			return PaisDTOAssembler.getInstance().convertirADTO(listaPaisesDominio);
		} catch (LibreriaUCOExcepcion excepcion) {
			throw excepcion;
		}catch (Exception exeption) {
			
			var mensajeUsuario = "Se ha presentado un problema inesperado tratando de consultar la inforamcion de todos los pais deseado. Por Favot intente de nuevo y si el problema persiste contante al administrador de la apliacion";
			throw LibreriaUCOFachadaException.crear(mensajeUsuario, exeption.getMessage(), exeption);
		}finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public List<PaisDTO> ConsultarPorID(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
