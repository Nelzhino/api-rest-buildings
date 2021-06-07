package com.constructora.mundoFuturo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.constructora.mundoFuturo.models.OrdenConstruccion;

@Repository
public interface IOrdenConstruccionRepository extends JpaRepository<OrdenConstruccion, Long>{

	@Query("select count(1) from OrdenConstruccion oc where oc.solicitudConstruccion.idSolicitud = :id ")
	public int countOrdenContruccionByEstados(Long id);
}
