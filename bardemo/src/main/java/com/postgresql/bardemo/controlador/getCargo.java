package com.postgresql.bardemo.controlador;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.postgresql.bardemo.modelo.Rol;
import com.postgresql.bardemo.repositorio.cargoRepo;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cargo")
public class getCargo {

    @Autowired
    private cargoRepo rolRepo;

    @GetMapping()
    public List<Rol> getAllCargo() {
        return rolRepo.findAll();
    }
    @PostMapping
    public ResponseEntity<Rol> guardarCargo(@RequestBody Rol rol){
    	try {
    		rolRepo.save(rol);
    		return ResponseEntity.ok(rol);
    	}catch(Exception e) {
    		return ResponseEntity.badRequest().build();
    	}
    }
}