package com.example.appstoreperu.entidades;

public class Marca {
    private int id;
    private String nombreM;

    // Constructor vacío
    public Marca() {
    }

    // Constructor
    public Marca(int id, String nombreM) {
        this.id = id;
        this.nombreM = nombreM;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreM() {
        return nombreM;
    }

    public void setNombreM(String nombreM) {
        this.nombreM = nombreM;
    }

    // Sobrescribir toString permite que el Spinner muestre solo el nombre de la
    // marca, manteniendo internamente el objeto completo.
    @Override
    public String toString() {
        return nombreM;
    }
}
