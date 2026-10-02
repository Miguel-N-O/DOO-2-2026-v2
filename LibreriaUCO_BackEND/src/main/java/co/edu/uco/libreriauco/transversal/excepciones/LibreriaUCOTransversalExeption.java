package co.edu.uco.libreriauco.transversal.excepciones;

import co.edu.uco.libreriauco.transversal.excepciones.enums.Capa;

public class LibreriaUCOTransversalExeption extends LibreriaUCOExcepcion{
	
	private static final long serialVersionUID = -6240488015702704944L;

	private LibreriaUCOTransversalExeption(String mensajeUsuario, String mensajeTecnico,
			Exception excepcionRaiz) {
		super(Capa.TRANSVERSAL, mensajeUsuario, mensajeTecnico, excepcionRaiz);
		// TODO Auto-generated constructor stub
	}

	public static LibreriaUCOExcepcion crear(String mensajeUsuario) {
		
		return new LibreriaUCOTransversalExeption(mensajeUsuario, mensajeUsuario, new Exception(mensajeUsuario));
	}
	
	
	public static LibreriaUCOExcepcion crear(String mensajeUsuario, String mensajeTecnico) {
		
		return new LibreriaUCOTransversalExeption(mensajeUsuario, mensajeTecnico, new Exception(mensajeTecnico));
	}
	
	public static LibreriaUCOExcepcion crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
		
		return new LibreriaUCOTransversalExeption(mensajeUsuario, mensajeTecnico, excepcionRaiz);
	}
}

