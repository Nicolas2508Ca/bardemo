package com.postgresql.bardemo.modelo;


import jakarta.persistence.*;

@Entity
@Table(name = "Detalle_Orden")
public class DetalleOrden {
	
	@Id
	@Column(name = "Id_Detalle")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetalle;
	@Column(name = "Cantidad")
	private Integer cantidad;
	
	@Column(name = "Subtotal")
	private Integer subtotal;
	
	@ManyToOne
	@JoinColumn(name = "Id_Producto", foreignKey = @ForeignKey(name = "detalleorden_idproducto_fkey"))
	private Productos producto;
	
	@ManyToOne
	@JoinColumn(name = "Id_Orden", foreignKey = @ForeignKey(name = "detalleorden_idorden_fkey"))
	private Orden orden;
	
	public DetalleOrden() {}

	public DetalleOrden(Long idDetalle, Integer cantidad, Integer subtotal, Productos producto, Orden orden) {
		super();
		this.idDetalle = idDetalle;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.producto = producto;
		this.orden = orden;
	}

	public Long getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(Long idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}
	
	
}
