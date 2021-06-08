package com.constructora.mundoFuturo.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constructora.mundoFuturo.models.Proyecto;
import com.constructora.mundoFuturo.repository.IProyectoRepository;
import com.constructora.mundoFuturo.services.IProyectoService;

@Service
@Transactional
public class ProyectoServiceImpl implements IProyectoService{

	@Value("${proyecto.default}")
	private Long idProyecto;
	
	@Autowired
	private IProyectoRepository proyectoRepository;
	
	@Override
	public void actualizar(Date fechaFinal) {
		Optional<Proyecto> optionalProyecto = this.proyectoRepository.findById(idProyecto);
		Proyecto proyecto = optionalProyecto.get();
		proyecto.setFechaFinal(fechaFinal);
		
		this.proyectoRepository.save(proyecto);
	}

	@Override
	public Proyecto verFechaFinal() {
		Optional<Proyecto> proyecto = proyectoRepository.findById(idProyecto);
		return proyecto.get();
	}

}
