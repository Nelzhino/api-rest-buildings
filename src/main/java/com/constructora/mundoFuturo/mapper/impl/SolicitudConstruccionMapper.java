package com.constructora.mundoFuturo.mapper.impl;

import org.springframework.stereotype.Service;

import com.constructora.mundoFuturo.dto.SolicitudConstruccionDTO;
import com.constructora.mundoFuturo.mapper.ISolicitudConstruccionMapper;
import com.constructora.mundoFuturo.models.SolicitudConstruccion;

@Service
public class SolicitudConstruccionMapper implements ISolicitudConstruccionMapper{


	@Override
	public SolicitudConstruccion convertirDtoAEntidad(SolicitudConstruccionDTO solicitudConstruccionDTO) {
		SolicitudConstruccion solicitudConstruccion = new SolicitudConstruccion();
		
	
		solicitudConstruccion.setArquitecto(solicitudConstruccionDTO.getArquitecto());
		solicitudConstruccion.setCoordenadaX(solicitudConstruccionDTO.getCoordenadaX());
		solicitudConstruccion.setCoordenadaY(solicitudConstruccionDTO.getCoordenadaY());
		solicitudConstruccion.setFechaCreacion(solicitudConstruccionDTO.getFechaCreacion());
		solicitudConstruccion.setFechaInicio(solicitudConstruccionDTO.getFechaInicio());
		solicitudConstruccion.setFechaFinal(solicitudConstruccionDTO.getFechaFinal());
		
		return solicitudConstruccion;
	}

	
}
