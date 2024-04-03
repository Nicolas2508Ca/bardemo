package com.postgresql.bardemo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.postgresql.bardemo.modelo.empleados;
import com.postgresql.bardemo.repositorio.empleadosRepo;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class crudEmpleados {

    @Autowired
    private empleadosRepo empleadosRepo;

    @PostMapping("/empleados")
    public empleados createEmpleado(@RequestBody empleados empleado) {
        return empleadosRepo.save(empleado);
    }

    @GetMapping("/empleados/{documento}")
    public empleados getEmpleado(@PathVariable Integer documento) {
        return empleadosRepo.findByDocumento(documento);
    }

    @GetMapping("/sucursales/{idSucursal}/empleados")
    public List<empleados> getEmpleadosBySucursal(@PathVariable Integer idSucursal) {
        return empleadosRepo.findByIdSucursal(idSucursal);
    }

    @PutMapping("/empleados/{id}")
    public empleados updateEmpleado(@PathVariable Integer id, @RequestBody empleados empleado) {
        empleados existingEmpleado = empleadosRepo.findById(id).orElse(null);
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
