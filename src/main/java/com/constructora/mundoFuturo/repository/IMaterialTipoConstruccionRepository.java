package com.constructora.mundoFuturo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.constructora.mundoFuturo.models.MaterialTipoConstruccion;
import com.constructora.mundoFuturo.models.SolicitudConstruccion;
import com.constructora.mundoFuturo.models.PK.MaterialTipoConstruccionPK;

public interface IMaterialTipoConstruccionRepository extends JpaRepository<MaterialTipoConstruccion, MaterialTipoConstruccionPK>{

	void deleteBySolicitudConstruccion(SolicitudConstruccion solicitud);
	
	List<MaterialTipoConstruccion> findBySolicitudConstruccion(SolicitudConstruccion solicitudConstruccion);
	
}
