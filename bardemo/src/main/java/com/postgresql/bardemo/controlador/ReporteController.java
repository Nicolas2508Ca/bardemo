package com.postgresql.bardemo.controlador;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.postgresql.bardemo.DTO.RangoFechasDTO;
import com.postgresql.bardemo.DTO.ReporteDTO;
import com.postgresql.bardemo.modelo.DetalleOrden;
import com.postgresql.bardemo.modelo.Orden;
import com.postgresql.bardemo.servicios.ExportarExcelService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/reporte")
public class ReporteController {
	@Autowired
	ExportarExcelService exportar;
	
	@PostMapping("/descargar")
	public ResponseEntity<byte[]> generarYDescargarReporte(@RequestBody RangoFechasDTO fechaBusqueda) {
	    try {
	    	System.out.println(fechaBusqueda.getFechaInicio());
	    	System.out.println(fechaBusqueda.getFechaFinal());
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        Date fechaInicio = formatter.parse(fechaBusqueda.getFechaInicio());
	        Date fechaFin = formatter.parse(fechaBusqueda.getFechaFinal());

	        byte[] archivoBytes = exportar.exportarExcel(fechaInicio, fechaFin, fechaBusqueda.getIdSucursal());
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.setContentDispositionFormData("attachment", "reporte.xls");

	        return ResponseEntity.ok()
	                             .headers(headers)
	                             .body(archivoBytes);
	    } catch (ParseException | IOException e) {
	        System.out.println("Error al generar el archivo de Excel: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
}
