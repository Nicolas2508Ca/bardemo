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
    private String contraseña;

    @Column(name = "idrol")
    private Integer idRol;

    @Column(name = "idtipodoc")
    private Integer idTipoDoc;

    @Column(name = "idsucursal")
    private Integer idSucursal;


    public String getContraseña() {
        return this.contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}