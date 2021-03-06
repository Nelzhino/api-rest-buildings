package com.constructora.mundoFuturo.dto;

import java.util.List;

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

	Long idSolicitud;
	
	String contruccion;
	
	List<MaterialDTO> materiales;
}
