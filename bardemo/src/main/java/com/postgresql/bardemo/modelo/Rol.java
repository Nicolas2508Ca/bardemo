package com.postgresql.bardemo.modelo;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Rol")
public class Rol {

    @Id
    @Column(name = "idrol")
    private Integer idRol;

    @Column(name = "nombrerol", length= 50, nullable = false)
    private String nombreRol;
    
    public Rol() {
    	
    }
    public Rol(Integer idRol, String nombreRol) {
    	this.idRol = idRol;
    	this.nombreRol = nombreRol;
    }
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
    
}
