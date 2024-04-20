package com.postgresql.bardemo.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postgresql.bardemo.modelo.Productos;

@Repository
public interface ProductosRepo extends JpaRepository<Productos, Integer>{
	
}
