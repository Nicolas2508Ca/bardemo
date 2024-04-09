package com.postgresql.bardemo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.postgresql.bardemo.modelo.Rol;

public interface cargoRepo extends JpaRepository<Rol, Integer> {
}
