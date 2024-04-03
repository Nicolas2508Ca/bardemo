package com.postgresql.bardemo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.postgresql.bardemo.modelo.sucursales;

public interface sucursalesRepo extends JpaRepository<sucursales, Integer> {
}
