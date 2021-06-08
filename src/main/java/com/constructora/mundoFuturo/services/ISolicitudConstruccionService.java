package com.constructora.mundoFuturo.services;

import com.constructora.mundoFuturo.dto.ResponseDTO;
import com.constructora.mundoFuturo.dto.SolicitudConstruccionDTO;
import com.constructora.mundoFuturo.exceptions.AplicacionException;

public interface ISolicitudConstruccionService {

	ResponseDTO generar(SolicitudConstruccionDTO solicitudConstruccionDTO) throws AplicacionException;
	
	void buscarFechaInicio() throws AplicacionException ;

	void buscarFechaFinal() throws AplicacionException ;
	
	
}
