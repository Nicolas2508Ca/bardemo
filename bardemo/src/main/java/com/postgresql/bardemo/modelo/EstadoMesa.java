package com.postgresql.bardemo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Estado_Mesa")
public class EstadoMesa {
	@Id
	@Column(name = "idestadomesa", length = 1)
	private Integer idEstadoMesa;
	
	@Column(name = "nombreestadomesa", length = 50, nullable = false)
	private String nombreEstadoMesa;
	
	public EstadoMesa() {
		
	}
	public EstadoMesa(Integer idEstadoMesa, String nombreEstadoMesa) {
		this.idEstadoMesa = idEstadoMesa;
		this.nombreEstadoMesa = nombreEstadoMesa;
	}
	public Integer getIdEstadoMesa() {
		return idEstadoMesa;
	}
	public String getNombreEstadoMesa() {
		return nombreEstadoMesa;
	}
	
	public void setIdEstadoMesa(Integer idEstadoMesa) {
		this.idEstadoMesa = idEstadoMesa;
	}
	
	
	
}
