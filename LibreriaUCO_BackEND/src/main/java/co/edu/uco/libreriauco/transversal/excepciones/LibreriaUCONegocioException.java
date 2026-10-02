package co.edu.uco.libreriauco.transversal.excepciones;

import co.edu.uco.libreriauco.transversal.excepciones.enums.Capa;

public class LibreriaUCONegocioException extends LibreriaUCOExcepcion {
	
	private static final long serialVersionUID = -6240488015702704944L;

	private LibreriaUCONegocioException(String mensajeUsuario, String mensajeTecnico,Exception excepcionRaiz) {
		super(Capa.NEGOCIO, mensajeUsuario, mensajeTecnico, excepcionRaiz);
		// TODO Auto-generated constructor stub
	}
	
	
	public static LibreriaUCONegocioException crear(String mensajeUsuario) {
		
		return new LibreriaUCONegocioException(mensajeUsuario, mensajeUsuario, new Exception(mensajeUsuario));
	}
	
	
	public static LibreriaUCONegocioException crear(String mensajeUsuario, String mensajeTecnico) {
		
		return new LibreriaUCONegocioException(mensajeUsuario, mensajeTecnico, new Exception(mensajeTecnico));
	}
	
	public static LibreriaUCONegocioException crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
		
		return new LibreriaUCONegocioException(mensajeUsuario, mensajeTecnico, excepcionRaiz);
	}


}
