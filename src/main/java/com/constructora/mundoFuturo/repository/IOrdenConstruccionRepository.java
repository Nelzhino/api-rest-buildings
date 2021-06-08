package com.constructora.mundoFuturo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.constructora.mundoFuturo.models.OrdenConstruccion;
import com.constructora.mundoFuturo.models.SolicitudConstruccion;

@Repository
public interface IOrdenConstruccionRepository extends JpaRepository<OrdenConstruccion, Long> {

	@Query("select count(1) from OrdenConstruccion oc where oc.solicitudConstruccion.idSolicitud = :id ")
	int countOrdenContruccionByEstados(Long id);

	@Transactional
	@Modifying
	@Query("update OrdenConstruccion set estado = :estadoOrden where solicitudConstruccion in (:id)")
	void updateOrdenConstruccionByIdSolicitud(String estadoOrden, List<SolicitudConstruccion> id);

}
