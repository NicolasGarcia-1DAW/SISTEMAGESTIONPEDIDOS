package com.sistemagestionpedidos;

public class Cliente {

    private String id;
    private String nombre;
    private int anosAntiguedad;
    private boolean esVip;
    private String pais;

    public Cliente(String id, String nombre, int anosAntiguedad, boolean esVip, String pais) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id no puede estar vacio");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }

        if (anosAntiguedad < 0) {
            throw new IllegalArgumentException("Los años de antiguedad no pueden ser negativos");
        }

        if (pais == null || pais.isBlank()) {
            throw new IllegalArgumentException("El pais no puede estar vacio");
        }

        this.id = id;
        this.nombre = nombre;
        this.anosAntiguedad = anosAntiguedad;
        this.esVip = esVip;
        this.pais = pais;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnosAntiguedad() {
        return this.anosAntiguedad;
    }

    public void setAnosAntiguedad(int anosAntiguedad) {
        this.anosAntiguedad = anosAntiguedad;
    }

    public boolean isEsVip() {
        return this.esVip;
    }

    public void setEsVip(boolean esVip) {
        this.esVip = esVip;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public double calcularDescuentoFidelidad() {

        double descuento = 0;

        if (esVip) {
            descuento += 0.10; // 10%
        }

        if (anosAntiguedad >= 5) {
            descuento += 0.05; // 5%
        } else if (anosAntiguedad >= 2) {
            descuento += 0.02; // 2%
        }

        return descuento;
    }
}