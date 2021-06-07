package com.constructora.mundoFuturo.services.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.constructora.mundoFuturo.dto.ResponseDTO;
import com.constructora.mundoFuturo.dto.SolicitudConstruccionDTO;
import com.constructora.mundoFuturo.mapper.ISolicitudConstruccionMapper;
import com.constructora.mundoFuturo.models.Arquitecto;
import com.constructora.mundoFuturo.models.SolicitudConstruccion;
import com.constructora.mundoFuturo.models.TipoConstruccion;
import com.constructora.mundoFuturo.repository.IArquitectoRepository;
import com.constructora.mundoFuturo.repository.IProyectoRepository;
import com.constructora.mundoFuturo.repository.ISolicitudContruccionRepository;
import com.constructora.mundoFuturo.repository.ITipoConstruccionRepository;
import com.constructora.mundoFuturo.services.IMaterialTipoContruccionService;
import com.constructora.mundoFuturo.services.IProyectoService;
import com.constructora.mundoFuturo.services.ISolicitudConstruccionService;


@Service
public class SolicitudConstruccionServiceImpl implements ISolicitudConstruccionService{

	@Autowired
	private ISolicitudContruccionRepository solicitudContruccionRepository;
	
	@Autowired
	private ISolicitudConstruccionMapper solicitudConstruccionMapper;
	
	@Autowired
	private IArquitectoRepository arquitectoRepository;
	
	@Autowired
	private IMaterialTipoContruccionService materialTipoConstruccionService;
	
	@Autowired
	private IProyectoService proyectoService;
	
	@Autowired
	private ITipoConstruccionRepository tipoConstruccionRepository;
	
	
	@Value("${dia.inicio}")
	private int diaInicial;
	
	@Value("${hora.inicio}")
	private int horaInicial;
	
	@Value("${hora.fin}")
	private int horaFin;
	
	
	@Override
	public ResponseDTO generar(SolicitudConstruccionDTO solicitudConstruccionDTO) {
		int codigo = 0;
		String mensaje = "";
		if(solicitudConstruccionDTO != null && solicitudConstruccionDTO.getCedula() != null 
				&& solicitudConstruccionDTO.getTipoContrucccion() != null) {
			
			if(!this.solicitudContruccionRepository.existsSolicitudConstruccionByCoordenadaXAndCoordenadaY(solicitudConstruccionDTO.getCoordenadaX(), solicitudConstruccionDTO.getCoordenadaY())) {				
				Arquitecto arquitecto = this.arquitectoRepository.findByCedula(solicitudConstruccionDTO.getCedula());
				TipoConstruccion tipoConstruccion = this.tipoConstruccionRepository.findByNombre(solicitudConstruccionDTO.getTipoContrucccion().name());
				Date fechaFinalUltimaSolicitud =  solicitudContruccionRepository.findMaxFechaFinal();
				
				solicitudConstruccionDTO.setArquitecto(arquitecto);
				
				Calendar cal = Calendar.getInstance();
				solicitudConstruccionDTO.setFechaCreacion(cal.getTime());	

				if(fechaFinalUltimaSolicitud != null) {
					cal.setTime(fechaFinalUltimaSolicitud);
				}
				
				
				this.transformarFechas(cal, diaInicial, horaInicial);
				solicitudConstruccionDTO.setFechaInicio(cal.getTime());
				this.transformarFechas(cal, 
						Integer.parseInt(tipoConstruccion.getTiempoTerminado()), horaFin);
				solicitudConstruccionDTO.setFechaFinal(cal.getTime());

				
				SolicitudConstruccion solicitudConstruccion = this.solicitudContruccionRepository.save(solicitudConstruccionMapper.convertirDtoAEntidad(solicitudConstruccionDTO));	
				if(solicitudConstruccion.getIdSolicitud() != null) {
					solicitudConstruccionDTO.setSolicitudConstruccion(solicitudConstruccion);
					this.materialTipoConstruccionService.registrar(solicitudConstruccionDTO);
					
					this.proyectoService.actualizar(solicitudConstruccionDTO.getFechaFinal());
					codigo = 1;
					mensaje = String.format("Se generó la solicitud %s exitosamente.", solicitudConstruccion.getIdSolicitud());
				}
			} else 
			{
				mensaje = "No se pudo crear la solicitud.";
			}
		} else {
			mensaje = "Faltan campos que son requeridos";
		}
		
		return new ResponseDTO(codigo, mensaje);
	}
	
	
	private void transformarFechas(Calendar cal, int cantidadDias, int hora) {
		cal.add(Calendar.DATE, cantidadDias);
		cal.set(Calendar.HOUR_OF_DAY, hora);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
	}
	
}