package com.constructora.mundoFuturo.services;

import java.util.Date;

import com.constructora.mundoFuturo.models.Proyecto;

public interface IProyectoService {
	
	
	void actualizar(Date fechaFinal);
	
	
	Proyecto verFechaFinal();

}
