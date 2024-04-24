package com.postgresql.bardemo.repositorio;

import com.postgresql.bardemo.modelo.Empleados;
import com.postgresql.bardemo.modelo.Sucursales;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface empleadosRepo extends JpaRepository<Empleados, Integer> {
    Optional<Empleados> findByDocumento(Integer documento);
    List<Empleados> findByIdSucursal(Sucursales idSucursal);
}
