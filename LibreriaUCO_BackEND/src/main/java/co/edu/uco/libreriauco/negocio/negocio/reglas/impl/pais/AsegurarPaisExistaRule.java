package co.edu.uco.libreriauco.negocio.negocio.reglas.impl.pais;

import java.util.UUID;

import co.edu.uco.libreriauco.dao.factoria.DAOFactory;
import co.edu.uco.libreriauco.negocio.negocio.reglas.Rule;
import co.edu.uco.libreriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCONegocioException;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilUUID;

public class AsegurarPaisExistaRule implements Rule<Object> {

	private static final Rule<Object> instancia = new AsegurarPaisExistaRule();

	private AsegurarPaisExistaRule() {
	}

	public static final Rule<Object> obtenerInstancia() {
		return instancia;
	}

	@Override
	public void ejecutar(Object... datos) {

		var id = (UUID) datos[0];
		var daoFactory = (DAOFactory) datos[1];

		var resultado = daoFactory.obtenerPaisDAO().consultarPorId(id);

		if (UtilUUID.obtenerUUIDDefecto().equals(resultado.getId())) {
			var mensajeUsuario = CatalogoMensajes.PaisNegocioImpl.PAIS_NO_EXISTE;
			throw LibreriaUCONegocioException.crear(mensajeUsuario);
		}

	}

}
