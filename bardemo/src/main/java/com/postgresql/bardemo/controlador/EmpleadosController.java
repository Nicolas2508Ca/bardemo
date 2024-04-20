package com.postgresql.bardemo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.postgresql.bardemo.modelo.Empleados;
import com.postgresql.bardemo.modelo.Rol;
import com.postgresql.bardemo.modelo.Sucursales;
import com.postgresql.bardemo.repositorio.empleadosRepo;
import com.postgresql.bardemo.servicios.ActualizarObjetoServicio;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class EmpleadosController {

    @Autowired
    private empleadosRepo empleadosRepo;
    @Autowired
    private ActualizarObjetoServicio actualizarObjeto;
    
    
    @PostMapping("/empleados")
    public ResponseEntity<Empleados> createEmpleado(@RequestBody Empleados empleado, @CookieValue(name = "rol", required = true) String valorRol) {
    	if("1".equals(valorRol)) {
    		empleadosRepo.save(empleado);
    		return ResponseEntity.ok(empleado);
    	}else {
    		System.out.println("No tiene permisos");
    		return ResponseEntity.badRequest().build();
    	}
    }
    @GetMapping("/empleados")
    public List<Empleados> obtenerEmpleados(){
    	return empleadosRepo.findAll();
    }
    @GetMapping("/empleados/{documento}")
    public Empleados getEmpleado(@PathVariable Integer documento) {
        return empleadosRepo.findByDocumento(documento)
        		.orElseThrow(() -> new ResourceNotFoundException("Empleado no existe"));
    }

    @GetMapping("/sucursales/{idSucursal}/empleados")
    public List<Empleados> getEmpleadosBySucursal(@PathVariable Sucursales idSucursal) {
        return empleadosRepo.findByIdSucursal(idSucursal);
    }

    
    @PatchMapping("/empleados/{idEmpleado}")
    public ResponseEntity<Empleados> actualizarEmpleado(@PathVariable Integer idEmpleado, @RequestBody Empleados empleadoActualizado){
    	Empleados empleadoExistente = empleadosRepo.findByDocumento(idEmpleado)
    			.orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado empleado con documento " + idEmpleado));
    	actualizarObjeto.actualizarObjeto(empleadoActualizado, empleadoExistente);
    	final Empleados empleadoActualizadoFinal = empleadosRepo.save(empleadoExistente);
    	return ResponseEntity.ok(empleadoActualizadoFinal);
    }

    @DeleteMapping("/empleados/{id}")
    public void deleteEmpleado(@PathVariable Integer id) {
        empleadosRepo.deleteById(id);
    }
}
