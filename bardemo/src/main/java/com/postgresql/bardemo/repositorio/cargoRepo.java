package com.postgresql.bardemo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.postgresql.bardemo.modelo.cargo;

public interface cargoRepo extends JpaRepository<cargo, Integer> {
}
