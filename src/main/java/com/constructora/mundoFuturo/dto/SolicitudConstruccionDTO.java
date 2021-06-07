package com.constructora.mundoFuturo.dto;

import java.util.Date;

import com.constructora.mundoFuturo.enums.TipoContruccionEnum;
import com.constructora.mundoFuturo.models.Arquitecto;
import com.constructora.mundoFuturo.models.SolicitudConstruccion;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SolicitudConstruccionDTO {

	Long idSolicitud;

	Integer coordenadaX;

	Integer coordenadaY;
	
	String cedula;
	
	Arquitecto arquitecto;
	
	TipoContruccionEnum tipoContrucccion;
	
	SolicitudConstruccion solicitudConstruccion;
	
	Date fechaCreacion;
	
	Date fechaInicio;
	
	Date fechaFinal;

}
