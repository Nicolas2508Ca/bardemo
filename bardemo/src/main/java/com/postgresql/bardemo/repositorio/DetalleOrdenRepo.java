package com.postgresql.bardemo.repositorio;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.postgresql.bardemo.modelo.DetalleOrden;
import com.postgresql.bardemo.modelo.Orden;

@Repository
public interface DetalleOrdenRepo extends JpaRepository<DetalleOrden, Long>{
	
	List<DetalleOrden> findByOrden(Orden orden);
	@Query("SELECT SUM(d.cantidad * p.precioCompra) " +
	           "FROM DetalleOrden d " +
	           "JOIN d.producto p " +
	           "JOIN d.orden o " +
	           "WHERE o.fechaVenta BETWEEN :fechaInicio AND :fechaFin " +
	           "AND o.sucursal.idSucursal = :idSucursal")
	    Double sumTotalPurchase(@Param("fechaInicio") LocalDateTime fechaInicio, 
	                            @Param("fechaFin") LocalDateTime fechaFin, 
	                            @Param("idSucursal") Integer idSucursal);
	
	@Query("SELECT p.nombreProducto, p.precioCompra, SUM(d.cantidad) AS totalVendido " +
	           "FROM DetalleOrden d " +
	           "JOIN d.producto p " +
	           "JOIN d.orden o " +
	           "WHERE o.fechaVenta BETWEEN :fechaInicio AND :fechaFin " +
	           "AND o.sucursal.idSucursal = :idSucursal " +
	           "GROUP BY p.nombreProducto, p.precioCompra " +
	           "ORDER BY totalVendido DESC " +
	           "LIMIT 1")
	    List<Object[]> productoMasVendido(@Param("fechaInicio") LocalDateTime fechaInicio, 
	                                      @Param("fechaFin") LocalDateTime fechaFin, 
	                                      @Param("idSucursal") Integer idSucursal);
}
