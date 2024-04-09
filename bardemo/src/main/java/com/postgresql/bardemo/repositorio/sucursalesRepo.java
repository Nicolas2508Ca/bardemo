package com.postgresql.bardemo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.postgresql.bardemo.modelo.Sucursales;

public interface sucursalesRepo extends JpaRepository<Sucursales, Integer> {
}
