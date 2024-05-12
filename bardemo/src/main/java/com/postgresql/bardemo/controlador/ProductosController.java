package com.postgresql.bardemo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.bardemo.modelo.Productos;
import com.postgresql.bardemo.repositorio.ProductosRepo;
import com.postgresql.bardemo.servicios.ActualizarObjetoServicio;

@RestController
@RequestMapping("/productos")
public class ProductosController {
	
	@Autowired
	private ProductosRepo productosRepo;
	@Autowired
	private ActualizarObjetoServicio actualizarObjeto;
	
	@GetMapping
	public ResponseEntity<List<Productos>> obtenerProductos(){
		List<Productos> productos = productosRepo.findAll();
		if(!productos.isEmpty()) {
			return ResponseEntity.ok(productos);
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Productos> crearProductos(@RequestBody Productos producto){
		try{
			productosRepo.save(producto);
			return ResponseEntity.ok(producto);
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PatchMapping("/{idProducto}")
	public ResponseEntity<Productos> modificarProducto(@PathVariable Integer idProducto, @RequestBody Productos productoActualizado){
		Productos productoExistente = productosRepo.findById(idProducto)
				.orElseThrow(() -> new ResourceNotFoundException("No existe tal producto con id " + idProducto));
		actualizarObjeto.actualizarObjeto(productoActualizado, productoExistente);
		final Productos productoActualizadoFinal = productosRepo.save(productoExistente);
		return ResponseEntity.ok(productoActualizadoFinal);
	}

	@DeleteMapping("/eliminar/{idProducto}")
	public ResponseEntity<Void> eliminarProducto(@PathVariable Integer idProducto) {
		Productos productoExistente = productosRepo.findById(idProducto)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + idProducto));
		productosRepo.delete(productoExistente);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/producto/{idProducto}")
	public ResponseEntity<Productos> obtenerProducto(@PathVariable Integer idProducto){
		Productos producto = productosRepo.findById(idProducto)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + idProducto));
		return ResponseEntity.ok(producto);
	}
}
