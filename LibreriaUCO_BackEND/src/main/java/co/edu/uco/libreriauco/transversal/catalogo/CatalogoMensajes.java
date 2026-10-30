package co.edu.uco.libreriauco.transversal.catalogo;

public class CatalogoMensajes {
	
	private CatalogoMensajes() {
		
	}
	

	public static class UtilSQL{
		
		private UtilSQL() {
			
		}
		
		public static final String USUARIO_ERROR_PROBLEMA_VALIDANDO_SI_CONEXION_SQL_ESTA_ABIERTA = "Se ha presentado un problema tratando de validar si la conexión contra la fuente de información en la cual se iba a tratar de llevar a cabo la operacion deseada estaba o no abierta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_VALIDANDO_SI_CONEXION_SQL_ESTA_ABIERTA = "Se ha presentado un problema NO CONTROLADO tratando de validar si la conexión contra la fuente de información en la cual se iba a tratar de llevar a cabo la operacion deseada estaba o no abierta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_VALIDANDO_SI_TRANSACCION_SQL_ESTA_INICIADA = "Se ha presentado un problema tratando de validar si la conexión contra la fuente de información estaba en un estado consistente al tratar de llevar a cabo la operacion deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_VALIDANDO_SI_TRANSACCION_SQL_ESTA_INICIADA = "Se ha presentado un problema NO CONTROLADO tratando de validar si la conexión contra la fuente de información estaba en un estado consistente al tratar de llevar a cabo la operacion deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_NO_ES_POSIBLE_INICIAR_TRANSACCION_SQL = "No es posible continuar con la operación deseada, debido a que la conexión contra la fuente de información se encuentra en un estado inconsistente porque está cerrada, está vacía o porque la transacción ya fue iniciada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		
		// Mensajes que faltaban:
		
		public static final String USUARIO_ERROR_CONEXION_SQL_NO_ESTA_ABIERTA = "No es posible continuar con la operación deseada, debido a que la conexión contra la fuente de información no se encuentra abierta, ya sea porque está cerrada o porque está vacía. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_NO_ES_POSIBLE_CONFIRMAR_TRANSACCION_SQL = "No es posible confirmar los cambios de la operación deseada, debido a que la conexión contra la fuente de información se encuentra en un estado inconsistente porque está cerrada, está vacía o porque la transacción no fue iniciada previamente. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_NO_ES_POSIBLE_CANCELAR_TRANSACCION_SQL = "No es posible deshacer los cambios de la operación deseada, debido a que la conexión contra la fuente de información se encuentra en un estado inconsistente porque está cerrada, está vacía o porque la transacción no fue iniciada previamente. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_NO_ES_POSIBLE_CERRAR_CONEXION_SQL = "No es posible finalizar de manera adecuada la operación deseada, debido a que la conexión contra la fuente de información que se intentó cerrar ya se encuentra cerrada o está vacía. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		
		// Mensajes de la conexión:
		
		public static final String USUARIO_ERROR_PROBLEMA_INICIANDO_TRANSACCION_SQL = "Se ha presentado un problema tratando de iniciar la transacción contra la fuente de información en la cual se iba a llevar a cabo la operación deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_INICIANDO_TRANSACCION_SQL = "Se ha presentado un problema NO CONTROLADO tratando de iniciar la transacción contra la fuente de información en la cual se iba a llevar a cabo la operación deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_CONFIRMANDO_TRANSACCION_SQL = "Se ha presentado un problema tratando de confirmar los cambios de la operación deseada en la fuente de información. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONFIRMANDO_TRANSACCION_SQL = "Se ha presentado un problema NO CONTROLADO tratando de confirmar los cambios de la operación deseada en la fuente de información. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_CANCELANDO_TRANSACCION_SQL = "Se ha presentado un problema tratando de deshacer los cambios de la operación deseada en la fuente de información. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CANCELANDO_TRANSACCION_SQL = "Se ha presentado un problema NO CONTROLADO tratando de deshacer los cambios de la operación deseada en la fuente de información. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_CERRANDO_CONEXION_SQL = "Se ha presentado un problema tratando de cerrar la conexión contra la fuente de información luego de llevar a cabo la operación deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CERRANDO_CONEXION_SQL = "Se ha presentado un problema NO CONTROLADO tratando de cerrar la conexión contra la fuente de información luego de llevar a cabo la operación deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
	
	}
	
	public static class DAOFactory {
		
		private DAOFactory() {
			
		}
		
		public static final String USUARIO_ERROR_FUENTE_INFORMACION_NO_HABILITADA = "La fuente de información que se solicitó para llevar a cabo la operación deseada no está habilitada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		
	}
	
	public static class SqlServerDAOFactory {
		
		private SqlServerDAOFactory() {
			
		}
		
		public static final String USUARIO_ERROR_PROBLEMA_ABRIENDO_CONEXION_SQL = "Se ha presentado un problema tratando de abrir la conexión contra la fuente de información en la cual se iba a llevar a cabo la operación deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ABRIENDO_CONEXION_SQL = "Se ha presentado un problema NO CONTROLADO tratando de abrir la conexión contra la fuente de información en la cual se iba a llevar a cabo la operación deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		
	}
	
	public static class PaisSqlServerDAO {
		
		private PaisSqlServerDAO() {
			
		}
		
		public static final String USUARIO_ERROR_PROBLEMA_CREANDO_PAIS = "Se ha presentado un problema tratando de crear el país en la fuente de información. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_PAIS = "Se ha presentado un problema NO CONTROLADO tratando de crear el país en la fuente de información. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_PAIS = "Se ha presentado un problema tratando de consultar la información de los países en la fuente de información. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PAIS = "Se ha presentado un problema NO CONTROLADO tratando de consultar la información de los países en la fuente de información. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_PAIS = "Se ha presentado un problema tratando de actualizar el país en la fuente de información. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_PAIS = "Se ha presentado un problema NO CONTROLADO tratando de actualizar el país en la fuente de información. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_ELIMINANDO_PAIS = "Se ha presentado un problema tratando de eliminar el país en la fuente de información. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_PAIS = "Se ha presentado un problema NO CONTROLADO tratando de eliminar el país en la fuente de información. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		
	}
	
	public static class DepartamentoSqlServerDAO {
		
		private DepartamentoSqlServerDAO() {
			
		}
		
		public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_DEPARTAMENTO = "Se ha presentado un problema tratando de consultar la información de los departamentos en la fuente de información. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DEPARTAMENTO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la información de los departamentos en la fuente de información. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		
	}
	
	public static class PaisNegocioImpl {
		private PaisNegocioImpl(){
			
		}
		
		public static final String PAIS_EXISTE_CON_EL_MISMO_NOMBRE_DE_PAIS_A_CREAR = "Ya existe otro país con el nombre con el cual se desea crear el país deseado";
		public static final String NOMBRE_PAIS_OBLIGATORIO = "El nombre del país es obligatorio para llevar a cabo la operación deseada.";
		public static final String LONGITUD_NOMBRE_PAIS_NO_VALIDA = "La longitud del nombre del país no es válida. Asegúrese que esté entre 1 y 50.";
		public static final String FORMATO_PAIS_NO_VALIDO = "El formato del nombre no es válido. Asegúrese de que solamente tenga letras de la A a la Z, mayúsculas o minúsculas y espacios";
		public static final String PAIS_NO_EXISTE = "El país sobre el cual se desea llevar a cabo la operación no existe.";
		public static final String PAIS_EXISTE_CON_EL_MISMO_NOMBRE_DE_PAIS_A_MODIFICAR = "Ya existe otro país con el nombre con el cual se desea modificar el país deseado";
		
	}
	
	public static class PaisFachadaImpl {
		
		private PaisFachadaImpl() {
			
		}
		
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_REGISTRANDO_INFORMACION_NUEVO_PAIS = "Se ha presentado un problema NO CONTROLADO tratando de registrar la información del nuevo país deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_MODIFICANDO_INFORMACION_PAIS_EXISTENTE = "Se ha presentado un problema NO CONTROLADO tratando de modificar la información del país deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_DANDO_BAJA_PAIS_EXISTENTE = "Se ha presentado un problema NO CONTROLADO tratando de dar de baja el país deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PAISES_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la información de los países que cumplen con el filtro deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_PAISES = "Se ha presentado un problema NO CONTROLADO tratando de consultar la información de todos los países. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PAIS_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la información del país deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y reporte la novedad...";
		
	}
	
	public static class AbstractRestController {
		
		private AbstractRestController() {
			
		}
		
		public static final String USUARIO_ERROR_PROBLEMA_INESPERADO = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicación y entréguele el siguiente código: ";
		public static final String USUARIO_ERROR_RECURSO_NO_ENCONTRADO = "El recurso que se intentó utilizar no existe. Por favor verifique la dirección y el método de la petición y si el problema persiste contacte al administrador de la aplicación y entréguele el siguiente código: ";
		public static final String TECNICO_ADVERTENCIA_EXCEPCION_CONTROLADA = "Se presentó una excepción controlada en la capa {}: {}";
		public static final String TECNICO_ERROR_PROBLEMA_INESPERADO = "Se presentó una excepción no controlada. Código de seguimiento: {}";
		public static final String TECNICO_ADVERTENCIA_RECURSO_NO_ENCONTRADO = "Se solicitó un recurso que no existe: {} {}. Código de seguimiento: {}";
		
	}
	
	public static class PaisControlador {
		
		private PaisControlador() {
			
		}
		
		public static final String PAISES_CONSULTADOS_EXITOSAMENTE = "La información de los países se consultó de forma exitosa.";
		public static final String PAIS_REGISTRADO_EXITOSAMENTE = "El país se registró de forma exitosa.";
		public static final String PAIS_MODIFICADO_EXITOSAMENTE = "La información del país se modificó de forma exitosa.";
		public static final String PAIS_DADO_DE_BAJA_EXITOSAMENTE = "El país se dio de baja de forma exitosa.";
		
	}
	
}