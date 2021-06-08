package com.constructora.mundoFuturo.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constructora.mundoFuturo.dto.OrdenConstruccionDTO;
import com.constructora.mundoFuturo.dto.ResponseDTO;
import com.constructora.mundoFuturo.enums.EstadoEnum;
import com.constructora.mundoFuturo.exceptions.AplicacionException;
import com.constructora.mundoFuturo.models.OrdenConstruccion;
import com.constructora.mundoFuturo.models.SolicitudConstruccion;
import com.constructora.mundoFuturo.repository.IOrdenConstruccionRepository;
import com.constructora.mundoFuturo.repository.ISolicitudContruccionRepository;
import com.constructora.mundoFuturo.services.IOrdenConstruccionService;

@Service
@Transactional
public class OrdenConstruccionServiceImpl implements IOrdenConstruccionService{

	@Autowired
	private IOrdenConstruccionRepository ordenConstruccionRepository;
	
	@Autowired
	private ISolicitudContruccionRepository solicitudContruccionRepository;
	
	@Override
	@Transactional(rollbackFor = { AplicacionException.class })
	public ResponseDTO registrar(OrdenConstruccionDTO ordenConstruccionDTO) throws AplicacionException {
		Optional<SolicitudConstruccion> optionalSolicitudConstruccion =  this.solicitudContruccionRepository.findById(ordenConstruccionDTO.getIdSolicitudConstruccion());
		int codigo = 0;
		String mensaje = "";
		if(optionalSolicitudConstruccion.isPresent()) {
			SolicitudConstruccion solicitudConstruccion = optionalSolicitudConstruccion.get();
			int cantidadOrden = this.ordenConstruccionRepository.countOrdenContruccionByEstados(solicitudConstruccion.getIdSolicitud());
			if(cantidadOrden == 0) {
				OrdenConstruccion ordenConstruccion = new OrdenConstruccion();
				ordenConstruccion.setSolicitudConstruccion(solicitudConstruccion);
				ordenConstruccion.setEstado(EstadoEnum.PENDIENTE.name());
				this.ordenConstruccionRepository.save(ordenConstruccion);
				codigo = 1;
				mensaje = "Se generó la solicitud exitosamente.";				
			} else {
				mensaje = "Ya existe una orden para esta solicitud";
			}
		} else {
			throw new AplicacionException("No pudo crear la orden, no existe una solicitud.");
		}
		
		return new ResponseDTO(codigo, mensaje);
	}

}
