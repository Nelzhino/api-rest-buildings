package com.constructora.mundoFuturo.models.PK;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MaterialTipoConstruccionPK implements Serializable{
	
	
	private static final long serialVersionUID = 3566221897501028672L;

	@Column(name = "id_solicitud")
	Long idSolicitud;
	
	@Column(name = "id_tipo_constrccion")
	Long idTipoConstruccion;
	
	@Column(name = "id_material")
	Long idMaterial;
	

}
