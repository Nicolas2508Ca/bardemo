package com.postgresql.bardemo.modelo;

import java.util.List;


public class CrearOrdenRequest {
	private Mesa mesa;
	private EstadoOrden estadoOrden;
	private Sucursales sucursal;
	private Empleados empleado;
	private List<DetalleOrdenRequest> detalles;
	
	public CrearOrdenRequest() {}

	public CrearOrdenRequest(Mesa mesa, List<DetalleOrdenRequest> detalles, EstadoOrden estadoOrden,
			Sucursales sucursal, Empleados empleado) {
		super();
		this.mesa = mesa;
		this.detalles = detalles;
		this.estadoOrden = estadoOrden;
		this.sucursal = sucursal;
		this.empleado = empleado;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public List<DetalleOrdenRequest> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleOrdenRequest> detalles) {
		this.detalles = detalles;
	}

	public EstadoOrden getEstadoOrden() {
		return estadoOrden;
	}

	public void setEstadoOrden(EstadoOrden estadoOrden) {
		this.estadoOrden = estadoOrden;
	}

	public Sucursales getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursales sucursal) {
		this.sucursal = sucursal;
	}

	public Empleados getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleados empleado) {
		this.empleado = empleado;
	}
	
	
	
	
}
