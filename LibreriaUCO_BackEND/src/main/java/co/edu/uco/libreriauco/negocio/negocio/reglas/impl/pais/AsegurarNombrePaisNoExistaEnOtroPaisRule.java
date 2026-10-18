package co.edu.uco.libreriauco.negocio.negocio.reglas.impl.pais;

import java.util.UUID;

import co.edu.uco.libreriauco.dao.factoria.DAOFactory;
import co.edu.uco.libreriauco.entidad.PaisEntidad;
import co.edu.uco.libreriauco.negocio.negocio.reglas.Rule;
import co.edu.uco.libreriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.transversal.excepciones.LibreriaUCONegocioException;

public class AsegurarNombrePaisNoExistaEnOtroPaisRule implements Rule<Object> {

	private static final Rule<Object> instancia = new AsegurarNombrePaisNoExistaEnOtroPaisRule();

	private AsegurarNombrePaisNoExistaEnOtroPaisRule() {
	}

	public static final Rule<Object> obtenerInstancia() {
		return instancia;
	}

	@Override
	public void ejecutar(Object... datos) {

		var id = (UUID) datos[0];
		var nombrePais = (String) datos[1];
		var daoFactory = (DAOFactory) datos[2];

		var entidadFiltro = new PaisEntidad();
		entidadFiltro.setNombre(nombrePais);

		var resultados = daoFactory.obtenerPaisDAO().consultarPorFiltro(entidadFiltro);

		for (PaisEntidad resultado : resultados) {
			if (!resultado.getId().equals(id)) {
				var mensajeUsuario = CatalogoMensajes.PaisNegocioImpl.PAIS_EXISTE_CON_EL_MISMO_NOMBRE_DE_PAIS_A_MODIFICAR;
				throw LibreriaUCONegocioException.crear(mensajeUsuario);
			}
		}

	}

}
