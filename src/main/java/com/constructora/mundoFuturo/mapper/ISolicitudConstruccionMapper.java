package com.constructora.mundoFuturo.mapper;

import com.constructora.mundoFuturo.dto.SolicitudConstruccionDTO;
import com.constructora.mundoFuturo.models.SolicitudConstruccion;

public interface ISolicitudConstruccionMapper {

	
	SolicitudConstruccion convertirDtoAEntidad(SolicitudConstruccionDTO solicitudConstruccionDTO);
}
