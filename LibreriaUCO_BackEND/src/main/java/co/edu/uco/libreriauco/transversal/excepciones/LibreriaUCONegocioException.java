package co.edu.uco.libreriauco.transversal.excepciones;

import co.edu.uco.libreriauco.transversal.excepciones.enums.Capa;

public class LibreriaUCONegocioException extends LibreriaUCOExcepcion {
	
	private static final long serialVersionUID = -6240488015702704944L;

	private LibreriaUCONegocioException(String mensajeUsuario, String mensajeTecnico,Exception excepcionRaiz) {
		super(Capa.NEGOCIO, mensajeUsuario, mensajeTecnico, excepcionRaiz);
		// TODO Auto-generated constructor stub
	}


}
