package co.edu.uco.libreriauco.dao.datos.entidad.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.libreriauco.dao.datos.entidad.DepartamentoDAO;
import co.edu.uco.libreriauco.dao.datos.entidad.SqlDAO;
import co.edu.uco.libreriauco.entidad.DepartamentoEntidad;
import co.edu.uco.libreriauco.entidad.PaisEntidad;
import co.edu.uco.libreriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCODatosException;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilObjeto;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilTexto;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilUUID;

public class DepartamentoSqlServerDAO extends SqlDAO implements DepartamentoDAO{

	public DepartamentoSqlServerDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public DepartamentoEntidad consultarPorId(UUID id) {
		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("SELECT d.id, d.nombre, p.id AS idPais, p.nombre AS nombrePais ");
		sentenciaSQL.append("FROM Departamento d ");
		sentenciaSQL.append("INNER JOIN Pais p ON d.pais = p.id ");
		sentenciaSQL.append("WHERE d.id = ?");

		var parametros = new ArrayList<String>();
		parametros.add(UtilUUID.obtenerValorDefecto(id).toString());

		var resultados = consultar(sentenciaSQL.toString(), parametros);

		if (resultados.isEmpty()) {
			return new DepartamentoEntidad();
		}

		return resultados.get(0);
	}

	@Override
	public List<DepartamentoEntidad> consultarPorFiltro(DepartamentoEntidad filtro) {
		var filtroValidado = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(filtro, new DepartamentoEntidad());
		var parametros = new ArrayList<String>();

		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("SELECT d.id, d.nombre, p.id AS idPais, p.nombre AS nombrePais ");
		sentenciaSQL.append("FROM Departamento d ");
		sentenciaSQL.append("INNER JOIN Pais p ON d.pais = p.id ");
		sentenciaSQL.append("WHERE 1 = 1 ");

		if (!UtilUUID.obtenerUUIDDefecto().equals(UtilUUID.obtenerValorDefecto(filtroValidado.getId()))) {
			sentenciaSQL.append("AND d.id = ? ");
			parametros.add(filtroValidado.getId().toString());
		}

		if (!UtilTexto.getUtilTexto().esVacia(filtroValidado.getNombre())) {
			sentenciaSQL.append("AND d.nombre = ? ");
			parametros.add(filtroValidado.getNombre());
		}

		if (!UtilUUID.obtenerUUIDDefecto().equals(UtilUUID.obtenerValorDefecto(filtroValidado.getPais().getId()))) {
			sentenciaSQL.append("AND d.pais = ? ");
			parametros.add(filtroValidado.getPais().getId().toString());
		}

		sentenciaSQL.append("ORDER BY d.nombre ASC");

		return consultar(sentenciaSQL.toString(), parametros);
	}

	@Override
	public List<DepartamentoEntidad> consultarTodos() {
		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("SELECT d.id, d.nombre, p.id AS idPais, p.nombre AS nombrePais ");
		sentenciaSQL.append("FROM Departamento d ");
		sentenciaSQL.append("INNER JOIN Pais p ON d.pais = p.id ");
		sentenciaSQL.append("ORDER BY d.nombre ASC");

		return consultar(sentenciaSQL.toString(), new ArrayList<String>());
	}

	private List<DepartamentoEntidad> consultar(String sentenciaSQL, List<String> parametros) {
		var resultados = new ArrayList<DepartamentoEntidad>();

		try (var sentenciaPreparada = getConnection().prepareStatement(sentenciaSQL)) {
			for (int indice = 0; indice < parametros.size(); indice++) {
				sentenciaPreparada.setString(indice + 1, parametros.get(indice));
			}

			try (var resultado = sentenciaPreparada.executeQuery()) {
				while (resultado.next()) {
					var pais = new PaisEntidad(UtilUUID.convertirAUUID(resultado.getString("idPais")), resultado.getString("nombrePais"));

					var departamento = new DepartamentoEntidad();
					departamento.setId(UtilUUID.convertirAUUID(resultado.getString("id")));
					departamento.setNombre(resultado.getString("nombre"));
					departamento.setPais(pais);

					resultados.add(departamento);
				}
			}
		} catch (SQLException excepcion) {
			var mensajeUsuario = CatalogoMensajes.DepartamentoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_DEPARTAMENTO;
			throw LibreriaUCODatosException.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.DepartamentoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DEPARTAMENTO;
			throw LibreriaUCODatosException.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}

		return resultados;
	}

}
