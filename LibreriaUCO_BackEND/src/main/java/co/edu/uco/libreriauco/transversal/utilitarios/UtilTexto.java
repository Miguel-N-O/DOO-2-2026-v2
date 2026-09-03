package co.edu.uco.libreriauco.transversal.utilitarios;

public class UtilTexto {
	
	private static UtilTexto INSTANCIA;
	public static String VACIO = "";
	
	private UtilTexto() {
	}
	
	public static UtilTexto getUtilTexto() {
		
		if(INSTANCIA == null) {
			synchronized (UtilTexto.class){
				if (UtilObjeto.esNulo(INSTANCIA)) {
					INSTANCIA = new UtilTexto();
					
				}
			}
		}
		return INSTANCIA;
	}
	
	public boolean esNula(String cadena) {
		return UtilObjeto.esNulo(cadena);
	}
	
	public boolean esVacia(String cadena) {
		return VACIO.equals(obtenerValorDefecto(cadena));
	}
	
	public String obtenerValorDefecto(String valor, String valorDefecto) {
		return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, valorDefecto);
	}
	
	public String obtenerValorDefecto(String valor) {
		return obtenerValorDefecto(valor, VACIO);
	}
	
	public String quitarEspacioEnBlanco(String valor) { 
		return obtenerValorDefecto(valor).trim();
	}
	
	public int obtenerLongitudCadena(String valor) {
		return obtenerValorDefecto(valor).length();
	}
	
	public int obtenerLongitudCadena(String valor, boolean quitarEspaciosBlanco) {
		return quitarEspaciosBlanco 
				? obtenerLongitudCadena(quitarEspacioEnBlanco(valor))
						: obtenerLongitudCadena(valor);	
	}
	
	public boolean obtenerLongitudCadenaEsValida(String valor, int longitudInicial, int longitudFinal, 
			boolean quitarEspaciosBlanco) {
		var valorSanitizado = quitarEspaciosBlanco ? quitarEspacioEnBlanco(valor) : valor;
		
		return obtenerLongitudCadena(valorSanitizado) >= longitudInicial
				&& obtenerLongitudCadena(valorSanitizado) <= longitudFinal;
		
	}
}
