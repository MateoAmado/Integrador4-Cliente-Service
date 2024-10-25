package org.Cliente.dto;

public class ProductoDTO {

    private Long id;
    private String nombre;
    private int cantidad_stock;
    private float valor;

    public ProductoDTO(Long id, String nombre, int cantidad_stock, float valor) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad_stock = cantidad_stock;
        this.valor = valor;
    }

    public ProductoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad_stock() {
        return cantidad_stock;
    }

    public void setCantidad_stock(int cantidad_stock) {
        this.cantidad_stock = cantidad_stock;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
