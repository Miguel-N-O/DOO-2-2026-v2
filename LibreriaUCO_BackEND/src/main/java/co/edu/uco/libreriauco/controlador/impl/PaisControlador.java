package co.edu.uco.libreriauco.controlador.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.libreriauco.controlador.AbstractRestController;
import co.edu.uco.libreriauco.controlador.respuesta.RespuestaOk;
import co.edu.uco.libreriauco.dto.PaisDTO;
import co.edu.uco.libreriauco.negocio.fachada.impl.PaisFachadaImpl;
import co.edu.uco.libreriauco.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisControlador extends AbstractRestController {
	
	@GetMapping
	public ResponseEntity<RespuestaOk<PaisDTO>> ConsultarTodos() {
		var paisFachada = new PaisFachadaImpl();
		var paises = paisFachada.ConsultarTodos();
		
		var respuesta = RespuestaOk.crear(List.of(CatalogoMensajes.PaisControlador.PAISES_CONSULTADOS_EXITOSAMENTE), paises);
		
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	}
	
	@PostMapping
	public ResponseEntity<RespuestaOk<PaisDTO>> registrarInformacionNuevoPais(@RequestBody PaisDTO datos) {
		var paisFachada = new PaisFachadaImpl();
		paisFachada.registrarInformacionNuevoPais(datos);
		
		var respuesta = RespuestaOk.crear(List.of(CatalogoMensajes.PaisControlador.PAIS_REGISTRADO_EXITOSAMENTE), List.<PaisDTO>of());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}
	
	@PutMapping
	public ResponseEntity<RespuestaOk<PaisDTO>> modificarInformacionPaisExistente(@RequestParam UUID id, @RequestBody PaisDTO datos) {
		var paisFachada = new PaisFachadaImpl();
		paisFachada.modificarInformacionPaisExistente(id, datos);
		
		var respuesta = RespuestaOk.crear(List.of(CatalogoMensajes.PaisControlador.PAIS_MODIFICADO_EXITOSAMENTE), List.<PaisDTO>of());
		
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	}
	
	@DeleteMapping
	public ResponseEntity<RespuestaOk<PaisDTO>> darbajaPaisExistente(@RequestParam UUID id) {
		var paisFachada = new PaisFachadaImpl();
		paisFachada.darbajaPaisExistente(id);
		
		var respuesta = RespuestaOk.crear(List.of(CatalogoMensajes.PaisControlador.PAIS_DADO_DE_BAJA_EXITOSAMENTE), List.<PaisDTO>of());
		
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	}

}




//investigar Richardson Maturity Model The Glory of Rest 
//TLS 1.3
//Wireshark

//<protocolo>://<host>:<puerto>/<recurso>
//	http: //localhost:8800//api/v1/paises
//http: //127.0.0.1:localhost:8800//api/v1/paises
//http://10.100.82.181:localhost:8800//api/v1/paises
//	http://LAPTOP-V4S59H96:8800//api/v1/paises
//http - - > 80
//https - - > 4