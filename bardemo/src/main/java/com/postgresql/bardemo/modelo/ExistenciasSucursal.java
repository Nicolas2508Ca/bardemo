package com.postgresql.bardemo.modelo;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Inventario")
public class ExistenciasSucursal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idinventario")
	private Long idInventario;
	
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@ManyToOne
	@JoinColumn(name = "sucursal", foreignKey= @ForeignKey(name = "existenciassucursal_idsucursal_fkey"))
	private Sucursales sucursal;
	
	@ManyToOne
	@JoinColumn(name = "producto", foreignKey = @ForeignKey(name = "existenciassucrsal_idproducto_fkey"))
	private Productos producto;
	
	public ExistenciasSucursal() {}

	public ExistenciasSucursal(Long idInventario, Integer cantidad,
			Sucursales sucursal, Productos producto) {
		super();
		this.idInventario = idInventario;
		this.cantidad = cantidad;
		this.sucursal = sucursal;
		this.producto = producto;
	}

	public Long getidInventario() {
		return idInventario;
	}

	public void setidInventario(Long idInventario) {
		this.idInventario = idInventario;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Sucursales getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursales sucursal) {
		this.sucursal = sucursal;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}
	
	
}
