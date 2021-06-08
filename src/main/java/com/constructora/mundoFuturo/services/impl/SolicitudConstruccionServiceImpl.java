package com.constructora.mundoFuturo.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constructora.mundoFuturo.dto.ResponseDTO;
import com.constructora.mundoFuturo.dto.SolicitudConstruccionDTO;
import com.constructora.mundoFuturo.enums.EstadoEnum;
import com.constructora.mundoFuturo.exceptions.AplicacionException;
import com.constructora.mundoFuturo.mapper.ISolicitudConstruccionMapper;
import com.constructora.mundoFuturo.models.Arquitecto;
import com.constructora.mundoFuturo.models.SolicitudConstruccion;
import com.constructora.mundoFuturo.models.TipoConstruccion;
import com.constructora.mundoFuturo.repository.IArquitectoRepository;
import com.constructora.mundoFuturo.repository.IOrdenConstruccionRepository;
import com.constructora.mundoFuturo.repository.ISolicitudContruccionRepository;
import com.constructora.mundoFuturo.repository.ITipoConstruccionRepository;
import com.constructora.mundoFuturo.services.IMaterialTipoContruccionService;
import com.constructora.mundoFuturo.services.IProyectoService;
import com.constructora.mundoFuturo.services.ISolicitudConstruccionService;

@Service
@Transactional
public class SolicitudConstruccionServiceImpl implements ISolicitudConstruccionService {

	@Autowired
	private ISolicitudContruccionRepository solicitudContruccionRepository;

	@Autowired
	private ISolicitudConstruccionMapper solicitudConstruccionMapper;

	@Autowired
	private IArquitectoRepository arquitectoRepository;

	@Autowired
	private IProyectoService proyectoService;

	@Autowired
	private ITipoConstruccionRepository tipoConstruccionRepository;

	@Autowired
	private IOrdenConstruccionRepository orderContruccionRepository;
	
	@Autowired
	private IMaterialTipoContruccionService materialTipoConstruccionService;

	@Value("${dia.inicio}")
	private int diaInicial;

	@Value("${hora.inicio}")
	private int horaInicial;

	@Value("${hora.fin}")
	private int horaFin;

	private static final Logger logger = LogManager.getLogger(SolicitudConstruccionServiceImpl.class);

	@Transactional(rollbackFor = { AplicacionException.class })
	@Override
	public ResponseDTO generar(SolicitudConstruccionDTO solicitudConstruccionDTO) throws AplicacionException {
		int codigo = 0;
		String mensaje = "";
		if (solicitudConstruccionDTO != null && solicitudConstruccionDTO.getCedula() != null
				&& solicitudConstruccionDTO.getTipoContrucccion() != null) {

			if (!this.solicitudContruccionRepository.existsSolicitudConstruccionByCoordenadaXAndCoordenadaY(
					solicitudConstruccionDTO.getCoordenadaX(), solicitudConstruccionDTO.getCoordenadaY())) {
				Arquitecto arquitecto = this.arquitectoRepository.findByCedula(solicitudConstruccionDTO.getCedula());
				TipoConstruccion tipoConstruccion = this.tipoConstruccionRepository
						.findByNombre(solicitudConstruccionDTO.getTipoContrucccion().name());
				Date fechaFinalUltimaSolicitud = solicitudContruccionRepository.findMaxFechaFinal();

				solicitudConstruccionDTO.setArquitecto(arquitecto);

				Calendar cal = Calendar.getInstance();
				solicitudConstruccionDTO.setFechaCreacion(cal.getTime());

				if (fechaFinalUltimaSolicitud != null) {
					cal.setTime(fechaFinalUltimaSolicitud);
				}

				cal.add(Calendar.DATE, diaInicial);
				solicitudConstruccionDTO.setFechaInicio(cal.getTime());
				cal.add(Calendar.DATE, Integer.parseInt(tipoConstruccion.getTiempoTerminado()));
				solicitudConstruccionDTO.setFechaFinal(cal.getTime());

				SolicitudConstruccion solicitudConstruccion = this.solicitudContruccionRepository
						.save(solicitudConstruccionMapper.convertirDtoAEntidad(solicitudConstruccionDTO));
				if (solicitudConstruccion.getIdSolicitud() != null) {
					solicitudConstruccionDTO.setSolicitudConstruccion(solicitudConstruccion);
					this.materialTipoConstruccionService.registrar(solicitudConstruccionDTO);
					this.proyectoService.actualizar(solicitudConstruccionDTO.getFechaFinal());
					codigo = 1;
					mensaje = String.format("Se generó la solicitud %s exitosamente.",
							solicitudConstruccion.getIdSolicitud());
				}
			} else {
				mensaje = "No se pudo crear la solicitud.";
			}
		} else {
			mensaje = "Faltan campos que son requeridos";
		}

		return new ResponseDTO(codigo, mensaje);
	}

	@Override
	public void buscarFechaInicio() throws AplicacionException {
		Calendar cal = Calendar.getInstance();

		logger.info(String.format("Comienza el proceso de busqueda de solicitudes: %s", cal.getTime()));
		List<SolicitudConstruccion> solicitudes = this.solicitudContruccionRepository
				.findByFechaIni(cal.getTime());
		this.actualizarEstadoOrdenContruccion(EstadoEnum.PROGRESO.name(), solicitudes);
	}

	@Override
	public void buscarFechaFinal() throws AplicacionException {
		Calendar cal = Calendar.getInstance();

		logger.info(String.format("Comienza el proceso de busqueda de solicitudes: %s", cal.getTime()));
		List<SolicitudConstruccion> solicitudes = this.solicitudContruccionRepository
				.findByFechaFin(cal.getTime());
		this.actualizarEstadoOrdenContruccion(EstadoEnum.FINALIZADO.name(), solicitudes);
	}

	private void actualizarEstadoOrdenContruccion(String estado, List<SolicitudConstruccion> solicitudes) {
		if (solicitudes != null && !solicitudes.isEmpty()) {
			this.orderContruccionRepository.updateOrdenConstruccionByIdSolicitud(estado, solicitudes);
		} else {
			logger.info("No hay solicitudes para actualizar en este momento.");
		}
	}

}