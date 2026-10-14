package co.edu.uco.libreriauco.controlador.respuesta;

import java.util.List;

public class RespuestaError<D> extends Respuesta<D> {

	protected RespuestaError(List<String> mensajes, List<D> datos) {
		super(mensajes, datos, EstadoRespuesta.ERROR);
		// TODO Auto-generated constructor stub
	}

	public static RespuestaError<String> crear(final String message) {
		return new RespuestaError<>(List.of(message), List.of());

	}

	public static <D> RespuestaError<D> crear(List<String> mensajes, List<D> datos) {
		return new RespuestaError<D>(mensajes, datos);

	}

}
