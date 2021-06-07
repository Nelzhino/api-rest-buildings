package com.constructora.mundoFuturo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.constructora.mundoFuturo.models.MaterialTipoConstruccion;
import com.constructora.mundoFuturo.models.SolicitudConstruccion;
import com.constructora.mundoFuturo.models.PK.MaterialTipoConstruccionPK;

public interface IMaterialTipoConstruccionRepository extends JpaRepository<MaterialTipoConstruccion, MaterialTipoConstruccionPK>{

	void deleteBySolicitudConstruccion(SolicitudConstruccion solicitud);
}
