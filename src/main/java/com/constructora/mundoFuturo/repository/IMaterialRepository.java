package com.constructora.mundoFuturo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.constructora.mundoFuturo.models.Material;

public interface IMaterialRepository extends JpaRepository<Material, Long> {
	
	List<Material> findByNombre(String nombre);
	
	List<Material> findByNomenclatura(String nomenclatura);
}
