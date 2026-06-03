package com.sistemagestionpedidos;

public class ProductoFisico extends Producto {

    private double peso;

    public ProductoFisico(int id, String nombre, double precioBase, double peso) {
        super(id, nombre, precioBase);

        if (peso < 0) {
            throw new IllegalArgumentException("El peso no puede ser negativo");
        }

        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {

        if (peso < 0) {
            throw new IllegalArgumentException("El peso no puede ser negativo");
        }

        this.peso = peso;
    }

    public double calcularCosteEnvio(String pais) {

        if (pais.equalsIgnoreCase("España")) {
            return 0;
        }

        if (pais.equalsIgnoreCase("Francia")
                || pais.equalsIgnoreCase("Italia")
                || pais.equalsIgnoreCase("Portugal")) {

            return 5;
        }

        return 10;
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase();
    }

    @Override
    public String toString() {

        return "Fisico --> "
                + super.toString()
                + ", Peso: "
                + peso
                + " kg";
    }
}