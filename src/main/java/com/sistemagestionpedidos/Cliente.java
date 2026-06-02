package com.sistemagestionpedidos;

public class Cliente {

    private String nombre;
    private String correo;
    private String direccion;

    public Cliente(String nombre, String correo, String direccion) {

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("El correo no puede estar vacio");
        }
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("La direccion no puede estar vacia");
        }

        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
