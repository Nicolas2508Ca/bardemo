package com.postgresql.bardemo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postgresql.bardemo.modelo.DetalleOrden;
import com.postgresql.bardemo.modelo.Orden;

@Repository
public interface DetalleOrdenRepo extends JpaRepository<DetalleOrden, Long>{
	
	List<DetalleOrden> findByOrden(Orden orden);
}
