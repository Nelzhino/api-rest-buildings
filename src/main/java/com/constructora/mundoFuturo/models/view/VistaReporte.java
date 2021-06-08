package com.constructora.mundoFuturo.models.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reporte_proceso_contruccion")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class VistaReporte implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "numero_solicitud")
	Long numeroSolicitud;
	
	@Column(name = "construccion")
	String construccion;
	
	@Column(name = "fecha_inicio")
	Date fechaInicio;
	
	@Column(name = "fecha_fin")
	Date fechaFin;
	
	@Column(name = "estado")
	String estado;

}
