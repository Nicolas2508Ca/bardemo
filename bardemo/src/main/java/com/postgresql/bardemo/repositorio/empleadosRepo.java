package com.postgresql.bardemo.repositorio;

import com.postgresql.bardemo.modelo.empleados;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface empleadosRepo extends JpaRepository<empleados, Integer> {
    empleados findByDocumento(Integer documento);
    List<empleados> findByIdSucursal(Integer idSucursal);
}
