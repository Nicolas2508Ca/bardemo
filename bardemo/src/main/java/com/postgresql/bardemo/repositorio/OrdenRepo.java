package com.postgresql.bardemo.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postgresql.bardemo.modelo.Mesa;
import com.postgresql.bardemo.modelo.Orden;

@Repository
public interface OrdenRepo extends JpaRepository<Orden, Long>{
	
	Optional<Orden> findByMesa(Mesa mesa);
}
