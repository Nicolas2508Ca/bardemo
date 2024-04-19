package com.postgresql.bardemo.modelo;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Existencias_Sucursal")
public class ExistenciasSucursal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdExistencias_Sucursal")
	private Integer idExistenciasSucursal;
	
	@Column(name = "Cantidad")
	private Integer cantidad;
	
	@Column(name = "Fecha_Actualizacion", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fechaActualizacion;
	
	@ManyToOne
	@JoinColumn(name = "Id_Sucursal", foreignKey= @ForeignKey(name = "existenciassucursal_idsucursal_fkey"))
	private Sucursales sucursal;
	
	@ManyToOne
	@JoinColumn(name = "Id_Producto", foreignKey = @ForeignKey(name = "existenciassucrsal_idproducto_fkey"))
	private Productos producto;
	
	public ExistenciasSucursal() {}

	public ExistenciasSucursal(Integer idExistenciasSucursal, Integer cantidad, Date fechaActualizacion,
			Sucursales sucursal, Productos producto) {
		super();
		this.idExistenciasSucursal = idExistenciasSucursal;
		this.cantidad = cantidad;
		this.fechaActualizacion = fechaActualizacion;
		this.sucursal = sucursal;
		this.producto = producto;
	}

	public Integer getIdExistenciasSucursal() {
		return idExistenciasSucursal;
	}

	public void setIdExistenciasSucursal(Integer idExistenciasSucursal) {
		this.idExistenciasSucursal = idExistenciasSucursal;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
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
