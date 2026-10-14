package co.edu.uco.libreriauco.controlador.respuesta;

import java.util.List;

public class RespuestaOk <D> extends Respuesta<D> {

	protected RespuestaOk(List<String> mensajes, List<D> datos) {
		super(mensajes, datos, EstadoRespuesta.OK);
		// TODO Auto-generated constructor stub
	}

	public static <D> RespuestaOk<D> crear(List<String> mensajes, List<D> datos) {
		return new RespuestaOk<D>(mensajes, datos);

	}

	public static <D> RespuestaOk<D> crear(List<D> datos) {
		return new RespuestaOk<D>(List.of(), datos);

	}
	
	
	public static <D> RespuestaOk<D> crear(D dato) {
		return new RespuestaOk<D>(List.of(), List.of(dato));

	}
	
	public static <D> RespuestaOk<D> crear() {
		return new RespuestaOk<D>(List.of(), List.of());

	}
}
