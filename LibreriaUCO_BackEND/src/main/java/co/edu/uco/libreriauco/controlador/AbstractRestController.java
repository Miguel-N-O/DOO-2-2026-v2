package co.edu.uco.libreriauco.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import co.edu.uco.libreriauco.controlador.respuesta.RespuestaError;
import co.edu.uco.libreriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCOExcepcion;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilUUID;

@RestControllerAdvice
public abstract class AbstractRestController {
	
	@ExceptionHandler(LibreriaUCOExcepcion.class)
	public ResponseEntity<RespuestaError<String>> controlarLibreriaUcoException(LibreriaUCOExcepcion excepcion) {
		var respuesta = RespuestaError.crear(excepcion.getMensajeUsuario());
		
		// logger.printException(Exception);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<RespuestaError<String>> controlarException(Exception excepcion) {
		var correlationId = UtilUUID.generarComoTexto();
		var mensajeRespuesta = CatalogoMensajes.AbstractRestController.USUARIO_ERROR_PROBLEMA_INESPERADO
				.concat(correlationId);
		var respuesta = RespuestaError.crear(mensajeRespuesta);
		
		excepcion.printStackTrace(); //ESTO NO USAR USAR EL LOGGER logger.printException
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
	}
	
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<RespuestaError<String>> controlarNotResourceFoundExeption(NoResourceFoundException excepcion) {
		var correlationId = UtilUUID.generarComoTexto();
		var mensajeRespuesta = CatalogoMensajes.AbstractRestController.USUARIO_ERROR_RECURSO_NO_ENCONTRADO
				.concat(correlationId);
		var respuesta = RespuestaError.crear(mensajeRespuesta);
		
		excepcion.printStackTrace(); //ESTO NO USAR USAR EL LOGGER logger.printException
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	}
	
}