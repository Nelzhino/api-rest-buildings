package com.constructora.mundoFuturo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.constructora.mundoFuturo.exceptions.AplicacionException;
import com.constructora.mundoFuturo.models.SolicitudConstruccion;

@Repository
public interface ISolicitudContruccionRepository extends JpaRepository<SolicitudConstruccion, Long> {

	boolean existsSolicitudConstruccionByCoordenadaXAndCoordenadaY(Integer coordenadaX, Integer coordenadaY);

	@Query("select max(sc.fechaFinal) from SolicitudConstruccion sc")
	Date findMaxFechaFinal();

	@Query("select min(sc.fechaInicio) from SolicitudConstruccion sc")
	Date findMinFechaInicial();

	@Query("select sc from SolicitudConstruccion sc where sc.fechaInicio = function('date_format', :fechaIni, '%Y-%m-%d')")
	List<SolicitudConstruccion> findByFechaIni(Date fechaIni) throws AplicacionException;

	@Query("select sc from SolicitudConstruccion sc where sc.fechaFinal = function('date_format', :fechaFin, '%Y-%m-%d')")
	List<SolicitudConstruccion> findByFechaFin(Date fechaFin) throws AplicacionException;


}
