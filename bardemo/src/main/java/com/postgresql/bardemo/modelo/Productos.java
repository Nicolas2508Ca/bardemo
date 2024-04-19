package com.postgresql.bardemo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Productos")
public class Productos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	@Column(name = "Nombre_Producto", length = 100, nullable = false)
	private String nombreProducto;
	@Column(name = "Precio_Producto", nullable = false)
	private Integer precioProducto;
	
	public Productos() {
		
	}

	public Productos(Integer idProducto, String nombreProducto, Integer precioProducto) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.precioProducto = precioProducto;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Integer getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(Integer precioProducto) {
		this.precioProducto = precioProducto;
	}
	
}
