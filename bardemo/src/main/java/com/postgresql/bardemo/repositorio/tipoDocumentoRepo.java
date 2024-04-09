package com.postgresql.bardemo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.postgresql.bardemo.modelo.TipoDocumento;

public interface tipoDocumentoRepo extends JpaRepository<TipoDocumento, Integer> {
}
