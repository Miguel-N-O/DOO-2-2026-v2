package co.edu.uco.libreriauco.negocio.fachada.assembler.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.dto.PaisDTO;
import co.edu.uco.libreriauco.negocio.fachada.assembler.DTOAssembler;
import co.edu.uco.libreriauco.transversal.utilitarios.UtilObjeto;


public class PaisDTOAssembler implements DTOAssembler<PaisDominio, PaisDTO>{
	
	
	private static final DTOAssembler<PaisDominio, PaisDTO> instancia = new PaisDTOAssembler();
	
	private PaisDTOAssembler() {
		
	}
	
	public static DTOAssembler<PaisDominio, PaisDTO> getInstance() {
		return instancia;
	}

	@Override
	public PaisDTO convertirADTO(PaisDominio dominio) {
		var dominioTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new PaisDominio.Builder().build());
		
		var dto = new PaisDTO();
		dto.setId(dominioTmp.getId());
		dto.setNombre(dominioTmp.getNombre());
		
		return dto;
	}

	@Override
	public PaisDominio convertirADominio(PaisDTO dto) {
		var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new PaisDTO());
		
		return new PaisDominio.Builder().id(dtoTmp.getId()).nombre(dtoTmp.getNombre()).build();
	}

	@Override
	public List<PaisDTO> convertirADTO(List<PaisDominio> listaDominios) {
		var listaTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaDominios, new ArrayList<PaisDominio>());

		return listaTmp.stream().map(this::convertirADTO).toList();
	}

	
}

