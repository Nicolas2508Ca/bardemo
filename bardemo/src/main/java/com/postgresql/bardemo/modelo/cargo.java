package com.postgresql.bardemo.modelo;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Rol")
public class cargo {

    @Id
    @Column(name = "idrol")
    private Integer idRol;

    @Column(name = "nombrerol")
    private String nombreRol;
}
