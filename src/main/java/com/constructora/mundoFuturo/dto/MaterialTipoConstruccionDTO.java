package com.constructora.mundoFuturo.dto;

import com.constructora.mundoFuturo.models.Material;
import com.constructora.mundoFuturo.models.SolicitudConstruccion;
import com.constructora.mundoFuturo.models.TipoConstruccion;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MaterialTipoConstruccionDTO {

	SolicitudConstruccion solicitudConstruccion;
	
	TipoConstruccion tipoContruccion;
	
	Material material;
	
	Integer cantidad;
	
}
