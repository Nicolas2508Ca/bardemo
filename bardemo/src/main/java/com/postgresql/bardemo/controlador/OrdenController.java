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
import com.postgresql.bardemo.modelo.EstadoMesa;
import com.postgresql.bardemo.modelo.Mesa;
import com.postgresql.bardemo.modelo.Orden;
import com.postgresql.bardemo.modelo.Productos;
import com.postgresql.bardemo.repositorio.DetalleOrdenRepo;
import com.postgresql.bardemo.repositorio.MesaRepo;
import com.postgresql.bardemo.repositorio.OrdenRepo;
import com.postgresql.bardemo.repositorio.ProductosRepo;
import com.postgresql.bardemo.repositorio.empleadosRepo;
import com.postgresql.bardemo.servicios.ActualizarObjetoServicio;


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
	@Autowired
	private MesaRepo mesaRepo;
	@Autowired
	private ActualizarObjetoServicio actualizarObjeto;
	
	@PostMapping("/crear")
	public ResponseEntity<Orden> crearOrden(@RequestBody CrearOrdenRequest request){
		Orden orden = new Orden();
		Mesa mesaExistente = mesaRepo.findById(request.getMesa().getIdMesa())
				.orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada"));
		actualizarObjeto.actualizarObjeto(request.getMesa(), mesaExistente);
		mesaRepo.save(mesaExistente);
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
	
	@PatchMapping("/pagar/{idOrden}")
	public ResponseEntity<?> pagarOrden(@PathVariable Long idOrden, @RequestBody Orden ordenPagada){
		try {
			Orden orden = ordenRepo.findById(idOrden)
	                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));
	        Mesa mesa = mesaRepo.findById(orden.getMesa().getIdMesa())
	        		.orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada"));
	        EstadoMesa estadoMesa = new EstadoMesa();
	        estadoMesa.setIdEstadoMesa(1);
	        mesa.setIdEstadoMesa(estadoMesa);
	        actualizarObjeto.actualizarObjeto(ordenPagada.getMesa(), mesa);
	        actualizarObjeto.actualizarObjeto(ordenPagada, orden);
	        
	        final Orden ordenActualizada = ordenRepo.save(orden);
	        return ResponseEntity.ok(ordenActualizada);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e);
		}
	}
	
	@GetMapping("/{idMesa}")
	public ResponseEntity<?> obtenerOrdenes(@PathVariable Integer idMesa){
		Mesa mesa = mesaRepo.findById(idMesa)
				.orElseThrow(() -> new ResourceNotFoundException(""));
		Optional<Orden> ordenOpt = ordenRepo.findByMesa(mesa);
		if(ordenOpt.isPresent()) {
			Orden orden = ordenOpt.get();
			List<DetalleOrden> detalles = detalleOrdenRepo.findByOrden(orden);
			Map<String, Object> response = new HashMap<>();
			
			response.put("idOrden", orden.getIdOrden());
			response.put("totalOrden", orden.getSubtotal());
			response.put("mesa", orden.getMesa());
			response.put("sucursal", orden.getIdOrden()); 
			response.put("estadoOrden", orden.getIdEstado());
			response.put("idEmpleado", orden.getMesero());
			response.put("detalles", detalles);
			
			return ResponseEntity.ok(response);
		}else {
			return ResponseEntity.notFound().build();
		}
		 
		 
	}
	
}
