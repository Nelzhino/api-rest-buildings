package com.constructora.mundoFuturo.services;

import com.constructora.mundoFuturo.dto.OrdenConstruccionDTO;
import com.constructora.mundoFuturo.dto.ResponseDTO;
import com.constructora.mundoFuturo.exceptions.AplicacionException;

public interface IOrdenConstruccionService {
	
	
	ResponseDTO registrar(OrdenConstruccionDTO oriConstruccionDTO) throws AplicacionException;

}
