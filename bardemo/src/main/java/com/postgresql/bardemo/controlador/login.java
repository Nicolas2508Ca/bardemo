package com.postgresql.bardemo.controlador;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.bardemo.modelo.empleados;
import com.postgresql.bardemo.repositorio.empleadosRepo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
public class login {
    @Autowired
    private empleadosRepo empleadosRepo;

    @PostMapping("/login")
    public String loginfun(@RequestParam Integer documento, @RequestParam String contrasenia, HttpServletResponse response) {
        empleados empleados = empleadosRepo.findByDocumento(documento);
        if (empleados != null && empleados.getContrasenia().equals(contrasenia)) {
            if(empleados.getIdRol() == 1){
                Cookie cookie = new Cookie("rol", empleados.getIdRol().toString());
                cookie.setMaxAge(60 * 60 * 24); // 24 horas
                cookie.setPath("/");
                response.addCookie(cookie);
                return "Inicio de sesión exitoso admin";
            }
            if(empleados.getIdRol() == 2){
                Cookie cookie = new Cookie("rol", empleados.getIdRol().toString());
                cookie.setMaxAge(60 * 60 * 24); // 24 horas
                cookie.setPath("/HomeMesero");
                response.addCookie(cookie);
                return "Inicio de sesión exitoso mesero";
            }
            return "Inicio de sesión exitoso";
        } else {
            return "Usuario o contraseña incorrecta";
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