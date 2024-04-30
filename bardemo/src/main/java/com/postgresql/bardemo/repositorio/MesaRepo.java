package com.postgresql.bardemo.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.postgresql.bardemo.modelo.Mesa;
import com.postgresql.bardemo.modelo.Sucursales;

@Repository
public interface MesaRepo extends JpaRepository<Mesa, Integer>{
	
	//@Procedure(procedureName = "obtener_mesas_por_sucursal")
	@Query("SELECT m FROM Mesa m WHERE m.idSucursal.idSucursal = :idSucursal AND m.idEstadoMesa.idEstadoMesa = 1")
	List<Mesa> findByIdSucursal(@Param("idSucursal") Integer idSucursal);
	@Query("SELECT m FROM Mesa m, Orden o WHERE m.idSucursal.idSucursal = :idSucursal AND m.idEstadoMesa.idEstadoMesa = 2 AND o.idEstado.idEstado = 1")
	List<Mesa> findMesasOcupadas(@Param("idSucursal") Integer idSucursal);
	Optional<Mesa> findByIdMesa(Integer idMesa);
}
