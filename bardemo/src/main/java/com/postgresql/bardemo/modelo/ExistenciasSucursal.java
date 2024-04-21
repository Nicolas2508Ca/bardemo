package com.postgresql.bardemo.modelo;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Existencias_Sucursal")
public class ExistenciasSucursal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdExistencias_Sucursal")
	private Long idExistenciasSucursal;
	
	@Column(name = "Cantidad")
	private Integer cantidad;
	
	@Column(name = "Fecha_Actualizacion")
	private LocalDateTime fechaActualizacion;
	
	@ManyToOne
	@JoinColumn(name = "Id_Sucursal", foreignKey= @ForeignKey(name = "existenciassucursal_idsucursal_fkey"))
	private Sucursales sucursal;
	
	@ManyToOne
	@JoinColumn(name = "Id_Producto", foreignKey = @ForeignKey(name = "existenciassucrsal_idproducto_fkey"))
	private Productos producto;
	
	@PrePersist
	public void prePersist() {
		if(this.fechaActualizacion == null) {
			this.fechaActualizacion = LocalDateTime.now();
		}
	}
	@PreUpdate
	public void preUpdate() {
		this.fechaActualizacion = LocalDateTime.now();
	}
	
	public ExistenciasSucursal() {}

	public ExistenciasSucursal(Long idExistenciasSucursal, Integer cantidad, LocalDateTime fechaActualizacion,
			Sucursales sucursal, Productos producto) {
		super();
		this.idExistenciasSucursal = idExistenciasSucursal;
		this.cantidad = cantidad;
		this.fechaActualizacion = fechaActualizacion;
		this.sucursal = sucursal;
		this.producto = producto;
	}

	public Long getIdExistenciasSucursal() {
		return idExistenciasSucursal;
	}

	public void setIdExistenciasSucursal(Long idExistenciasSucursal) {
		this.idExistenciasSucursal = idExistenciasSucursal;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
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
