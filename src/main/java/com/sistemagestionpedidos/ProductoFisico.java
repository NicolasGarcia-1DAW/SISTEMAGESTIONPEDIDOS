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

        double costeDestino = 0;

        if (pais.equalsIgnoreCase("España")) {
            costeDestino = 0;
        } else if (pais.equalsIgnoreCase("Francia")
                || pais.equalsIgnoreCase("Italia")
                || pais.equalsIgnoreCase("Portugal")) {

            costeDestino = 5;
        } else costeDestino = 10;

        return costeDestino + peso;
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