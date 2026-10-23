package co.edu.uco.libreriauco.controlador.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.libreriauco.controlador.AbstractRestController;
import co.edu.uco.libreriauco.controlador.respuesta.RespuestaOk;
import co.edu.uco.libreriauco.dto.PaisDTO;
import co.edu.uco.libreriauco.negocio.fachada.PaisFachada;
import co.edu.uco.libreriauco.negocio.fachada.impl.PaisFachadaImpl;
import co.edu.uco.libreriauco.transversal.catalogo.CatalogoMensajes;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisControlador extends AbstractRestController {
	
	private PaisFachada fachada = new PaisFachadaImpl();
	
	@GetMapping("/dummy")
	public PaisDTO obtenerDummy() {
		return new PaisDTO();
	}
	
	
	
	@GetMapping("/login")
	public String hacerLogin(@AuthenticationPrincipal OidcUser datosUsuario) {
		return "Hola Usuario %s. Te has autenticado con el correo %s. Su token es %S".
				formatted(datosUsuario.getFullName(), datosUsuario.getEmail(), datosUsuario.getIdToken().getTokenValue());
	}
	
	
	
	
	@PostMapping
	public ResponseEntity<RespuestaOk<String>> registrarInformacionNuevoPais(@RequestBody PaisDTO pais) {
		fachada.registrarInformacionNuevoPais(pais);
		var listaMensajes = List.of();
		RespuestaOk<String> respuesta = RespuestaOk
				.crear("Se ha registrado la información del nuevo país de forma exitosa.");
		return ResponseEntity.ok(respuesta);
	}
	

	
	//Flat params INVESTIGAR
	
	@GetMapping	("/{id}")
	public ResponseEntity<RespuestaOk<PaisDTO>> ConsultarId(@PathVariable UUID id) {
		var pais = fachada.ConsultarPorID(id);
		
		RespuestaOk<PaisDTO> respuesta = RespuestaOk.crear(pais);
		
		return ResponseEntity.ok(respuesta);
		}
		
	//.......//api/v1/paises?id=valor&nombre=Colombia
	//.......//api/v1/paises?id=valor
	//.......//api/v1/paises?nombre=Colombia
	//.......//api/v1/paises?nombre=Colombia&id=valor
	//.......//api/v1/paises
	//.......//api/v1/paises?paisResidencia = valor BAD PRACTICE
	//.......//api/v1/paises?pais-Residencia = valor BEST PRACTICE
	
	
	@GetMapping	
	public ResponseEntity<RespuestaOk<PaisDTO>> consultaroPorFiltro(PaisDTO filtro) {
		var paises = fachada.ConsultarPorFiltro(filtro);
		
		RespuestaOk<PaisDTO> respuesta = RespuestaOk.crear(paises);
		
		return ResponseEntity.ok(respuesta);
		
	}
	
	
	@PutMapping ("/{id}")
	public ResponseEntity<RespuestaOk<String>> modificarInformacionPaisExistente(@RequestParam UUID id, @RequestBody PaisDTO datos){
		
		fachada.modificarInformacionPaisExistente(id, datos);
		RespuestaOk<String> respuesta = RespuestaOk
				.crear("Se ha modificado la información del nuevo país de forma exitosa.");
		return ResponseEntity.ok(respuesta);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<RespuestaOk<String>> darbajaPaisExistente(@RequestParam UUID id) {
		var paisFachada = new PaisFachadaImpl();
		paisFachada.darbajaPaisExistente(id);
		
		RespuestaOk<String> respuesta = RespuestaOk
				.crear("Se ha modificado la información del nuevo país de forma exitosa.");
		return ResponseEntity.ok(respuesta);
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