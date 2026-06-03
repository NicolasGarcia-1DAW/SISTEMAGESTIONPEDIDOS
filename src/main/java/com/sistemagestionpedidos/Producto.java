package com.sistemagestionpedidos;

public class Producto {

    private int id;
    private String nombre;
    private double precioBase;

    public Producto(int id, String nombre, double precioBase) {

        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser mayor que cero");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }

        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser mayor que cero");
        }

        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }

        this.nombre = nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {

        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        this.precioBase = precioBase;
    }

    public double calcularPrecioFinal() {
        return precioBase;
    }

    @Override
    public String toString() {
        return "Producto [id=" + id
                + ", nombre=" + nombre
                + ", precioBase=" + precioBase + "]";
    }
}