package com.constructora.mundoFuturo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "solicitudes_construcciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SolicitudConstruccion  {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_solicitud")
	Long idSolicitud;
	
	@Column(name = "coordenada_x")
	Integer coordenadaX;
	
	@Column(name = "coordenada_y")
	Integer coordenadaY;
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "idArquitecto")
	Arquitecto arquitecto;
	
	
	@Column(name = "fecha_creacion")
	Date fechaCreacion;
	
	@Column(name = "fecha_inicio")
	Date fechaInicio;
	
	@Column(name = "fecha_fin")
	Date fechaFinal;
	

}
