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
    private double descuento;

    /**
     * Constructor vacío.
     */
    public Factura() {
    }

    /**
     * Constructor de la factura generada.
     *
     * @param totalNeto importe base de los productos
     * @param totalIva importe total del IVA
     * @param totalEnvio importe de los gastos de envío
     * @param descuento descuento aplicado
     * @param totalFinal importe final de la compra
     *
     * @throws IllegalArgumentException si el total final es negativo
     */
    public Factura(double totalNeto, double totalIva, double totalEnvio, double descuento, double totalFinal) {

        if (totalFinal < 0) {
            throw new IllegalArgumentException("El total final no puede ser negativo");
        }

        this.codigoFactura = "FACT-" + LocalDate.now() + " " + (int)((Math.random() * 89999) + 10000);

        this.fechaEmision = LocalDate.now();

        this.totalNeto = totalNeto;
        this.totalIva = totalIva;
        this.totalEnvio = totalEnvio;
        this.descuento = descuento;
        this.totalFinal = totalFinal;
    }

    /**
     * Constructor completo.
     */
    public Factura(String codigoFactura, LocalDate fechaEmision, double totalNeto, double totalIva, double totalEnvio, double totalFinal, double descuento) {

        this.codigoFactura = codigoFactura;
        this.fechaEmision = fechaEmision;
        this.totalNeto = totalNeto;
        this.totalIva = totalIva;
        this.totalEnvio = totalEnvio;
        this.totalFinal = totalFinal;
        this.descuento = descuento;
    }

    public String getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
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

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {

        return "Factura: " + codigoFactura + "\n"
                + "Fecha de emision: " + fechaEmision + "\n"
                + "Total neto: " + totalNeto + "\n"
                + "Total IVA: " + totalIva + "\n"
                + "Total envio: " + totalEnvio + "\n"
                + "Descuento: " + descuento + "\n"
                + "Total final: " + totalFinal;
    }
}