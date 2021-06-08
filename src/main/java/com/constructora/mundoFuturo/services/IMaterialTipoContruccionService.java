package com.constructora.mundoFuturo.services;

import com.constructora.mundoFuturo.dto.MaterialTipoConstruccionDTO;
import com.constructora.mundoFuturo.dto.ResponseDTO;
import com.constructora.mundoFuturo.dto.SolicitudConstruccionDTO;
import com.constructora.mundoFuturo.exceptions.AplicacionException;

public interface IMaterialTipoContruccionService {
	
	void registrar(SolicitudConstruccionDTO solicitudConstruccionDTO);
	
	ResponseDTO registrar(MaterialTipoConstruccionDTO materialTipoConstruccionDTO) throws AplicacionException;

}
