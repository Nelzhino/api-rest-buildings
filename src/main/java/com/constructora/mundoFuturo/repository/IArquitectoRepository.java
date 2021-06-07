package com.constructora.mundoFuturo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.constructora.mundoFuturo.models.Arquitecto;

public interface IArquitectoRepository extends JpaRepository<Arquitecto, Long>{

	Arquitecto findByCedula(String cedula);
}
