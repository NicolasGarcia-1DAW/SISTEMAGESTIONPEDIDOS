package com.sistemagestionpedidos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pedido {

    public static final String PRODUCT_LIST_EMPTY_EXCEPTION_MESSAGE = "El pedido no tiene productos";

    private int idPedido;
    private List<Producto> productos;
    private Map<Integer, Integer> cantidades;
    private Cliente cliente;

    public Pedido(int idPedido, Cliente cliente) {

        if (idPedido <= 0) {
            throw new IllegalArgumentException("El id del pedido debe ser mayor que cero");
        }

        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }

        this.idPedido = idPedido;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.cantidades = new HashMap<>();
    }

    public Pedido(int idPedido, Cliente cliente, List<Producto> productos, Map<Integer, Integer> cantidades) {

        if (idPedido <= 0) {
            throw new IllegalArgumentException("El id del pedido debe ser mayor que cero");
        }

        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }

        if (productos == null || cantidades == null) {
            throw new IllegalArgumentException("Productos y cantidades no pueden ser nulos");
        }

        for (Producto producto : productos) {

            if (!cantidades.containsKey(producto.getId())) {
                throw new IllegalArgumentException("Falta la cantidad de alguno de los productos");
            }
        }

        this.idPedido = idPedido;
        this.cliente = cliente;
        this.productos = new ArrayList<>(productos);
        this.cantidades = new HashMap<>(cantidades);
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    public void setProductos(List<Producto> productos) {
        this.productos = new ArrayList<>(productos);
    }

    public Map<Integer, Integer> getCantidades() {
        return new HashMap<>(cantidades);
    }

    public void setCantidades(Map<Integer, Integer> cantidades) {
        this.cantidades = new HashMap<>(cantidades);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void agregarProducto(Producto producto, int cantidad) {

        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }

        productos.add(producto);
        cantidades.put(producto.getId(), cantidad);
    }

    public void eliminarProducto(Producto producto) {

        productos.remove(producto);
        cantidades.remove(producto.getId());
    }

    public double calcularTotal() {

        if (productos.isEmpty()) {
            throw new IllegalStateException(PRODUCT_LIST_EMPTY_EXCEPTION_MESSAGE);
        }

        double total = 0;

        for (Producto producto : productos) {

            int cantidad = cantidades.get(producto.getId());

            total += producto.calcularPrecioFinal() * cantidad;
        }

        return total;
    }

    public double calcularEnvio(String pais) {

        double totalEnvio = 0;

        for (Producto producto : productos) {

            if (producto instanceof ProductoFisico) {

                ProductoFisico pf = (ProductoFisico) producto;

                int cantidad = cantidades.getOrDefault(producto.getId(), 0);

                totalEnvio += pf.calcularCosteEnvio(pais) * cantidad;
            }
        }

        return totalEnvio;
    }

    public double calcularIva(String tipoIva) {

        double totalIva = 0;

        for (Producto producto : productos) {

            if (producto instanceof ProductoDigital) {

                ProductoDigital pd = (ProductoDigital) producto;

                int cantidad = cantidades.get(producto.getId());

                totalIva += (pd.aplicarIVA(tipoIva) - pd.getPrecioBase()) * cantidad;
            }
        }

        return totalIva;
    }

    public String mostrarResumen() {

        String resumen = "Resumen del Pedido:\n";

        resumen += "Id Pedido: " + idPedido + "\n";
        resumen += "Cliente: " + cliente.getNombre() + "\n";
        resumen += "Pais: " + cliente.getPais() + "\n";

        resumen += "Productos:\n";

        for (Producto producto : productos) {

            resumen += producto.toString()
                    + " | Cantidad: "
                    + cantidades.get(producto.getId())
                    + "\n";
        }

        resumen += "Total: " + calcularTotal() + " euros\n";

        return resumen;
    }
}