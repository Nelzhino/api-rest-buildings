package com.constructora.mundoFuturo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.constructora.mundoFuturo.models.Parametro;

@Repository
public interface IParametroRepository extends JpaRepository<Parametro, Long>{

	
	public List<Parametro> findByNombreStartsWith (String nombre); 
}
