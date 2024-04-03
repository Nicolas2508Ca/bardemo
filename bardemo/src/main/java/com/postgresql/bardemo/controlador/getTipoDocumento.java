package com.postgresql.bardemo.controlador;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.postgresql.bardemo.modelo.tipoDocumento;
import com.postgresql.bardemo.repositorio.tipoDocumentoRepo;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tipodocumento")
public class getTipoDocumento {

    @Autowired
    private tipoDocumentoRepo tipoDocumentoRepo;

    @GetMapping()
    public List<tipoDocumento> getAllTipoDocumento() {
        return tipoDocumentoRepo.findAll();
    }
}
