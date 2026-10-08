package co.edu.uco.libreriauco.negocio.negocio.reglas.impl.pais;

import co.edu.uco.libreriauco.dao.factoria.DAOFactory;
import co.edu.uco.libreriauco.entidad.PaisEntidad;
import co.edu.uco.libreriauco.negocio.negocio.reglas.Rule;
import co.edu.uco.libreriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCONegocioException;
import co.edu.uco.libreriauco.entidad.PaisEntidad;
import co.edu.uco.libreriauco.negocio.negocio.reglas.Rule;
import co.edu.uco.libreriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCONegocioException;

public class AsegurarNombreNuevoPaisNoExistaRule implements Rule<Object> {

	private static final  Rule<Object> instancia = new AsegurarNombreNuevoPaisNoExistaRule();

	private AsegurarNombreNuevoPaisNoExistaRule() {	
	}

	public static final Rule<Object> obtenerInstancia() {
		return instancia;
	}

	@Override
	public void ejecutar(Object... datos) {

		var nombrePais = (String) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var entidadFiltro = new PaisEntidad();
		entidadFiltro.setNombre(nombrePais);

		var resultados = daoFactory.obtenerPaisDAO().consultarPorFiltro(entidadFiltro);

		if (!resultados.isEmpty()) {
			var mensajeUsuario = CatalogoMensajes.PaisNegocioImpl.PAIS_EXISTE_CON_EL_MISMO_NOMBRE_DE_PAIS_A_CREAR;
			throw LibreriaUCONegocioException.crear(mensajeUsuario);
		}

	}

}