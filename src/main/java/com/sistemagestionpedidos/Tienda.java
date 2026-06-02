package com.sistemagestionpedidos;

/**
 * Clase encargada de dirigir el proceso de venta.
 * Coordina Cliente, Pedido y genera una Factura final.
 */
public class Tienda {

    /**
     * Realiza una venta completa en el sistema.
     * Calcula el total del pedido, aplica costes de envío
     * y genera una factura final.
     *
     * @param cliente cliente que realiza la compra
     * @param pedido pedido con los productos seleccionados
     * @return Factura generada con el resumen de la venta
     * @throws IllegalArgumentException si cliente o pedido son nulos
     */
    public Factura realizarVenta(Cliente cliente, Pedido pedido) {

        if (cliente == null || pedido == null) {
            throw new IllegalArgumentException("Cliente o pedido no pueden ser nulos");
        }

        double totalProductos = pedido.calcularTotal();
        double totalEnvio = calcularEnvio(pedido, cliente);

        double subtotal = totalProductos + totalEnvio;

        double descuento = cliente.calcularDescuentoFidelidad();
        double totalDescuento = subtotal * descuento;

        double totalFinal = subtotal - totalDescuento;

        double totalIva = totalProductos * 0.21; // simplificado (válido para práctica)

        return new Factura(subtotal, totalIva, totalEnvio, totalFinal);
    }

    /**
     * Calcula el coste total de envío de todos los productos físicos.
     *
     * @param pedido pedido con los productos
     * @param cliente cliente para determinar el país de envío
     * @return coste total de envío
     */
    private double calcularEnvio(Pedido pedido, Cliente cliente) {

        double totalEnvio = 0;

        for (Producto p : pedido.getProductos()) {

            if (p instanceof ProductoFisico) {
                ProductoFisico pf = (ProductoFisico) p;
                totalEnvio += pf.calcularCosteEnvio(cliente.getPais());
            }
        }

        return totalEnvio;
    }

}