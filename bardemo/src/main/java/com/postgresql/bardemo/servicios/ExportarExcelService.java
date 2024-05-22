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
import com.postgresql.bardemo.repositorio.DetalleOrdenRepo;

@Service
public class ExportarExcelService {

    @Autowired
    private OrdenRepo ordenRepo;

    @Autowired
    private DetalleOrdenRepo detalleOrdenRepo;

    public byte[] exportarExcel(Date fechaInicio, Date fechaFin, Integer idSucursal) throws IOException {
        LocalDateTime fechaInicioLocalDateTime = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime fechaFinLocalDateTime = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        List<Object[]> objetos = ordenRepo.totalOrdenes(fechaInicioLocalDateTime, fechaFinLocalDateTime, idSucursal);

        // Obtener el valor total de compra
        Double totalCompra = detalleOrdenRepo.sumTotalPurchase(fechaInicioLocalDateTime, fechaFinLocalDateTime, idSucursal);

        // Obtener el producto más vendido
        List<Object[]> productoMasVendido = detalleOrdenRepo.productoMasVendido(fechaInicioLocalDateTime, fechaFinLocalDateTime, idSucursal);

        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            // Primera hoja: Reporte de órdenes
            Sheet sheet1 = workbook.createSheet("Reporte de Ordenes");

            // Crear el encabezado
            Row headerRow = sheet1.createRow(0);
            String[] columnas = {"ID Orden", "Fecha Venta", "Total"};
            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
            }

            // Llenar el excel con los datos de los reportes
            int rowNum = 1;
            Long totalVentas = 0L;
            for (Object[] resultado : objetos) {
                Orden orden = (Orden) resultado[0];
                Long total = (Long) resultado[1];

                Row row = sheet1.createRow(rowNum++);
                row.createCell(0).setCellValue(orden.getIdOrden());
                row.createCell(1).setCellValue(orden.getFechaVenta().toString()); // Puedes formatear la fecha según tus necesidades
                row.createCell(2).setCellValue(total);

                totalVentas += total;
            }

            // Calcular la ganancia total
            Double gananciaTotal = totalVentas - totalCompra;

            // Añadir filas de totales
            Row totalVentasRow = sheet1.createRow(rowNum++);
            totalVentasRow.createCell(1).setCellValue("Total Ventas");
            totalVentasRow.createCell(2).setCellValue(totalVentas);

            Row totalCompraRow = sheet1.createRow(rowNum++);
            totalCompraRow.createCell(1).setCellValue("Total Compra");
            totalCompraRow.createCell(2).setCellValue(totalCompra);

            Row gananciaTotalRow = sheet1.createRow(rowNum++);
            gananciaTotalRow.createCell(1).setCellValue("Ganancia Total");
            gananciaTotalRow.createCell(2).setCellValue(gananciaTotal);

            // Segunda hoja: Producto más vendido
            Sheet sheet2 = workbook.createSheet("Producto Más Vendido");

            // Crear el encabezado para producto más vendido
            Row headerRow2 = sheet2.createRow(0);
            String[] columnas2 = {"Nombre Producto", "Precio Compra", "Total Vendido"};
            for (int i = 0; i < columnas2.length; i++) {
                Cell cell = headerRow2.createCell(i);
                cell.setCellValue(columnas2[i]);
            }

            // Llenar la hoja con el producto más vendido
            if (!productoMasVendido.isEmpty()) {
                Object[] producto = productoMasVendido.get(0);
                Row row = sheet2.createRow(1);
                row.createCell(0).setCellValue((String) producto[0]); // Nombre del producto
                row.createCell(1).setCellValue((Integer) producto[1]); // Precio de compra
                row.createCell(2).setCellValue((Long) producto[2]); // Total vendido
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
