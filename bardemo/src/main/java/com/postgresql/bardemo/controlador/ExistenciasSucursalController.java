package com.postgresql.bardemo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.bardemo.modelo.ExistenciasSucursal;
import com.postgresql.bardemo.repositorio.ExistenciasSucursalRepo;

@RestController
@RequestMapping("/inventario")
public class ExistenciasSucursalController {
	@Autowired
	private ExistenciasSucursalRepo existenciasRepo;
	
	@GetMapping("/{idSucursal}")
	public ResponseEntity<List<ExistenciasSucursal>> obtenerExistenciasSucursal(@PathVariable Integer idSucursal){
		List<ExistenciasSucursal> existenciasSucursal =  existenciasRepo.findBySucursal(idSucursal);
		if(!existenciasSucursal.isEmpty()) {
			return ResponseEntity.ok(existenciasSucursal);
		}
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("")
	public ResponseEntity<ExistenciasSucursal> alimentarInventario(
			@RequestBody ExistenciasSucursal existenciasSucursal, @CookieValue(name = "rol", required = true) String valorRol){
		if("1".equals(valorRol)) {
			existenciasRepo.save(existenciasSucursal);
			return ResponseEntity.ok(existenciasSucursal);
		}
		return ResponseEntity.badRequest().build();
	}
}
