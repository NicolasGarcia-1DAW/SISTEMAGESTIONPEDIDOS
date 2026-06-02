package com.sistemagestionpedidos;

public class ProductoFisico extends Producto {
    private double costeEnvio;

    public ProductoFisico(String nombre, double precio, double costeEnvio) {
        super(nombre, precio);
        
        if (costeEnvio < 0) {
            throw new IllegalArgumentException("El coste de envio no puede ser negativo");
        }
        this.costeEnvio = costeEnvio;
    }

    public double getCosteEnvio() {
        return costeEnvio;
    }

    public void setCosteEnvio(double costeEnvio) {
        this.costeEnvio = costeEnvio;
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecio() + costeEnvio;
    }

    @Override
    public String toString() {
        return "Fisico --> " + super.toString() + ", Coste de Envío: " + costeEnvio;
    }
}
