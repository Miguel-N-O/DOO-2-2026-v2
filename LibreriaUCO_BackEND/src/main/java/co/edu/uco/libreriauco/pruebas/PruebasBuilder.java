package co.edu.uco.libreriauco.pruebas;
import co.edu.uco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilUUID;

public class PruebasBuilder {

	public static void main(String[] arg) {
		
		PaisDominio dominioPaisuno = new PaisDominio.Builder().build();
		PaisDominio dominioPaisConId = new PaisDominio.Builder().id(UtilUUID.generar()).build();
		PaisDominio dominioPaisConNombre = new PaisDominio.Builder().nombre("C").build();
		PaisDominio dominioPaisCompleto = new PaisDominio.Builder()
												.nombre("C")
												.id(UtilUUID.generar())
												.build();
	}
	
}


