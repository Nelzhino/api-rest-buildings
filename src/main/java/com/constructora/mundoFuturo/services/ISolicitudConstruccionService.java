package com.constructora.mundoFuturo.services;

import com.constructora.mundoFuturo.dto.ResponseDTO;
import com.constructora.mundoFuturo.dto.SolicitudConstruccionDTO;

public interface ISolicitudConstruccionService {

	ResponseDTO generar(SolicitudConstruccionDTO solicitudConstruccionDTO);
}
