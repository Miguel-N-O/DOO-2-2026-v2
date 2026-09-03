package co.edu.uco.libreriauco.pruebas;

import co.edu.uco.libreriauco.transversal.utilitarios.UtilTexto;
import static co.edu.uco.libreriauco.transversal.utilitarios.UtilTexto.getUtilTexto;

public class PruebasTexto {

	public static void main(String[] args) {
		
		String miVariable = "mi casa es azul";
		
		System.out.println(miVariable);
		System.out.println(getUtilTexto().obtenerLongitudCadena(miVariable, false));
		System.out.println(getUtilTexto().obtenerLongitudCadena(miVariable, true));
		
	}
	
}