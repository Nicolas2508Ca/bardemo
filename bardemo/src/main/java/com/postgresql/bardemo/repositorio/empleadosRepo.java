package com.postgresql.bardemo.repositorio;

import com.postgresql.bardemo.modelo.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface empleadosRepo extends JpaRepository<Empleados, Integer> {
    Empleados findByDocumento(Integer documento);
    List<Empleados> findByIdSucursal(Integer idSucursal);
}
