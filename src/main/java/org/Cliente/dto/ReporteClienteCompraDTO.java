package org.Cliente.dto;

public class ReporteClienteCompraDTO {

    private Long idCliente;
    private String nombre;
    private String email;
    private float montoTotal;

    public ReporteClienteCompraDTO(Long idCliente, String nombre, String email, float montoTotal) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        this.montoTotal = montoTotal;
    }

    public ReporteClienteCompraDTO() {
        this.idCliente = 0L;
        this.nombre = "";
        this.email = "";
        this.montoTotal = 0;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }
}
