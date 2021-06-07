package com.constructora.mundoFuturo.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.constructora.mundoFuturo.models.SolicitudConstruccion;

@Repository
public interface ISolicitudContruccionRepository extends JpaRepository<SolicitudConstruccion, Long>{

	boolean existsSolicitudConstruccionByCoordenadaXAndCoordenadaY(Integer coordenadaX, Integer coordenadaY);
	
	@Query("select max(sc.fechaFinal) from SolicitudConstruccion sc")
	Date findMaxFechaFinal();
	
	@Query("select min(sc.fechaInicio) from SolicitudConstruccion sc")
	Date findMinFechaInicial();

	
}
