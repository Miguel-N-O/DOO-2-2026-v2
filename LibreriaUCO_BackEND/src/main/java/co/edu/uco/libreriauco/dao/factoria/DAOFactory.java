package co.edu.uco.libreriauco.dao.factoria;

import java.sql.Connection;

import co.edu.uco.libreriauco.dao.datos.entidad.DepartamentoDAO;
import co.edu.uco.libreriauco.dao.datos.entidad.PaisDAO;

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
		//TAREA: COMO SE CIERRA LA CONEXION DE FORMA SEGURA
	}

	public void iniciarTransaccion() {
		//TAREA: COMO SE INICIA UNA TRANSACCION DE FORMA SEGURA ?
	}
	
	public void cancelarTransaccion() {
		//TAREA: COMO SE CANCELA UNA TRANSACCION DE FORMA SEGURA ?
	}
	
	
	public void confirmarTransaccion() {
		//TAREA: COMO SE CONFIRMA UNA TRANSACCION DE FORMA SEGURA ?
	}
	
	public abstract PaisDAO obtenerPaisDAO();
	public abstract DepartamentoDAO obtenerDepartamentoDAO();
	
	
}







