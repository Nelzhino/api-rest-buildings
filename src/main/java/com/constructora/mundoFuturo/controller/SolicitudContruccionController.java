
package com.constructora.mundoFuturo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.constructora.mundoFuturo.dto.MaterialTipoConstruccionDTO;
import com.constructora.mundoFuturo.dto.ResponseDTO;
import com.constructora.mundoFuturo.dto.SolicitudConstruccionDTO;
import com.constructora.mundoFuturo.services.IMaterialTipoContruccionService;
import com.constructora.mundoFuturo.services.ISolicitudConstruccionService;

@RestController
@RequestMapping(value = "/solicitud")
public class SolicitudContruccionController {

	@Autowired
	private ISolicitudConstruccionService solicitudConstruccionService;

	@Autowired
	private IMaterialTipoContruccionService materialTipoConstruccionService;


	@PostMapping("/generar")
	public ResponseDTO generar(@RequestBody SolicitudConstruccionDTO solicitudConstruccionDTO) {
		return this.solicitudConstruccionService.generar(solicitudConstruccionDTO);
	}


	@PostMapping("/actualizarMaterialesTipoContruccion")
	public void actualizarMateriales(@RequestBody MaterialTipoConstruccionDTO materialTipoconstruccionDTO) {

	}
	
	
	@Scheduled(cron = "${cron.expression.inicio}")
	public void validarInicioSolicitudes() {
		long now = System.currentTimeMillis() / 1000;
	    System.out.println(
	      "schedule tasks using cron jobs STARTS - " + now);
	}
	
	@Scheduled(cron = "${cron.expression.final}")
	public void validarFinalSolicitudes() {
		long now = System.currentTimeMillis() / 1000;
	    System.out.println(
	      "schedule tasks using cron jobs END - " + now);
	}

}
