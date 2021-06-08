package com.constructora.mundoFuturo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.constructora.mundoFuturo.models.Proyecto;
import com.constructora.mundoFuturo.services.IProyectoService;

@RestController
@RequestMapping(value = "/proyecto")
public class ProyectoController {

	@Autowired
	private IProyectoService proyectoService;
	
	@GetMapping("/verFechaFinalizacion")
	public Proyecto verFechaFinalizacion() {
		return this.proyectoService.verFechaFinal();
	}
}
