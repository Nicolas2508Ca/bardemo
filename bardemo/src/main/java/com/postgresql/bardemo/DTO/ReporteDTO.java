package com.postgresql.bardemo.DTO;

import java.util.List;

import com.postgresql.bardemo.modelo.DetalleOrden;
import com.postgresql.bardemo.modelo.Orden;

public class ReporteDTO {
	private Orden orden;
	private DetalleOrden detalleOrden;
	private double total;
    private String productosMasVendidos;
    private int totalVendidos;
	
	
	public ReporteDTO(Orden orden, DetalleOrden detalleOrden, double total, String productosMasVendidos,
			int totalVendidos) {
		super();
		this.orden = orden;
		this.detalleOrden = detalleOrden;
		this.total = total;
		this.productosMasVendidos = productosMasVendidos;
		this.totalVendidos = totalVendidos;
	}
	public Orden getOrden() {
		return orden;
	}
	public void setOrden(Orden orden) {
		this.orden = orden;
	}
	public DetalleOrden getDetalleOrden() {
		return detalleOrden;
	}
	public void setDetalleOrden(DetalleOrden detalleOrden) {
		this.detalleOrden = detalleOrden;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getProductosMasVendidos() {
		return productosMasVendidos;
	}
	public void setProductosMasVendidos(String productosMasVendidos) {
		this.productosMasVendidos = productosMasVendidos;
	}
	public int getTotalVendidos() {
		return totalVendidos;
	}
	public void setTotalVendidos(int totalVendidos) {
		this.totalVendidos = totalVendidos;
	}
	
	
	
}
