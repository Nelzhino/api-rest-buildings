package com.constructora.mundoFuturo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.constructora.mundoFuturo.models.PK.MaterialTipoConstruccionPK;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "materiales_tipo_construcciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MaterialTipoConstruccion implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	MaterialTipoConstruccionPK id;

	@ManyToOne
	@MapsId("idSolicitud")
	@JoinColumn(name = "id_solicitud")
	SolicitudConstruccion solicitudConstruccion;

	@ManyToOne
	@MapsId("idTipoConstruccion")
	@JoinColumn(name = "id_tipo_construccion")
	TipoConstruccion tipoConstruccion;

	@ManyToOne
	@MapsId("idMaterial")
	@JoinColumn(name = "id_material")
	Material material;

	@Column(name = "cantidad")
	Integer cantidad;
}
