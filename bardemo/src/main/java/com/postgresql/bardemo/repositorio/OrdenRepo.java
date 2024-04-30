package com.postgresql.bardemo.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.postgresql.bardemo.modelo.Mesa;
import com.postgresql.bardemo.modelo.Orden;

@Repository
public interface OrdenRepo extends JpaRepository<Orden, Long>{
	
	@Query("SELECT o FROM Orden o WHERE o.mesa.idMesa = :idMesa AND o.idEstado.idEstado = 1")
	Optional<Orden> findByMesa(@Param("idMesa") Integer idMesa);
}
