package com.constructora.mundoFuturo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.constructora.mundoFuturo.models.view.VistaReporte;

@Repository
public interface VistaReporteRepository extends JpaRepository<VistaReporte, Long>{

}
