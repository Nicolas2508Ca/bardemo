package com.postgresql.bardemo.modelo;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Tipo_Documento")
public class TipoDocumento {

    @Id
    @Column(name = "idtipodoc")
    private Integer idTipoDoc;

    @Column(name = "nombretipodoc", length = 50, nullable = false)
    private String nombreTipoDoc;
    
    public TipoDocumento() {
    	
    }
    public TipoDocumento(Integer idTipoDoc, String nombreTipoDoc) {
    	this.idTipoDoc = idTipoDoc;
    	this.nombreTipoDoc = nombreTipoDoc;
    }
	public Integer getIdTipoDoc() {
		return idTipoDoc;
	}

	public String getNombreTipoDoc() {
		return nombreTipoDoc;
	}

    
}
