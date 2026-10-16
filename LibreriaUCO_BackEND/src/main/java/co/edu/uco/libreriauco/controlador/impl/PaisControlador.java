package co.edu.uco.libreriauco.controlador.impl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.libreriauco.controlador.AbstractRestController;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisControlador extends AbstractRestController {
	
@GetMapping	
	public String ConsultarTodos() {
		return "ahora si mono! Metodo GET exitoso";
	}

@PostMapping
public String registrarInformacionNuevoPais() {
	return "ahora si mono! Metodo POST exitoso";
}

@PutMapping
public String modificarInformacionPaisExistente() {
	return "ahora si mono! Metodo PUT exitoso";
}


@DeleteMapping	
public String darbajaPaisExistente() {
	return "ahora si mono! Metodo DELETE exitoso";
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