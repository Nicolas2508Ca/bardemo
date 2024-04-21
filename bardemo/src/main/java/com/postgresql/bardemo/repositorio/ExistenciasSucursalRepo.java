package com.postgresql.bardemo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.postgresql.bardemo.modelo.ExistenciasSucursal;

@Repository
public interface ExistenciasSucursalRepo extends JpaRepository<ExistenciasSucursal, Long>{
	

	@Query("SELECT e FROM ExistenciasSucursal e WHERE e.sucursal.idSucursal = :idSucursal")
	List<ExistenciasSucursal> findBySucursal(@Param("idSucursal") Integer sucursal);
}
