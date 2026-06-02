package com.sistemagestionpedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private String idPedido;
    private List<Producto> productos;
    private Cliente cliente;

    public Pedido(String idPedido, Cliente cliente) {

        if (idPedido == null || idPedido.isBlank()) {
            throw new IllegalArgumentException("El id del pedido no puede estar vacio");
        }

        this.idPedido = idPedido;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void eliminarProducto(Producto p) {
        productos.remove(p);
    }

    public double calcularTotal() {

        if (productos.isEmpty()) {
            throw new IllegalStateException("El pedido no tiene productos");
        }

        double total = 0;

        for (Producto producto : productos) {
            total += producto.calcularPrecioFinal();
        }

        return total;
    }

    public String mostrarResumen() {

        String resumen = "Resumen del Pedido:\n";

        resumen += "Id Pedido: " + idPedido + "\n";
        resumen += "Cliente: " + cliente.getNombre() + "\n";
        resumen += "Pais: " + cliente.getPais() + "\n";

        resumen += "Productos:\n";

        for (Producto producto : productos) {
            resumen += producto.toString() + "\n";
        }

        resumen += "Total: " + calcularTotal() + " euros\n";

        return resumen;
    }
}