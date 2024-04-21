package com.postgresql.bardemo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.bardemo.modelo.ExistenciasSucursal;
import com.postgresql.bardemo.repositorio.ExistenciasSucursalRepo;
import com.postgresql.bardemo.servicios.ActualizarObjetoServicio;

@RestController
@RequestMapping("/inventario")
public class ExistenciasSucursalController {
	@Autowired
	private ExistenciasSucursalRepo existenciasRepo;
	@Autowired
	private ActualizarObjetoServicio actualizarObjeto;
	
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
	
	@PatchMapping("/{idExistencia}")
	public ResponseEntity<ExistenciasSucursal> modificarInventario(
			@RequestBody ExistenciasSucursal existenciasSucursalActualizada, @CookieValue(name = "rol", required = true) String valorRol,
			@PathVariable Long idExistencia){
		if("1".equals(valorRol) || "2".equals(valorRol)) {
			ExistenciasSucursal existenciaExistente =  existenciasRepo.findById(idExistencia)
					.orElseThrow(() -> new ResourceNotFoundException("No existese ese item de inventario"));
			actualizarObjeto.actualizarObjeto(existenciasSucursalActualizada, existenciaExistente);
			final ExistenciasSucursal existenciaActualizadaFinal = existenciasRepo.save(existenciaExistente);
			return ResponseEntity.ok(existenciaActualizadaFinal);
		}
		return ResponseEntity.badRequest().build();
	}
}
