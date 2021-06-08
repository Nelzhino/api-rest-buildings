
package com.constructora.mundoFuturo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.constructora.mundoFuturo.dto.ResponseDTO;
import com.constructora.mundoFuturo.dto.SolicitudConstruccionDTO;
import com.constructora.mundoFuturo.exceptions.AplicacionException;
import com.constructora.mundoFuturo.services.IReporteService;
import com.constructora.mundoFuturo.services.ISolicitudConstruccionService;

@RestController
@RequestMapping(value = "/solicitud")
public class SolicitudContruccionController {

	@Autowired
	private ISolicitudConstruccionService solicitudConstruccionService;
	
	@Autowired
	private IReporteService reporteService;


	@PostMapping("/generar")
	public ResponseDTO generar(@RequestBody SolicitudConstruccionDTO solicitudConstruccionDTO) throws AplicacionException {
		return this.solicitudConstruccionService.generar(solicitudConstruccionDTO);
	}

	
	@GetMapping("/exportar/reporte")
	public void exportarReporte(HttpServletResponse response) {
		this.reporteService.generarReporteSolicitudConstruccion(response);
	}
	
	@Scheduled(cron = "${cron.expression.inicio}")
	public void validarInicioSolicitudes() throws AplicacionException {
		this.solicitudConstruccionService.buscarFechaInicio();
	}
	
	@Scheduled(cron = "${cron.expression.final}")
	public void validarFinalSolicitudes() throws AplicacionException {
		this.solicitudConstruccionService.buscarFechaFinal();
	}

}
