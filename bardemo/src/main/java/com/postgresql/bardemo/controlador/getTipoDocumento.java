package com.postgresql.bardemo.controlador;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.postgresql.bardemo.modelo.TipoDocumento;
import com.postgresql.bardemo.repositorio.tipoDocumentoRepo;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tipodocumento")
public class getTipoDocumento {

    @Autowired
    private tipoDocumentoRepo tipoDocumentoRepo;

    @GetMapping()
    public List<TipoDocumento> getAllTipoDocumento() {
        return tipoDocumentoRepo.findAll();
    }
    @PostMapping
    public ResponseEntity<TipoDocumento> guardarTipoDocumento(@RequestBody TipoDocumento tipo){
    	try {
    		tipoDocumentoRepo.save(tipo);
    		return ResponseEntity.ok(tipo);
    	}catch(Exception e) {
    		return ResponseEntity.badRequest().build();
    	}
    }
}
