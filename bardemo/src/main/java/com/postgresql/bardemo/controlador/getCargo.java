package com.postgresql.bardemo.controlador;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.postgresql.bardemo.modelo.cargo;
import com.postgresql.bardemo.repositorio.cargoRepo;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cargo")
public class getCargo {

    @Autowired
    private cargoRepo cargoRepo;

    @GetMapping()
    public List<cargo> getAllCargo() {
        return cargoRepo.findAll();
    }
}