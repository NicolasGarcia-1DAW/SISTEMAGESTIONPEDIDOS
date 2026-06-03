package com.sistemagestionpedidos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pedido {

    public static final String PRODUCT_LIST_EMPTY_EXCEPTION_MESSAGE = "El pedido no tiene productos";

    private int idPedido;
    private List<Producto> productos;

    /**
     * Se usa Map<Producto, Integer> en lugar de Map<Integer, Integer>
     *
     * Aunque el enunciado indica usar el id del producto como clave,
     * el test "testCambiarIdDeProductoNoDeberiaRomperPedido" modifica el id
     * de un producto después de añadirlo al pedido. Asi que, si usáramos el id como clave
     * del mapa, el pedido se rompería porque la clave cambiaría.
     *
     * SOLUCIÓN: usar el objeto Producto como clave internamente para mantener
     * la identidad del producto independientemente de cambios en su id.
     * El getter público getCantidades() expone Map<Integer, Integer> para
     * compatibilidad con los tests que acceden por id.
     */
    private Map<Producto, Integer> cantidades;

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

        this.idPedido = idPedido;
        this.cliente = cliente;

        // COPIA DEFENSIVA de la lista
        this.productos = new ArrayList<>(productos);

        // COPIA DEFENSIVA REAL del mapa externo
        Map<Integer, Integer> copiaCantidades = new HashMap<>(cantidades);

        this.cantidades = new HashMap<>();

        for (Producto producto : productos) {

            Integer cantidad = copiaCantidades.get(producto.getId());

            if (cantidad == null) {
                throw new IllegalArgumentException("Falta la cantidad de alguno de los productos");
            }

            this.cantidades.put(producto, cantidad);
        }
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * Se devuelve copia defensiva para evitar que modificaciones externas
     * alteren el estado interno del pedido.
     */
    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    public void setProductos(List<Producto> productos) {
        this.productos = new ArrayList<>(productos);
    }

    /**
     * Devuelve una copia defensiva del mapa de cantidades con el ID del producto
     * como clave (Map<Integer, Integer>), para que los tests puedan acceder
     * por id sin acoplarse al objeto Producto.
     *
     * El mapa interno sigue usando Producto como clave para garantizar que
     * cambios en el id del producto no rompan los cálculos del pedido.
     */
    public Map<Integer, Integer> getCantidades() {
        Map<Integer, Integer> resultado = new HashMap<>();

        for (Map.Entry<Producto, Integer> entry : cantidades.entrySet()) {
            resultado.put(entry.getKey().getId(), entry.getValue());
        }

        return resultado;
    }
 
    /**
     * Permite reemplazar el mapa de cantidades completo.
     * Recibe Map<Producto, Integer> internamente para mantener coherencia.
     */
    public void setCantidades(Map<Producto, Integer> cantidades) {
        // Copia defensiva del mapa recibido
        this.cantidades = new HashMap<>();
        
        for (Map.Entry<Producto, Integer> entry : cantidades.entrySet()) {
            this.cantidades.put(entry.getKey(), entry.getValue());
        }
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
        cantidades.put(producto, cantidad);
    }

    public void eliminarProducto(Producto producto) {

        productos.remove(producto);
        cantidades.remove(producto);
    }

    public double calcularTotal() {

        if (productos.isEmpty()) {
            throw new IllegalArgumentException(PRODUCT_LIST_EMPTY_EXCEPTION_MESSAGE);
        }

        double total = 0;

        for (Producto producto : productos) {

            Integer cantidad = cantidades.get(producto);

            // Evita NullPointerException si el producto no tiene cantidad asociada
            if (cantidad == null) {
                cantidad = 0;
            }

            total += producto.calcularPrecioFinal() * cantidad;
        }

        return Math.round(total * 100.0) / 100.0;
    }

    public double calcularEnvio(String pais) {

        double totalEnvio = 0;

        for (Producto producto : productos) {

            if (producto instanceof ProductoFisico) {

                ProductoFisico pf = (ProductoFisico) producto;

                Integer cantidad = cantidades.get(producto);

                // Seguridad ante posibles inconsistencias del mapa
                if (cantidad == null) {
                    cantidad = 0;
                }

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

                Integer cantidad = cantidades.get(producto);

                // Seguridad ante valores nulos en el mapa
                if (cantidad == null) {
                    cantidad = 0;
                }

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
                    + cantidades.get(producto)
                    + "\n";
        }

        resumen += "Total: " + calcularTotal() + " euros\n";

        return resumen;
    }
}