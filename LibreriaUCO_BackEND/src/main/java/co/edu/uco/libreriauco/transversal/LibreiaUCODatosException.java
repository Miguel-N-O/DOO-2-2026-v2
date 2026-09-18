package co.edu.uco.libreriauco.transversal;

import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCOExcepcion;
import co.edu.uco.libreriauco.transversal.excepciones.enums.Capa;

public class LibreiaUCODatosException extends LibreriaUCOExcepcion {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7882964888057678427L;

	protected LibreiaUCODatosException(Capa capa, String mensajeUsuario, String mensajeTecnico,
			Exception excepcionRaiz) {
		super(Capa.DATOS, mensajeUsuario, mensajeTecnico, excepcionRaiz);
		// TODO Auto-generated constructor stub
	}

	
}
