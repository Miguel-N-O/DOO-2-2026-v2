package co.edu.uco.libreriauco.dao.factoria.impl;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.libreriauco.dao.datos.entidad.DepartamentoDAO;
import co.edu.uco.libreriauco.dao.datos.entidad.PaisDAO;
import co.edu.uco.libreriauco.dao.datos.entidad.sqlserver.DepartamentoSqlServerDAO;
import co.edu.uco.libreriauco.dao.datos.entidad.sqlserver.PaisSqlServerDAO;
import co.edu.uco.libreriauco.dao.factoria.DAOFactory;
import co.edu.uco.libreriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCODatosException;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCOExcepcion;

public class SqlServerDAOFactory extends DAOFactory{

	private static final String CADENA_CONEXION = "jdbc:sqlserver://localhost:1433;databaseName=LibreriaUCO;encrypt=false";
	private static final String USUARIO = "libreriauco";
	private static final String CLAVE = "LibreriaUCO2026*";

	@Override
	protected void abrirConexion() {
		//TAREA: ¿Como abrir una conexion con SQL Server desde java?	
		try {
			setConexion(DriverManager.getConnection(CADENA_CONEXION, USUARIO, CLAVE));
		} catch (LibreriaUCOExcepcion excepcion) {
			throw excepcion;
		} catch (SQLException excepcion) {
			var mensajeUsuario = CatalogoMensajes.SqlServerDAOFactory.USUARIO_ERROR_PROBLEMA_ABRIENDO_CONEXION_SQL;
			throw LibreriaUCODatosException.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		} catch (Exception excepcion) {
			var mensajeUsuario = CatalogoMensajes.SqlServerDAOFactory.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ABRIENDO_CONEXION_SQL;
			throw LibreriaUCODatosException.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
		}
	}

	@Override
	public PaisDAO obtenerPaisDAO() {

		return new PaisSqlServerDAO(getConexion());
	}

	@Override
	public DepartamentoDAO obtenerDepartamentoDAO() {

		return new DepartamentoSqlServerDAO(getConexion());
	}

}
