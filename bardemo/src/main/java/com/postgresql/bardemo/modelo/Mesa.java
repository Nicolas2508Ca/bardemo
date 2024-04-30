package com.postgresql.bardemo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Mesa")
public class Mesa {
	
	@Id
	@Column(name = "idmesa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMesa;
	
	@Column(name = "nombremesa", length = 10)
	private String nombreMesa;
	@Column(name = "puestomesa", length = 2)
	private Integer puestosMesa;
	@ManyToOne
	@JoinColumn(name = "sucursal", foreignKey = @ForeignKey(name = "mesa_idSucursal_fkey"))
	private Sucursales idSucursal;
	@ManyToOne()
	@JoinColumn(name = "estado", foreignKey = @ForeignKey(name = "mesa_idEstdoMesa_fkey"))
	private EstadoMesa idEstadoMesa;
	
	public Mesa() {
		
	}

	public Mesa(Integer idMesa, String nombreMesa, Integer puestosMesa, Sucursales idSucursal,
			EstadoMesa idEstadoMesa) {
		super();
		this.idMesa = idMesa;
		this.nombreMesa = nombreMesa;
		this.puestosMesa = puestosMesa;
		this.idSucursal = idSucursal;
		this.idEstadoMesa = idEstadoMesa;
	}

	public Integer getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(Integer idMesa) {
		this.idMesa = idMesa;
	}

	public String getNombreMesa() {
		return nombreMesa;
	}

	public void setNombreMesa(String nombreMesa) {
		this.nombreMesa = nombreMesa;
	}

	public Integer getPuestosMesa() {
		return puestosMesa;
	}

	public void setPuestosMesa(Integer puestosMesa) {
		this.puestosMesa = puestosMesa;
	}

	public Sucursales getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Sucursales idSucursal) {
		this.idSucursal = idSucursal;
	}

	public EstadoMesa getIdEstadoMesa() {
		return idEstadoMesa;
	}

	public void setIdEstadoMesa(EstadoMesa idEstadoMesa) {
		this.idEstadoMesa = idEstadoMesa;
	}
	
	
}
