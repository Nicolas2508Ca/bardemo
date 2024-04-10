package com.postgresql.bardemo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.postgresql.bardemo.modelo.Empleados;
import com.postgresql.bardemo.modelo.Sucursales;
import com.postgresql.bardemo.repositorio.empleadosRepo;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class EmpleadosController {

    @Autowired
    private empleadosRepo empleadosRepo;

    @PostMapping("/empleados")
    public ResponseEntity<Empleados> createEmpleado(@RequestBody Empleados empleado, @CookieValue(name = "rol", required = true) String valorRol) {
    	if("1".equals(valorRol)) {
    		empleadosRepo.save(empleado);
    		return ResponseEntity.ok(empleado);
    	}else {
    		return ResponseEntity.badRequest().build();
    	}
    }
    @GetMapping("/empleados")
    public List<Empleados> obtenerEmpleados(){
    	return empleadosRepo.findAll();
    }
    @GetMapping("/empleados/{documento}")
    public Empleados getEmpleado(@PathVariable Integer documento) {
        return empleadosRepo.findByDocumento(documento);
    }

    @GetMapping("/sucursales/{idSucursal}/empleados")
    public List<Empleados> getEmpleadosBySucursal(@PathVariable Sucursales idSucursal) {
        return empleadosRepo.findByIdSucursal(idSucursal);
    }

    @PutMapping("/empleados/{id}")
    public Empleados updateEmpleado(@PathVariable Integer id, @RequestBody Empleados empleado) {
        Empleados existingEmpleado = empleadosRepo.findById(id).orElse(null);
        if (existingEmpleado != null) {
            existingEmpleado.setNombre(empleado.getNombre());
            existingEmpleado.setApellido(empleado.getApellido());
            existingEmpleado.setEmail(empleado.getEmail());
            existingEmpleado.setTelefono(empleado.getTelefono());
            existingEmpleado.setIdRol(empleado.getIdRol());
            existingEmpleado.setIdTipoDoc(empleado.getIdTipoDoc());
            existingEmpleado.setIdSucursal(empleado.getIdSucursal());
            // Aquí deberías agregar el código para actualizar los demás campos del empleado existente con los valores del empleado proporcionado
        }
        return empleadosRepo.save(existingEmpleado);
    }

    @DeleteMapping("/empleados/{id}")
    public void deleteEmpleado(@PathVariable Integer id) {
        empleadosRepo.deleteById(id);
    }
}
