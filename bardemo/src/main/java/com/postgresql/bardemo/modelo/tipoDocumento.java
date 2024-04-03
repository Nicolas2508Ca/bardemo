package com.postgresql.bardemo.modelo;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Tipo_Documento")
public class tipoDocumento {

    @Id
    @Column(name = "idtipodoc")
    private Integer idTipoDoc;

    @Column(name = "nombretipodoc")
    private String nombreTipoDoc;
}
