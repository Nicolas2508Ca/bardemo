package com.postgresql.bardemo.servicios;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.postgresql.bardemo.modelo.Orden;

import com.postgresql.bardemo.repositorio.OrdenRepo;

@Service
public class ExportarExcelService {
	@Autowired
	OrdenRepo ordenRepo;
	
	public byte[] exportarExcel(Date fechaInicio, Date fechaFin, Integer idSucursal) throws IOException {
        LocalDateTime fechaInicioLocalDateTime = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime fechaFinLocalDateTime = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        List<Object[]> objetos = ordenRepo.findByFechaVentaBetween(fechaInicioLocalDateTime, fechaFinLocalDateTime, idSucursal);
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reporte");

            // Crear el encabezado
            Row headerRow = sheet.createRow(0);
            String[] columnas = {"ID Orden", "Fecha Venta", "Total"};
            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
            }

            // Llenar el excel con los datos de los reportes
            int rowNum = 1;
            for (Object[] resultado : objetos) {
                Orden orden = (Orden) resultado[0];
                //DetalleOrden detalleOrden = (DetalleOrden) resultado[1];

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(orden.getIdOrden());
                row.createCell(1).setCellValue(orden.getFechaVenta().toString()); // Puedes formatear la fecha según tus necesidades
                row.createCell(2).setCellValue(orden.getSubtotal());
            }

            // Escribir el libro de trabajo en un flujo de bytes
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            System.out.println("Error al generar el archivo de Excel: " + e.getMessage());
            throw e;
        }
    }
	
}

