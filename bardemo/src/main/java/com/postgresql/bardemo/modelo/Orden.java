package com.postgresql.bardemo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Orden")
public class Orden {

	@Id
	@Column(name = "idorden")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrden;
	
	@Column(name = "subtotal")
	private Integer subtotal;
	
	@ManyToOne
	@JoinColumn(name = "mesa", foreignKey = @ForeignKey(name = "orden_idMesa_fkey"))
	private Mesa mesa;
	
	@ManyToOne
	@JoinColumn(name = "idsucursal", foreignKey = @ForeignKey(name = "orden_idSucursal_fkey"))
	private Sucursales sucursal;
	
	@ManyToOne
	@JoinColumn(name = "idestado", foreignKey = @ForeignKey(name = "orden_idEstadoOrden_fkey"))
	private EstadoOrden idEstado;
	
	@ManyToOne
	@JoinColumn(name = "mesero", foreignKey = @ForeignKey(name = "orden_documento_fkey"))
	private Empleados mesero;
	
	public Orden() {}

	public Orden(Long idOrden, Integer subtotal, Mesa mesa, Sucursales sucursal, EstadoOrden idEstado,
			Empleados mesero) {
		super();
		this.idOrden = idOrden;
		this.subtotal = subtotal;
		this.mesa = mesa;
		this.sucursal = sucursal;
		this.idEstado = idEstado;
		this.mesero = mesero;
	}

	public Long getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(Long idOrden) {
		this.idOrden = idOrden;
	}

	public Integer getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Sucursales getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursales sucursal) {
		this.sucursal = sucursal;
	}

	public EstadoOrden getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(EstadoOrden idEstado) {
		this.idEstado = idEstado;
	}

	public Empleados getMesero() {
		return mesero;
	}

	public void setMesero(Empleados mesero) {
		this.mesero = mesero;
	}
	
	
}
