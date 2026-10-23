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
import co.edu.uco.libreriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCOExcepcion;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCOFachadaException;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCONegocioException;

public class PaisFachadaImpl implements PaisFachada {
	
	@Override
	public void registrarInformacionNuevoPais(PaisDTO datos) {
		DAOFactory daoFactory = DAOFactory.obtenerFactoria();
		PaisNegocio paisNegocio = new PaisNegocioImpl(daoFactory);

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
			
			var mensajeUsuario = CatalogoMensajes.PaisFachadaImpl.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_REGISTRANDO_INFORMACION_NUEVO_PAIS;
			throw LibreriaUCOFachadaException.crear(mensajeUsuario, exeption.getMessage(), exeption);
		}finally {
			daoFactory.cerrarConexion();
		}
		
	}

	@Override
	public void modificarInformacionPaisExistente(UUID id, PaisDTO datos) {
		DAOFactory daoFactory = DAOFactory.obtenerFactoria();
		PaisNegocio paisNegocio = new PaisNegocioImpl(daoFactory);

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
			
			var mensajeUsuario = CatalogoMensajes.PaisFachadaImpl.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_MODIFICANDO_INFORMACION_PAIS_EXISTENTE;
			throw LibreriaUCOFachadaException.crear(mensajeUsuario, exeption.getMessage(), exeption);
		}finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public void darbajaPaisExistente(UUID id) {
		DAOFactory daoFactory = DAOFactory.obtenerFactoria();
		PaisNegocio paisNegocio = new PaisNegocioImpl(daoFactory);

		daoFactory.iniciarTransaccion();
		
		try {
			paisNegocio.darbajaPaisExistente(id);
			daoFactory.confirmarTransaccion();
		} catch (LibreriaUCOExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		}catch (Exception exeption) {
			daoFactory.cancelarTransaccion();
			
			var mensajeUsuario = CatalogoMensajes.PaisFachadaImpl.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_DANDO_BAJA_PAIS_EXISTENTE;
			throw LibreriaUCOFachadaException.crear(mensajeUsuario, exeption.getMessage(), exeption);
		}finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public List<PaisDTO> ConsultarPorFiltro(PaisDTO filtro) {
		DAOFactory daoFactory = DAOFactory.obtenerFactoria();
		PaisNegocio paisNegocio = new PaisNegocioImpl(daoFactory);

		try {
			var paisDominioFiltro = PaisDTOAssembler.getInstance().convertirADominio(filtro);
			var listaPaisesDominio = paisNegocio.ConsultarPorFiltro(paisDominioFiltro);
			return PaisDTOAssembler.getInstance().convertirADTO(listaPaisesDominio);
		} catch (LibreriaUCOExcepcion excepcion) {
			throw excepcion;
		}catch (Exception exeption) {
			
			var mensajeUsuario = CatalogoMensajes.PaisFachadaImpl.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PAISES_POR_FILTRO;
			throw LibreriaUCOFachadaException.crear(mensajeUsuario, exeption.getMessage(), exeption);
		}finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public List<PaisDTO> ConsultarTodos() {
		DAOFactory daoFactory = DAOFactory.obtenerFactoria();
		PaisNegocio paisNegocio = new PaisNegocioImpl(daoFactory);

		try {
			var listaPaisesDominio = paisNegocio.ConsultarTodos();
			return PaisDTOAssembler.getInstance().convertirADTO(listaPaisesDominio);
		} catch (LibreriaUCOExcepcion excepcion) {
			throw excepcion;
		}catch (Exception exeption) {
			
			var mensajeUsuario = CatalogoMensajes.PaisFachadaImpl.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_PAISES;
			throw LibreriaUCOFachadaException.crear(mensajeUsuario, exeption.getMessage(), exeption);
		}finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public List<PaisDTO> ConsultarPorID(UUID id) {
		DAOFactory daoFactory = DAOFactory.obtenerFactoria();
		PaisNegocio paisNegocio = new PaisNegocioImpl(daoFactory);

		try {
			var listaPaisesDominio = paisNegocio.ConsultarPorID(id);
			return PaisDTOAssembler.getInstance().convertirADTO(listaPaisesDominio);
		} catch (LibreriaUCOExcepcion excepcion) {
			throw excepcion;
		}catch (Exception exeption) {
			
			var mensajeUsuario = CatalogoMensajes.PaisFachadaImpl.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PAIS_POR_ID;
			throw LibreriaUCOFachadaException.crear(mensajeUsuario, exeption.getMessage(), exeption);
		}finally {
			daoFactory.cerrarConexion();
		}
	}

}
