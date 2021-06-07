package com.constructora.mundoFuturo.services;

import com.constructora.mundoFuturo.dto.OrdenConstruccionDTO;
import com.constructora.mundoFuturo.dto.ResponseDTO;

public interface IOrdenConstruccionService {
	
	
	ResponseDTO registrar(OrdenConstruccionDTO oriConstruccionDTO);

}
