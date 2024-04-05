package com.postgresql.bardemo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.postgresql.bardemo.modelo.sucursales;
import com.postgresql.bardemo.repositorio.sucursalesRepo;
import java.util.Optional;

import java.util.List;


@RestController
@RequestMapping("/sucursales")
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
    public ResponseEntity<sucursales> createSucursal(@RequestBody sucursales sucursal, @CookieValue(name = "rol", required = false) String valorRol) {
    	System.out.println(valorRol);
    	if("1".equals(valorRol)) {
    		System.out.println("Buena request");
    		sucursalesRepo.save(sucursal);
    		return ResponseEntity.ok(sucursal);    		
    	}
    		System.out.println("mala request");
    	return ResponseEntity.badRequest().build();
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
