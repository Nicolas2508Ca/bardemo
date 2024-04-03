package com.postgresql.bardemo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.bardemo.modelo.empleados;
import com.postgresql.bardemo.repositorio.empleadosRepo;

@CrossOrigin
@RestController
public class login {
    @Autowired
    private empleadosRepo empleadosRepo;

    @PostMapping("/login")
    public String loginfun(@RequestParam Integer documento, @RequestParam String contraseña) {
        empleados empleados = empleadosRepo.findByDocumento(documento);
        if (empleados != null && empleados.getContraseña().equals(contraseña)) {
            return "Inicio de sesión exitoso";
        } else {
            return "Cédula o contraseña incorrecta";
        }
    }
}