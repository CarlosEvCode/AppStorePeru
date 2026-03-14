package com.example.appstoreperu.entidades;

public class Producto {
    private int id;
    private int id_marca;
    private String descripcion;
    private double precio;
    private int stock;
    private int garantia;

    // Este campo nos sirve para mostrar el nombre de la marca en la lista
    // traido del INNER JOIN del backend
    private String nombre_marca;

    // Constructor vacío
    public Producto() {
    }

    // Constructor completo
    public Producto(int id, int id_marca, String descripcion, double precio, int stock, int garantia,
            String nombre_marca) {
        this.id = id;
        this.id_marca = id_marca;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.garantia = garantia;
        this.nombre_marca = nombre_marca;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

    public String getNombre_marca() {
        return nombre_marca;
    }

    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }
}
