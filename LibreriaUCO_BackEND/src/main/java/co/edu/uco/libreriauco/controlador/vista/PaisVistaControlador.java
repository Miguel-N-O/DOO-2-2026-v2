package co.edu.uco.libreriauco.controlador.vista;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaisVistaControlador {

	@GetMapping("/")
	public String mostrarInicio() {
		return "redirect:/paises";
	}

	@GetMapping("/paises")
	public String mostrarPaises(@AuthenticationPrincipal OidcUser datosUsuario, Model modelo) {
		modelo.addAttribute("nombreUsuario", datosUsuario.getFullName());
		return "pais/paises";
	}
}
