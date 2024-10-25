package org.Cliente.dto;

import java.util.Date;

public class CompraDTO {

    private Long idProducto;
    private Long idCliente;
    private Integer cantidad;

    private Date fecha;


    public CompraDTO(Long idProducto, Long idCliente, Integer cantidad, Date fecha) {
        this.idProducto = idProducto;
        this.idCliente = idCliente;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public CompraDTO(Long idProducto) {
        super();
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
