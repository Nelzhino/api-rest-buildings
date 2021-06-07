package com.constructora.mundoFuturo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.constructora.mundoFuturo.models.TipoConstruccion;

public interface ITipoConstruccionRepository extends JpaRepository<TipoConstruccion, Long>{

	TipoConstruccion findByNombre(String nombre);
}
