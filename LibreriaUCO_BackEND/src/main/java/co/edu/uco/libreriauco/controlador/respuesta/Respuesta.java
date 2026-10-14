package co.edu.uco.libreriauco.controlador.respuesta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.libreriauco.transversal.utilitarios.UtilObjeto;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilUUID;

class Respuesta<D> {

	private LocalDateTime timestamp = LocalDateTime.now();
	private String correlationId;
	private List<String> mensajes;
	private List<D> datos;
	private EstadoRespuesta estado;

	protected Respuesta(List<String> mensajes, List<D> datos, EstadoRespuesta estado) {
		setMensajes(mensajes);
		setDatos(datos);
		setEstado(estado);
		correlationId = UtilUUID.generarComoTexto();
	}

	public String getCorrelationId() {
		return correlationId;
	}

	private void setMensajes(List<String> mensajes) {
		this.mensajes = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(mensajes, new ArrayList<String>());
	}

	private void setDatos(List<D> datos) {
		this.datos = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(datos, new ArrayList<D>());
		;
	}

	private void setEstado(EstadoRespuesta estado) {
		this.estado = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(estado, EstadoRespuesta.ERROR);

	}

	public List<String> getMensajes() {
		return mensajes;
	}

	public List<D> getDatos() {
		return datos;
	}

	public EstadoRespuesta getEstado() {
		return estado;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

}
