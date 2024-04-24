package com.postgresql.bardemo.modelo;

public class DetalleOrdenRequest {
    private Productos producto;
    private Integer cantidad;
    private Integer subTotal;
    
    public DetalleOrdenRequest() {}

    public DetalleOrdenRequest(Productos producto, Integer cantidad, Integer subTotal) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }
}
