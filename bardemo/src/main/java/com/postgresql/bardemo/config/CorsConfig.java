package com.postgresql.bardemo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowCredentials(true);
		// Permitir solicitudes desde todos los orígenes
        //config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedOriginPatterns(Arrays.asList("*"));

        // Permitir todos los métodos HTTP (GET, POST, PUT, DELETE, etc.)
        config.setAllowedMethods(Arrays.asList("*"));

        // Permitir todos los encabezados
        config.setAllowedHeaders(Arrays.asList("*"));

        // Aplicar la configuración CORS a todas las rutas
        source.registerCorsConfiguration("/**", config);
		
        //source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
	}
}
