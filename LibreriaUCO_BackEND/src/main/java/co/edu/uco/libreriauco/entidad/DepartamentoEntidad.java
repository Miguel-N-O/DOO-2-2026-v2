package co.edu.uco.libreriauco.entidad;
import java.util.UUID;

import co.edu.uco.libreriauco.dto.PaisDTO;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilTexto;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilUUID;

public class DepartamentoEntidad {
 
	
	
	private PaisDTO paisdto;
	private UUID id;
	private String nombre;
	
	
	public DepartamentoEntidad() {
		setId(UtilUUID.obtenerUUIDDefecto());
		setNombre(UtilTexto.VACIO);
		setPaisdto(new PaisDTO());
	}
	
	public PaisDTO getPaisdto() {
		return paisdto;
	}
	public void setPaisdto(PaisDTO paisdto) {
		this.paisdto = paisdto;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = UtilTexto.getUtilTexto().quitarEspacioEnBlanco(nombre);
	}
	
	
	
}


