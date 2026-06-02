package com.sistemagestionpedidos;

import java.time.LocalDate;

/**
 * Representa la factura generada tras una venta.
 */
public class Factura {

    private String codigoFactura;
    private LocalDate fechaEmision;

    private double totalNeto;
    private double totalIva;
    private double totalEnvio;
    private double totalFinal;

    /**
     * Constructor de la factura generada.
     *
     * @param totalNeto importe base de los productos
     * @param totalIva importe total del IVA
     * @param totalEnvio importe de los gastos de envío
     * @param totalFinal importe final de la compra
     * 
     * @throws IllegalArgumentException si el total final es negativo
     */
    public Factura(double totalNeto, double totalIva, double totalEnvio, double totalFinal) {

        if (totalFinal < 0) {
            throw new IllegalArgumentException("El total final no puede ser negativo");
        }

        this.codigoFactura = "FAC-" + (1 + (int)(Math.random() * 9999));
        this.fechaEmision = LocalDate.now();

        this.totalNeto = totalNeto;
        this.totalIva = totalIva;
        this.totalEnvio = totalEnvio;
        this.totalFinal = totalFinal;
    }

    public String getCodigoFactura() {
        return codigoFactura;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public double getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(double totalNeto) {
        this.totalNeto = totalNeto;
    }

    public double getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(double totalIva) {
        this.totalIva = totalIva;
    }

    public double getTotalEnvio() {
        return totalEnvio;
    }

    public void setTotalEnvio(double totalEnvio) {
        this.totalEnvio = totalEnvio;
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(double totalFinal) {
        this.totalFinal = totalFinal;
    }

    @Override
    public String toString() {

        return "Factura\n"
                + "Codigo: " + codigoFactura + "\n"
                + "Fecha: " + fechaEmision + "\n"
                + "Total neto: " + totalNeto + " €\n"
                + "IVA: " + totalIva + " €\n"
                + "Envio: " + totalEnvio + " €\n"
                + "Total final: " + totalFinal + " €";
    }
}