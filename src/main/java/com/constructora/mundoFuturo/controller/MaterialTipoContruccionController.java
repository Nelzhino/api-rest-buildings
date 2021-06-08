package com.constructora.mundoFuturo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.constructora.mundoFuturo.dto.MaterialTipoConstruccionDTO;
import com.constructora.mundoFuturo.dto.ResponseDTO;
import com.constructora.mundoFuturo.exceptions.AplicacionException;
import com.constructora.mundoFuturo.services.IMaterialTipoContruccionService;

@RestController
@RequestMapping(value = "/material")
public class MaterialTipoContruccionController {

	
	@Autowired
	private IMaterialTipoContruccionService materialTipoContruccionService;
	
	@PostMapping("/generar")
	public ResponseDTO generar(@RequestBody MaterialTipoConstruccionDTO materialTipoConstruccionDTO) throws AplicacionException {
		return this.materialTipoContruccionService.registrar(materialTipoConstruccionDTO);
	}
	
}
