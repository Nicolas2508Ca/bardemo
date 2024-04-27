package com.postgresql.bardemo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.postgresql.bardemo.modelo.Mesa;
import com.postgresql.bardemo.modelo.Sucursales;
import com.postgresql.bardemo.repositorio.MesaRepo;
import com.postgresql.bardemo.repositorio.sucursalesRepo;
import java.util.Optional;

import java.util.List;


@RestController
@RequestMapping("/sucursales")
public class SucursalesController {

    @Autowired
    private sucursalesRepo sucursalesRepo;
    @Autowired
    private MesaRepo mesaRepo;

    @GetMapping
    public List<Sucursales> getAllSucursales() {
        return sucursalesRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursales> getSucursalById(@PathVariable Integer id) {
    Optional<Sucursales> sucursalData = sucursalesRepo.findById(id);

    if (sucursalData.isPresent()) {
        return new ResponseEntity<>(sucursalData.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
    @PostMapping
    public ResponseEntity<Sucursales> createSucursal(@RequestBody Sucursales sucursal) {
    	System.out.println(sucursal.getIdSucursal());
    	try{
    		sucursalesRepo.save(sucursal);
    		return ResponseEntity.ok(sucursal);    		
    	}catch(Exception e) {
    		return ResponseEntity.badRequest().build();
    	}
    }

    @PutMapping("/{id}")
    public Sucursales updateSucursal(@PathVariable Integer id, @RequestBody Sucursales sucursalActualizada) {
        return sucursalesRepo.findById(id)
                .map(sucursal -> {
                    sucursal.setNombreSucursal(sucursalActualizada.getNombreSucursal());
                    sucursal.setDireccionSucursal(sucursalActualizada.getDireccionSucursal());
                    return sucursalesRepo.save(sucursal);
                })
                .orElseGet(() -> {
                    sucursalActualizada.setIdSucursal(id);
                    return sucursalesRepo.save(sucursalActualizada);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteSucursal(@PathVariable Integer id) {
        sucursalesRepo.deleteById(id);
    }

    // Aquí puedes agregar más métodos para manejar otras operaciones CRUD...
}
