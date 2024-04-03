package com.postgresql.bardemo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.postgresql.bardemo.modelo.sucursales;
import com.postgresql.bardemo.repositorio.sucursalesRepo;
import java.util.Optional;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sucursales")
public class crudSucursales {

    @Autowired
    private sucursalesRepo sucursalesRepo;

    @GetMapping
    public List<sucursales> getAllSucursales() {
        return sucursalesRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<sucursales> getSucursalById(@PathVariable Integer id) {
    Optional<sucursales> sucursalData = sucursalesRepo.findById(id);

    if (sucursalData.isPresent()) {
        return new ResponseEntity<>(sucursalData.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

    @PostMapping
    public sucursales createSucursal(@RequestBody sucursales sucursal) {
        sucursales newSucursal = new sucursales();
        newSucursal.setNombreSucursal(sucursal.getNombreSucursal());
        newSucursal.setDireccionSucursal(sucursal.getDireccionSucursal());
        return sucursalesRepo.save(newSucursal);
    }

    @PutMapping("/{id}")
    public sucursales updateSucursal(@PathVariable Integer id, @RequestBody sucursales sucursalActualizada) {
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
