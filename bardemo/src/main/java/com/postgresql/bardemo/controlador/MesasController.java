package com.postgresql.bardemo.controlador;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.bardemo.modelo.Mesa;
import com.postgresql.bardemo.repositorio.MesaRepo;
import com.postgresql.bardemo.servicios.ActualizarObjetoServicio;

@RestController
@RequestMapping("/mesas")
public class MesasController {

	@Autowired
	private MesaRepo mesaRepo;
	@Autowired
	private ActualizarObjetoServicio actualizarObjeto;
	
	@GetMapping("/{idSucursal}")
	public ResponseEntity<List<Mesa>> obtenerMesasSucursal(@PathVariable Integer idSucursal){
		List<Mesa> mesas = mesaRepo.findByIdSucursal(idSucursal);
		if(!mesas.isEmpty()) {
			return ResponseEntity.ok(mesas);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Mesa> crearMesa(@RequestBody Mesa mesa){
		try {
			mesaRepo.save(mesa);
			return ResponseEntity.ok(mesa);
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PatchMapping("mesas/actualizar/{idMesa}")
	public ResponseEntity<Mesa> actualizarMesa(@PathVariable Integer idMesa, @RequestBody Mesa mesaActualizada){
		Mesa mesaExistente = mesaRepo.findById(idMesa)
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada con el ID: " + idMesa));

        // Copiar las propiedades no nulas de mesaActualizada a mesaExistente
		actualizarObjeto.actualizarObjeto(mesaActualizada, mesaExistente);
        //BeanUtils.copyProperties(mesaActualizada, mesaExistente, getNullPropertyNames(mesaActualizada));

        final Mesa mesaActualizadaFinal = mesaRepo.save(mesaExistente);
        return ResponseEntity.ok(mesaActualizadaFinal);
	}
	@GetMapping("/cajero/{idSucursal}")
	public ResponseEntity<List<Mesa>> obtenerMesasOcupadas(@PathVariable Integer idSucursal){
		List<Mesa> mesas = mesaRepo.findMesasOcupadas(idSucursal);
		if(!mesas.isEmpty()) {
			return ResponseEntity.ok(mesas);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
