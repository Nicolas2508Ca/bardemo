package com.postgresql.bardemo.modelo;

import jakarta.persistence.Id; // Aquí está el cambio
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "empleado")
public class empleados {
    @Id
    private Integer documento;
    
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String contrasenia;

    @Column(name = "idrol")
    private Integer idRol;

    @Column(name = "idtipodoc")
    private Integer idTipoDoc;

    @Column(name = "idsucursal")
    private Integer idSucursal;


    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

	public Integer getDocumento() {
		return documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public Integer getIdTipoDoc() {
		return idTipoDoc;
	}

	public void setIdTipoDoc(Integer idTipoDoc) {
		this.idTipoDoc = idTipoDoc;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}
    
}