package com.postgresql.bardemo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Estado_Orden")
public class EstadoOrden {
	
	@Id
	@Column(name = "Id_Estado")
	private Integer idEstado;
	
	@Column(name = "Nombre_Estado", length=50)
	private String nombreEstado;
	
	public EstadoOrden() {
		
	}

	
	public EstadoOrden(Integer idEstado, String nombreEstado) {
		super();
		this.idEstado = idEstado;
		this.nombreEstado = nombreEstado;
	}


	public Integer getIdEstado() {
		return idEstado;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}
	
	
	
}
