package com.postgresql.bardemo.DTO;

public class RangoFechasDTO {
	private String fechaInicio;
	private String fechaFinal;
	private Integer idSucursal;
	public RangoFechasDTO(String fechaInicio, String fechaFinal, Integer idSucursal) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.idSucursal = idSucursal;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public Integer getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}
	
	
}
