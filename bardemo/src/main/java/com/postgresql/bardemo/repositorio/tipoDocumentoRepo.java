package com.postgresql.bardemo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.postgresql.bardemo.modelo.tipoDocumento;

public interface tipoDocumentoRepo extends JpaRepository<tipoDocumento, Integer> {
}
