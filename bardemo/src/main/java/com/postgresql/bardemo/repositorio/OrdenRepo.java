package com.postgresql.bardemo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postgresql.bardemo.modelo.Orden;

@Repository
public interface OrdenRepo extends JpaRepository<Orden, Long>{
	
}
