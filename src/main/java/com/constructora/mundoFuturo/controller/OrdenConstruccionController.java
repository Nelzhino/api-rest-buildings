package com.constructora.mundoFuturo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.constructora.mundoFuturo.dto.OrdenConstruccionDTO;
import com.constructora.mundoFuturo.dto.ResponseDTO;
import com.constructora.mundoFuturo.services.IOrdenConstruccionService;


@RestController
@RequestMapping(value = "/orden")
public class OrdenConstruccionController {
	
	@Autowired
	private IOrdenConstruccionService ordenConstruccionService;
	
	@PostMapping("/generar")
	public ResponseDTO generarOrden(@RequestBody OrdenConstruccionDTO ordenConstruccionDTO) {
		return this.ordenConstruccionService.registrar(ordenConstruccionDTO);
	}

}
