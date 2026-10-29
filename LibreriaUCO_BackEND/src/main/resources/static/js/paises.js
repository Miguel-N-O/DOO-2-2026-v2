const SOLO_LETRAS_ESPACIOS = /^[a-zA-Z ñÑáÁéÉíÍóÓúÚ]+$/;
const CARACTERES_NO_PERMITIDOS = /[^a-zA-Z ñÑáÁéÉíÍóÓúÚ]/g;
const LONGITUD_MAXIMA_NOMBRE = 50;
const URL_PAISES = "/api/v1/paises";
const MENSAJE_OPERACION_EXITOSA = "La operación se realizó de forma exitosa.";
const MENSAJE_PROBLEMA_INESPERADO = "Se ha presentado un problema inesperado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación.";
const MENSAJE_PROBLEMA_CARGANDO_PAISES = "No fue posible cargar los países. Por favor recargue la página y si el problema persiste contacte al administrador de la aplicación.";

const formularioPais = document.getElementById("formularioPais");
const tituloFormulario = document.getElementById("tituloFormulario");
const campoNombre = document.getElementById("nombre");
const textoErrorNombre = document.getElementById("nombreError");
const botonGuardar = document.getElementById("botonGuardar");
const botonCancelar = document.getElementById("botonCancelar");
const formularioBuscar = document.getElementById("formularioBuscar");
const campoBuscar = document.getElementById("buscar");
const botonConsultarTodos = document.getElementById("botonConsultarTodos");
const cuerpoTabla = document.getElementById("cuerpoTabla");
const contadorPaises = document.getElementById("contadorPaises");
const mensaje = document.getElementById("mensaje");
const dialogoBaja = document.getElementById("dialogoBaja");
const nombrePaisBaja = document.getElementById("nombrePaisBaja");
const botonConfirmarBaja = document.getElementById("botonConfirmarBaja");
const botonCancelarBaja = document.getElementById("botonCancelarBaja");

let idPaisEnEdicion = null;
let idPaisParaBaja = null;

function mostrarMensaje(tipo, texto) {
	mensaje.className = "alert alert-" + tipo;
	mensaje.textContent = texto;
	mensaje.scrollIntoView({ behavior: "smooth", block: "center" });
}

function ocultarMensaje() {
	mensaje.className = "alert d-none";
	mensaje.textContent = "";
}

function marcarNombre(error) {
	textoErrorNombre.textContent = error;

	if (error === "") {
		campoNombre.classList.remove("is-invalid");
		return true;
	}

	campoNombre.classList.add("is-invalid");
	return false;
}

function validarNombre() {
	const nombre = campoNombre.value.trim();

	if (nombre === "") {
		return marcarNombre("El nombre del país es obligatorio.");
	}

	if (nombre.length > LONGITUD_MAXIMA_NOMBRE) {
		return marcarNombre("El nombre del país debe tener de 1 a 50 caracteres.");
	}

	if (!SOLO_LETRAS_ESPACIOS.test(nombre)) {
		return marcarNombre("El nombre del país solo puede tener letras y espacios.");
	}

	return marcarNombre("");
}

function quitarCaracteresNoPermitidos(campo) {
	const valorLimpio = campo.value.replace(CARACTERES_NO_PERMITIDOS, "");

	if (valorLimpio === campo.value) {
		return;
	}

	const posicion = Math.max(0, campo.selectionStart - (campo.value.length - valorLimpio.length));
	campo.value = valorLimpio;
	campo.setSelectionRange(posicion, posicion);
}

function obtenerEncabezadosEnvio() {
	const encabezados = { "Content-Type": "application/json" };
	const token = document.querySelector('meta[name="_csrf"]');
	const nombreEncabezado = document.querySelector('meta[name="_csrf_header"]');

	if (token && nombreEncabezado) {
		encabezados[nombreEncabezado.content] = token.content;
	}

	return encabezados;
}

async function leerCuerpo(respuesta) {
	try {
		return await respuesta.json();
	} catch (error) {
		return null;
	}
}

function obtenerPrimerMensaje(cuerpo, mensajePorDefecto) {
	if (cuerpo !== null && Array.isArray(cuerpo.mensajes) && cuerpo.mensajes.length > 0) {
		return String(cuerpo.mensajes[0]);
	}
	return mensajePorDefecto;
}

function prepararRegistro() {
	idPaisEnEdicion = null;
	formularioPais.reset();
	marcarNombre("");
	tituloFormulario.textContent = "Registrar país";
	botonGuardar.textContent = "Registrar";
	botonCancelar.textContent = "Limpiar";
	formularioPais.classList.remove("formulario-edicion");
}

function prepararModificacion(pais) {
	ocultarMensaje();
	idPaisEnEdicion = pais.id;
	campoNombre.value = pais.nombre;
	marcarNombre("");
	tituloFormulario.textContent = "Modificar país";
	botonGuardar.textContent = "Guardar cambios";
	botonCancelar.textContent = "Cancelar";
	formularioPais.classList.add("formulario-edicion");
	campoNombre.focus();
}

function abrirDialogoBaja(pais) {
	idPaisParaBaja = pais.id;
	nombrePaisBaja.textContent = pais.nombre;
	dialogoBaja.showModal();
}

function crearBoton(texto, clase, accion) {
	const boton = document.createElement("button");
	boton.type = "button";
	boton.className = "btn btn-sm " + clase;
	boton.textContent = texto;
	boton.addEventListener("click", accion);
	return boton;
}

function pintarTabla(paises) {
	cuerpoTabla.replaceChildren();

	if (paises.length === 0) {
		const fila = document.createElement("tr");
		const celda = document.createElement("td");
		celda.colSpan = 2;
		celda.className = "text-secondary";
		celda.textContent = "No hay países para mostrar.";
		fila.appendChild(celda);
		cuerpoTabla.appendChild(fila);
	}

	for (const pais of paises) {
		const fila = document.createElement("tr");
		const celdaNombre = document.createElement("td");
		const celdaAcciones = document.createElement("td");

		celdaNombre.textContent = pais.nombre;
		celdaAcciones.className = "text-end text-nowrap";
		celdaAcciones.append(
			crearBoton("Modificar", "btn-outline-libreria me-2", () => prepararModificacion(pais)),
			crearBoton("Dar de baja", "btn-outline-danger", () => abrirDialogoBaja(pais)));

		fila.append(celdaNombre, celdaAcciones);
		cuerpoTabla.appendChild(fila);
	}

	contadorPaises.textContent = paises.length === 1 ? "1 país" : paises.length + " países";
}

async function consultarPaises() {
	const nombre = campoBuscar.value.trim();
	const url = nombre === "" ? URL_PAISES : URL_PAISES + "?nombre=" + encodeURIComponent(nombre);

	try {
		const respuesta = await fetch(url);
		const cuerpo = await leerCuerpo(respuesta);

		if (!respuesta.ok || cuerpo === null || !Array.isArray(cuerpo.datos)) {
			mostrarMensaje("danger", obtenerPrimerMensaje(cuerpo, MENSAJE_PROBLEMA_CARGANDO_PAISES));
			return;
		}

		pintarTabla(cuerpo.datos);
	} catch (error) {
		mostrarMensaje("danger", MENSAJE_PROBLEMA_CARGANDO_PAISES);
	}
}

async function guardarPais(evento) {
	evento.preventDefault();
	ocultarMensaje();

	if (botonGuardar.disabled || !validarNombre()) {
		return;
	}

	botonGuardar.disabled = true;

	const esModificacion = idPaisEnEdicion !== null;
	const url = esModificacion ? URL_PAISES + "/" + encodeURIComponent(idPaisEnEdicion) : URL_PAISES;

	try {
		const respuesta = await fetch(url, {
			method: esModificacion ? "PUT" : "POST",
			headers: obtenerEncabezadosEnvio(),
			body: JSON.stringify({ nombre: campoNombre.value.trim() })
		});
		const cuerpo = await leerCuerpo(respuesta);

		if (respuesta.ok) {
			prepararRegistro();
			await consultarPaises();
			mostrarMensaje("success", obtenerPrimerMensaje(cuerpo, MENSAJE_OPERACION_EXITOSA));
		} else {
			mostrarMensaje("danger", obtenerPrimerMensaje(cuerpo, MENSAJE_PROBLEMA_INESPERADO));
		}
	} catch (error) {
		mostrarMensaje("danger", MENSAJE_PROBLEMA_INESPERADO);
	} finally {
		botonGuardar.disabled = false;
	}
}

async function darDeBajaPais() {
	if (idPaisParaBaja === null || botonConfirmarBaja.disabled) {
		return;
	}

	const id = idPaisParaBaja;
	botonConfirmarBaja.disabled = true;
	ocultarMensaje();

	try {
		const respuesta = await fetch(URL_PAISES + "/" + encodeURIComponent(id), {
			method: "DELETE",
			headers: obtenerEncabezadosEnvio()
		});
		const cuerpo = await leerCuerpo(respuesta);
		dialogoBaja.close();

		if (respuesta.ok) {
			if (idPaisEnEdicion === id) {
				prepararRegistro();
			}
			await consultarPaises();
			mostrarMensaje("success", obtenerPrimerMensaje(cuerpo, MENSAJE_OPERACION_EXITOSA));
		} else {
			mostrarMensaje("danger", obtenerPrimerMensaje(cuerpo, MENSAJE_PROBLEMA_INESPERADO));
		}
	} catch (error) {
		dialogoBaja.close();
		mostrarMensaje("danger", MENSAJE_PROBLEMA_INESPERADO);
	} finally {
		botonConfirmarBaja.disabled = false;
	}
}

formularioPais.addEventListener("submit", guardarPais);

botonCancelar.addEventListener("click", () => {
	ocultarMensaje();
	prepararRegistro();
});

campoNombre.addEventListener("input", () => {
	quitarCaracteresNoPermitidos(campoNombre);

	if (campoNombre.classList.contains("is-invalid")) {
		validarNombre();
	}
});

campoBuscar.addEventListener("input", () => quitarCaracteresNoPermitidos(campoBuscar));

formularioBuscar.addEventListener("submit", (evento) => {
	evento.preventDefault();
	ocultarMensaje();
	consultarPaises();
});

botonConsultarTodos.addEventListener("click", () => {
	campoBuscar.value = "";
	ocultarMensaje();
	consultarPaises();
});

botonConfirmarBaja.addEventListener("click", darDeBajaPais);

botonCancelarBaja.addEventListener("click", () => dialogoBaja.close());

dialogoBaja.addEventListener("close", () => {
	idPaisParaBaja = null;
});

consultarPaises();
