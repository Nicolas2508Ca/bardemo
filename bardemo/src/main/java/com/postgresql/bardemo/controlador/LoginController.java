package com.postgresql.bardemo.controlador;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.bardemo.modelo.Empleados;
import com.postgresql.bardemo.repositorio.empleadosRepo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
public class LoginController {
    @Autowired
    private empleadosRepo empleadosRepo;

    @PostMapping("/login")
    public ResponseEntity<?> loginfun(@RequestParam Integer documento, @RequestParam String contrasenia, HttpServletResponse response) {
        Empleados empleados = empleadosRepo.findByDocumento(documento)
            .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado"));	
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("mensaje", "Inicio de sesión exitoso");
        responseData.put("sucursal", empleados.getIdSucursal());
        responseData.put("documentoEmpleado", empleados.getDocumento());
        if (empleados != null && empleados.getContrasenia().equals(contrasenia)) {
            if(empleados.getIdRol().getIdRol() == 1){
                Cookie cookie = new Cookie("rol", empleados.getIdRol().getIdRol().toString());
                cookie.setMaxAge(60 * 60 * 24); // 24 horas
                cookie.setPath("/");
                response.addCookie(cookie);
                responseData.put("mensaje", "Inicio de sesión exitoso admin");
            }
            if(empleados.getIdRol().getIdRol() == 2){
                Cookie cookie = new Cookie("rol", empleados.getIdRol().getIdRol().toString());
                cookie.setMaxAge(60 * 60 * 24); // 24 horas
                cookie.setPath("/HomeMesero");
                response.addCookie(cookie);
                responseData.put("mensaje", "Inicio de sesión exitoso mesero");
            }
            if(empleados.getIdRol().getIdRol() == 3) {
            	responseData.put("mensaje", "Inicio de sesión exitoso cajero");
            }
            
            return ResponseEntity.ok(responseData);
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "Usuario o contraseña incorrecta"));

        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
    	Cookie cookie = new Cookie("rol", null);
    	cookie.setMaxAge(0);
    	
    	cookie.setPath("/");
    	response.addCookie(cookie);

    	return "Haz salido de la sesión";
    }
}