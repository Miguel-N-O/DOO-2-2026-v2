package co.edu.uco.libreriauco.negocio.negocio.reglas.impl.pais;

import co.edu.uco.libreriauco.negocio.negocio.reglas.Rule;
import co.edu.uco.libreriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCOTransversalExeption;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilTexto;

public class AsegurarNombrePaisValidoRule implements Rule<String> {

	@Override
	public void ejecutar(String... datos) {
		var nombrePais = datos[0];
		validarObligatoriedad(nombrePais);
		// Validar Formato
		validarLongitud (nombrePais, 1, 50);
		
		
	}
	
	private void validarObligatoriedad(String dato) {
		
		if(UtilTexto.getUtilTexto().esVacia(dato));{
			var mensajeUsuario = CatalogoMensajes.PaisNegocioImpl.NOMBRE_PAIS_OBLIGATORIO;
			throw LibreriaUCOTransversalExeption.crear(mensajeUsuario);
		}
	
	}
	
	private void validarLongitud(String dato, int longitudMinima, int LongitudMaxima) {
		
		if(!UtilTexto.getUtilTexto().obtenerLongitudCadenaEsValida(dato, longitudMinima, LongitudMaxima, true));{
			var mensajeUsuario = CatalogoMensajes.PaisNegocioImpl.LONGITUD_NOMBRE_PAIS_NO_VALIDA;
			throw LibreriaUCOTransversalExeption.crear(mensajeUsuario);
		}
	
	}
	
	
}
