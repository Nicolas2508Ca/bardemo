package com.postgresql.bardemo.repositorio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.postgresql.bardemo.DTO.ReporteDTO;
import com.postgresql.bardemo.modelo.DetalleOrden;
import com.postgresql.bardemo.modelo.Mesa;
import com.postgresql.bardemo.modelo.Orden;

@Repository
public interface OrdenRepo extends JpaRepository<Orden, Long>{
	
	@Query("SELECT o FROM Orden o WHERE o.mesa.idMesa = :idMesa AND o.idEstado.idEstado = 1")
	Optional<Orden> findByMesa(@Param("idMesa") Integer idMesa);
	
	@Query("SELECT o, od, od.producto, o.subtotal, SUM(od.cantidad) AS cantidad "
	           + "FROM Orden o INNER JOIN DetalleOrden od ON o.idOrden = od.orden.idOrden "
	           + "WHERE o.sucursal.idSucursal = :idSucursal AND  o.fechaVenta BETWEEN :fechaInicio AND :fechaFin "
	           + "GROUP BY o.idOrden, od.idDetalle, od.producto "
	           + "ORDER BY SUM(od.cantidad) DESC")
	 List<Object[]> findByFechaVentaBetween(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin, @Param("idSucursal") Integer idSucursal);
	    @Query("SELECT o, od, od.producto, o.subtotal, SUM(od.cantidad) AS cantidad "
		           + "FROM Orden o INNER JOIN DetalleOrden od ON o.idOrden = od.orden.idOrden "
		           + "WHERE o.fechaVenta BETWEEN :fechaInicio AND :fechaFin "
		           + "GROUP BY o.idOrden, od.idDetalle, od.producto "
		           + "ORDER BY SUM(od.cantidad) DESC")
	List<Object[]> findByFechaVentaBetween(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);
	@Query("SELECT o, SUM(o.subtotal) as total"
			+ " FROM Orden o"
			+ " WHERE o.sucursal.idSucursal = :idSucursal AND  o.fechaVenta BETWEEN :fechaInicio AND :fechaFin "
			+ " GROUP BY o.idOrden")
	List<Object[]> totalOrdenes(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin, @Param("idSucursal")Integer idSucursal);
	
	@Query("SELECT p.nombreProducto, p.precioCompra, p.precioProducto, SUM(d.cantidad) as totalVendido, " +
	           "SUM(d.cantidad * p.precioProducto) as totalVentas, " +
	           "SUM(d.cantidad * p.precioProducto) - SUM(d.cantidad * p.precioCompra) as ganancia " +
	           "FROM DetalleOrden d " +
	           "INNER JOIN d.producto p " +
	           "INNER JOIN d.orden o " +
	           "WHERE o.sucursal.idSucursal = :idSucursal AND  o.fechaVenta BETWEEN :fechaInicio AND :fechaFin " +
	           "GROUP BY p.nombreProducto, p.precioCompra, p.precioProducto " +
	           "ORDER BY totalVendido DESC " +
	           "LIMIT 1")
	    List<Object[]> gananciaProductos(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin, @Param("idSucursal") Integer idSucursal);
	    
	    @Query("SELECT p.nombreProducto, p.precioCompra, p.precioProducto, SUM(d.cantidad) as totalVendido, " +
		           "SUM(d.cantidad * p.precioProducto) as totalVentas, " +
		           "SUM(d.cantidad * p.precioProducto) - SUM(d.cantidad * p.precioCompra) as ganancia " +
		           "FROM DetalleOrden d " +
		           "INNER JOIN d.producto p " +
		           "INNER JOIN d.orden o " +
		           "WHERE o.sucursal.idSucursal = :idSucursal AND  o.fechaVenta BETWEEN :fechaInicio AND :fechaFin " +
		           "GROUP BY p.nombreProducto, p.precioCompra, p.precioProducto " +
		           "ORDER BY totalVendido DESC ") 
	    List<Object[]> gananciasTotales();
	
	
}
