package com.postgresql.bardemo.modelo;

import jakarta.persistence.Id; // Aquí está el cambio
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "empleado")
public class Empleados {
    @Id
    private Integer documento;
    
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    @Column(name = "apellido", length = 50, nullable = false)
    private String apellido;
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    @Column(name = "telefono", length = 20, nullable = false)
    private String telefono;
    @Column(name = "contrasenia", length = 50, nullable = false)
    private String contrasenia;

    @ManyToOne
    							/// Aca en foreign key se coloca el nombre de la relacion
    							/// en la base de datos para que no de error
    @JoinColumn(name = "idrol", foreignKey = @ForeignKey(name = "empleados_idrol_fkey"))
    private Rol idRol;
    @ManyToOne
    							// Aca en foreign key se coloca el nombre de la relacion
    							// en la base de datos para que no de error
    @JoinColumn(name = "idtipodoc", foreignKey = @ForeignKey(name = "empleados_idtipodoc_fkey"))
    private TipoDocumento idTipoDoc;
    @ManyToOne
    								// Aca en foreign key se coloca el nombre de la relacion
									// en la base de datos para que no de error
    @JoinColumn(name="idsucursal", foreignKey= @ForeignKey(name = "empleados_idsucursal_fkey"))
    private Sucursales idSucursal;

    public Empleados() {}
    
    


	public Empleados(Integer documento, String nombre, String apellido, String email, String telefono,
			String contrasenia, Rol idRol, TipoDocumento idTipoDoc, Sucursales idSucursal) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.contrasenia = contrasenia;
		this.idRol = idRol;
		this.idTipoDoc = idTipoDoc;
		this.idSucursal = idSucursal;
	}




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

	public Rol getIdRol() {
		return idRol;
	}

	public void setIdRol(Rol idRol) {
		this.idRol = idRol;
	}

	public TipoDocumento getIdTipoDoc() {
		return idTipoDoc;
	}

	public void setIdTipoDoc(TipoDocumento idTipoDoc) {
		this.idTipoDoc = idTipoDoc;
	}

	public Sucursales getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Sucursales idSucursal) {
		this.idSucursal = idSucursal;
	}
    
}