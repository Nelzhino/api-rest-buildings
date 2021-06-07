package com.constructora.mundoFuturo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "parametros")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Parametro {

	@Id
	@Column(name = "id_parametro")
	Long idParametro;
	
	@Column(name = "nombre")
	String nombre;
	
	@Column(name = "descripcion")
	String descripcion;
	
	
}
