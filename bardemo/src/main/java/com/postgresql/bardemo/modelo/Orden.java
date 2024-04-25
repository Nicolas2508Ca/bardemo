package com.postgresql.bardemo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Orden")
public class Orden {

	@Id
	@Column(name = "Id_Orden")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrden;
	
	@Column(name = "Total_Orden")
	private Integer totalOrden;
	
	@ManyToOne
	@JoinColumn(name = "Mesa", foreignKey = @ForeignKey(name = "orden_idMesa_fkey"))
	private Mesa mesa;
	
	@ManyToOne
	@JoinColumn(name = "Id_Sucursal", foreignKey = @ForeignKey(name = "orden_idSucursal_fkey"))
	private Sucursales sucursal;
	
	@ManyToOne
	@JoinColumn(name = "Id_Estado", foreignKey = @ForeignKey(name = "orden_idEstadoOrden_fkey"))
	private EstadoOrden estadoOrden;
	
	@ManyToOne
	@JoinColumn(name = "Mesero", foreignKey = @ForeignKey(name = "orden_idEmpleado_fkey"))
	private Empleados idEmpleado;
	
	public Orden() {}

	public Orden(Long idOrden, Integer totalOrden, Mesa mesa, Sucursales sucursal, EstadoOrden estadoOrden,
			Empleados idEmpleado) {
		super();
		this.idOrden = idOrden;
		this.totalOrden = totalOrden;
		this.mesa = mesa;
		this.sucursal = sucursal;
		this.estadoOrden = estadoOrden;
		this.idEmpleado = idEmpleado;
	}

	public Long getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(Long idOrden) {
		this.idOrden = idOrden;
	}

	public Integer getTotalOrden() {
		return totalOrden;
	}

	public void setTotalOrden(Integer totalOrden) {
		this.totalOrden = totalOrden;
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

	public EstadoOrden getEstadoOrden() {
		return estadoOrden;
	}

	public void setEstadoOrden(EstadoOrden estadoOrden) {
		this.estadoOrden = estadoOrden;
	}

	public Empleados getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Empleados idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	
}
