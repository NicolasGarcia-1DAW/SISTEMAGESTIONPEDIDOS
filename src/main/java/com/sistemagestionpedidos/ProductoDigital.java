package com.sistemagestionpedidos;

public class ProductoDigital extends Producto {

    private double tamanoDescarga;

    public ProductoDigital(int id, String nombre, double precioBase, double tamanoDescarga) {
        super(id, nombre, precioBase);

        if (tamanoDescarga < 0) {
            throw new IllegalArgumentException("El tamaño de descarga no puede ser negativo");
        }

        this.tamanoDescarga = tamanoDescarga;
    }

    public double getTamanoDescarga() {
        return tamanoDescarga;
    }

    public void setTamanoDescarga(double tamanoDescarga) {

        if (tamanoDescarga < 0) {
            throw new IllegalArgumentException("El tamaño de descarga no puede ser negativo");
        }

        this.tamanoDescarga = tamanoDescarga;
    }

    public double aplicarIVA(String tipoIva) {

        switch (tipoIva.toUpperCase()) {

            case "GENERAL":
                return Math.round(getPrecioBase() * 1.21 * 100.0) / 100.0;

            case "REDUCIDO":
                return Math.round(getPrecioBase() * 1.10 * 100.0) / 100.0;

            case "SUPER":
                return Math.round(getPrecioBase() * 1.04 * 100.0) / 100.0;

            default:
                throw new IllegalArgumentException("Tipo de IVA no válido");
        }
    }

    @Override
    public double calcularPrecioFinal() {
        return aplicarIVA("GENERAL");
    }

    @Override
    public String toString() {
        return "Digital --> "
                + super.toString()
                + ", Tamaño de Descarga: "
                + tamanoDescarga
                + " MB";
    }
}