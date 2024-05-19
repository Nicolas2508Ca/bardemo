package com.postgresql.bardemo.servicios;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
        List<Object[]> objetos = ordenRepo.totalOrdenes(fechaInicioLocalDateTime, fechaFinLocalDateTime, idSucursal);

        // Obtener los productos más vendidos y sus ganancias
        List<Object[]> topProductos = ordenRepo.gananciaProductos(fechaInicioLocalDateTime, fechaFinLocalDateTime,idSucursal);

        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            // Primera hoja: Reporte de órdenes
            Sheet sheet1 = workbook.createSheet("Reporte de Ordenes");

            // Crear el encabezado
            Row headerRow1 = sheet1.createRow(0);
            String[] columnas1 = {"ID Orden", "Fecha Venta", "Total"};
            for (int i = 0; i < columnas1.length; i++) {
                Cell cell = headerRow1.createCell(i);
                cell.setCellValue(columnas1[i]);
            }

            // Llenar el excel con los datos de los reportes
            int rowNum1 = 1;
            Long totalVentas = 0L;
            for (Object[] resultado : objetos) {
                Orden orden = (Orden) resultado[0];
                Long total = (Long) resultado[1];

                Row row = sheet1.createRow(rowNum1++);
                row.createCell(0).setCellValue(orden.getIdOrden());
                row.createCell(1).setCellValue(orden.getFechaVenta().toString()); // Puedes formatear la fecha según tus necesidades
                row.createCell(2).setCellValue(total);
                
                totalVentas += total;
            }

            Row totalRow = sheet1.createRow(rowNum1);
            totalRow.createCell(1).setCellValue("Total Ventas");
            totalRow.createCell(2).setCellValue(totalVentas);

            // Segunda hoja: Información de productos más vendidos
            Sheet sheet2 = workbook.createSheet("Productos más Vendidos");

            // Crear el encabezado
            Row headerRow2 = sheet2.createRow(0);
            String[] columnas2 = {"Nombre Producto", "Precio Compra", "Precio Venta", "Total Vendido", "Total Ventas", "Ganancia"};
            for (int i = 0; i < columnas2.length; i++) {
                Cell cell = headerRow2.createCell(i);
                cell.setCellValue(columnas2[i]);
            }

            // Llenar la hoja con los datos de los productos más vendidos
            int rowNum2 = 1;
            for (Object[] producto : topProductos) {
                Row row = sheet2.createRow(rowNum2++);
                row.createCell(0).setCellValue((String) producto[0]);
                row.createCell(1).setCellValue((Integer) producto[1]);
                row.createCell(2).setCellValue((Integer) producto[2]);
                row.createCell(3).setCellValue((Long) producto[3]);
                row.createCell(4).setCellValue((Long) producto[4]);
                row.createCell(5).setCellValue((Long) producto[5]);
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
