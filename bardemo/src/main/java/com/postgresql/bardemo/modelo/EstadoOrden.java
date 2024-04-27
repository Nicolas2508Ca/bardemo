package com.postgresql.bardemo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Estado_Orden")
public class EstadoOrden {
	
	@Id
	@Column(name = "idestado")
	private Integer idEstado;
	
	@Column(name = "nombreestado", length=50)
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
