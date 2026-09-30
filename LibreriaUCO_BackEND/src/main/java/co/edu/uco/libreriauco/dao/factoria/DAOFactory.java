package co.edu.uco.libreriauco.dao.factoria;

import java.sql.Connection;

import co.edu.uco.libreriauco.dao.datos.entidad.DepartamentoDAO;
import co.edu.uco.libreriauco.dao.datos.entidad.PaisDAO;
import co.edu.uco.libreriauco.transversal.catalogo.CatalogoMensajes.UtilSQL;

public abstract class DAOFactory {

	private Connection conexion;
	
	protected DAOFactory() {
		abrirConexion();
		
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	protected abstract void  abrirConexion();
	
	public void cerrarConexion() {
	co.edu.uco.libreriauco.transversal.utilitarios.UtilSQL.cerrarConexion(conexion);;
	}

	public void iniciarTransaccion() {
		co.edu.uco.libreriauco.transversal.utilitarios.UtilSQL.iniciarTransaccion(conexion);
	}
	
	public void cancelarTransaccion() {
		co.edu.uco.libreriauco.transversal.utilitarios.UtilSQL.cancelarTransaccion(conexion);
	}
	
	
	public void confirmarTransaccion() {
		co.edu.uco.libreriauco.transversal.utilitarios.UtilSQL.confirmarTransaccion(conexion);
	}
	
	public abstract PaisDAO obtenerPaisDAO();
	public abstract DepartamentoDAO obtenerDepartamentoDAO();
	
	
}







