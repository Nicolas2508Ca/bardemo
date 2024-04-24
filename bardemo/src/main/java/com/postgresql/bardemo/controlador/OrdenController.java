package com.postgresql.bardemo.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import com.postgresql.bardemo.modelo.CrearOrdenRequest;
import com.postgresql.bardemo.modelo.DetalleOrden;
import com.postgresql.bardemo.modelo.DetalleOrdenRequest;
import com.postgresql.bardemo.modelo.Empleados;
import com.postgresql.bardemo.modelo.Orden;
import com.postgresql.bardemo.modelo.Productos;
import com.postgresql.bardemo.repositorio.DetalleOrdenRepo;
import com.postgresql.bardemo.repositorio.OrdenRepo;
import com.postgresql.bardemo.repositorio.ProductosRepo;
import com.postgresql.bardemo.repositorio.empleadosRepo;


@RestController
@RequestMapping("/ordenes")
public class OrdenController {
	@Autowired
	private OrdenRepo ordenRepo;
	@Autowired
	private DetalleOrdenRepo detalleOrdenRepo;
	@Autowired
	private ProductosRepo productoRepo;
	@Autowired
	private empleadosRepo empleadoRepo;
	
	@PostMapping("/crear")
	public ResponseEntity<Orden> crearOrden(@RequestBody CrearOrdenRequest request){
		Orden orden = new Orden();
		Empleados empleado = empleadoRepo.findByDocumento(request.getEmpleado().getDocumento())
				.orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id " + request.getEmpleado().getDocumento()));
		orden.setMesa(request.getMesa());
		orden.setSucursal(request.getSucursal());
		orden.setIdEmpleado(empleado);
		orden.setEstadoOrden(request.getEstadoOrden());
		orden = ordenRepo.save(orden);
		
		Integer totalOrden = 0;
		for(DetalleOrdenRequest detalle : request.getDetalles()) {
			DetalleOrden detalleOrden = new DetalleOrden();
			Productos producto = productoRepo.findById(detalle.getProducto().getIdProducto())
					.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
			detalleOrden.setOrden(orden);
			detalleOrden.setProducto(producto);
			detalleOrden.setCantidad(detalle.getCantidad());
			
			Integer subtotalDetalle = producto.getPrecioProducto() * detalle.getCantidad();
			detalleOrden.setSubtotal(subtotalDetalle);
			
			totalOrden+= subtotalDetalle;
			
			detalleOrdenRepo.save(detalleOrden);
			
		}
		orden.setTotalOrden(totalOrden);
		ordenRepo.save(orden);
		
		return ResponseEntity.ok(orden);
	}
	
	@GetMapping("/{idOrden}")
	public ResponseEntity<?> obtenerOrdenes(@PathVariable Long idOrden){
//		 Orden orden = ordenRepo.findById(idOrden)
//				 .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));
//		 List<DetalleOrden> detalles = detalleOrdenRepo.findByOrden(orden);
//		 
//		 Map<String, Object> response = new HashMap<>();
//		 
//		 response.put("orden", orden.getIdOrden());
//		 response.put("detalles", detalles);
//		 
//		 return ResponseEntity.ok(response);
		
		Optional<Orden> ordenOpt = ordenRepo.findById(idOrden);
		if(ordenOpt.isPresent()) {
			Orden orden = ordenOpt.get();
			List<DetalleOrden> detalles = detalleOrdenRepo.findByOrden(orden);
			Map<String, Object> response = new HashMap<>();
			
			response.put("idOrden", orden.getIdOrden());
			response.put("totalOrden", orden.getTotalOrden());
			response.put("mesa", orden.getMesa());
			response.put("sucursal", orden.getIdOrden());
			response.put("estadoOrden", orden.getEstadoOrden());
			response.put("idEmpleado", orden.getIdEmpleado());
			response.put("detalles", detalles);
			
			return ResponseEntity.ok(response);
		}else {
			return ResponseEntity.notFound().build();
		}
		 
		 
	}
	
}
