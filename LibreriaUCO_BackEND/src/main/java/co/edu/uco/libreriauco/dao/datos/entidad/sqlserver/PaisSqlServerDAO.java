package co.edu.uco.libreriauco.dao.datos.entidad.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.libreriauco.dao.datos.entidad.PaisDAO;
import co.edu.uco.libreriauco.dao.datos.entidad.SqlDAO;
import co.edu.uco.libreriauco.entidad.PaisEntidad;
import co.edu.uco.libreriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCODatosException;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilObjeto;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilTexto;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilUUID;

public class PaisSqlServerDAO extends SqlDAO implements PaisDAO {

	public PaisSqlServerDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public void crear(PaisEntidad entidad) {
		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("INSERT INTO Pais (id, nombre) ");
		sentenciaSQL.append("VALUES (?, ?)");

		try (var sentenciaPreparada = getConnection().prepareStatement(sentenciaSQL.toString())) {
			sentenciaPreparada.setString(1, entidad.getId().toString());
			sentenciaPreparada.setString(2, entidad.getNombre());
			sentenciaPreparada.executeUpdate();
		} catch (SQLException excepcion) {
			var mensajeUsuario = CatalogoMensajes.PaisSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_PAIS;
			throw LibreriaUCODatosException.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.PaisSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_PAIS;
			throw LibreriaUCODatosException.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public PaisEntidad consultarPorId(UUID id) {
		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("SELECT id, nombre ");
		sentenciaSQL.append("FROM Pais ");
		sentenciaSQL.append("WHERE id = ?");

		var parametros = new ArrayList<String>();
		parametros.add(UtilUUID.obtenerValorDefecto(id).toString());

		var resultados = consultar(sentenciaSQL.toString(), parametros);

		if (resultados.isEmpty()) {
			return new PaisEntidad();
		}

		return resultados.get(0);
	}

	@Override
	public List<PaisEntidad> consultarPorFiltro(PaisEntidad filtro) {
		var filtroValidado = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(filtro, new PaisEntidad());
		var parametros = new ArrayList<String>();

		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("SELECT id, nombre ");
		sentenciaSQL.append("FROM Pais ");
		sentenciaSQL.append("WHERE 1 = 1 ");

		if (!UtilUUID.obtenerUUIDDefecto().equals(UtilUUID.obtenerValorDefecto(filtroValidado.getId()))) {
			sentenciaSQL.append("AND id = ? ");
			parametros.add(filtroValidado.getId().toString());
		}

		if (!UtilTexto.getUtilTexto().esVacia(filtroValidado.getNombre())) {
			sentenciaSQL.append("AND nombre = ? ");
			parametros.add(filtroValidado.getNombre());
		}

		sentenciaSQL.append("ORDER BY nombre ASC");

		return consultar(sentenciaSQL.toString(), parametros);
	}

	@Override
	public List<PaisEntidad> consultarTodos() {
		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("SELECT id, nombre ");
		sentenciaSQL.append("FROM Pais ");
		sentenciaSQL.append("ORDER BY nombre ASC");

		return consultar(sentenciaSQL.toString(), new ArrayList<String>());
	}

	@Override
	public void actualizar(UUID id, PaisEntidad entidad) {
		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("UPDATE Pais ");
		sentenciaSQL.append("SET nombre = ? ");
		sentenciaSQL.append("WHERE id = ?");

		try (var sentenciaPreparada = getConnection().prepareStatement(sentenciaSQL.toString())) {
			sentenciaPreparada.setString(1, entidad.getNombre());
			sentenciaPreparada.setString(2, id.toString());
			sentenciaPreparada.executeUpdate();
		} catch (SQLException excepcion) {
			var mensajeUsuario = CatalogoMensajes.PaisSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_PAIS;
			throw LibreriaUCODatosException.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.PaisSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_PAIS;
			throw LibreriaUCODatosException.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public void eliminar(UUID id) {
		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("DELETE FROM Pais ");
		sentenciaSQL.append("WHERE id = ?");

		try (var sentenciaPreparada = getConnection().prepareStatement(sentenciaSQL.toString())) {
			sentenciaPreparada.setString(1, id.toString());
			sentenciaPreparada.executeUpdate();
		} catch (SQLException excepcion) {
			var mensajeUsuario = CatalogoMensajes.PaisSqlServerDAO.USUARIO_ERROR_PROBLEMA_ELIMINANDO_PAIS;
			throw LibreriaUCODatosException.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.PaisSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_PAIS;
			throw LibreriaUCODatosException.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	private List<PaisEntidad> consultar(String sentenciaSQL, List<String> parametros) {
		var resultados = new ArrayList<PaisEntidad>();

		try (var sentenciaPreparada = getConnection().prepareStatement(sentenciaSQL)) {
			for (int indice = 0; indice < parametros.size(); indice++) {
				sentenciaPreparada.setString(indice + 1, parametros.get(indice));
			}

			try (var resultado = sentenciaPreparada.executeQuery()) {
				while (resultado.next()) {
					var id = UtilUUID.convertirAUUID(resultado.getString("id"));
					var nombre = resultado.getString("nombre");
					resultados.add(new PaisEntidad(id, nombre));
				}
			}
		} catch (SQLException excepcion) {
			var mensajeUsuario = CatalogoMensajes.PaisSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PAIS;
			throw LibreriaUCODatosException.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.PaisSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PAIS;
			throw LibreriaUCODatosException.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}

		return resultados;
	}

}
