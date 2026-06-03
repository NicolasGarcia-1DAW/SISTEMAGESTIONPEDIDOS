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

        if (cliente != pedido.getCliente()) {
            throw new IllegalArgumentException("El cliente de la venta no coincide con el cliente del pedido");
        }

        double totalNeto = pedido.calcularTotal();
        double totalEnvio = calcularEnvio(pedido, cliente);
        double totalIva = calcularIva(pedido);

        double subtotal = totalNeto + totalEnvio + totalIva;

        double porcentajeDescuento = cliente.calcularDescuentoFidelidad();
        double descuento = subtotal * porcentajeDescuento;

        double totalFinal = subtotal - descuento;

        return new Factura(totalNeto, totalIva, totalEnvio, descuento, totalFinal);
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

        for (Producto producto : pedido.getProductos()) {

            if (producto instanceof ProductoFisico) {

                ProductoFisico pf = (ProductoFisico) producto;

                int cantidad = pedido.getCantidades().get(producto.getId());

                totalEnvio += pf.calcularCosteEnvio(cliente.getPais()) * cantidad;
            }
        }

        return totalEnvio;
    }

    /**
     * Obtiene el IVA total generado por los productos digitales
     * del pedido utilizando el tipo de IVA general.
     *
     * @param pedido pedido a procesar
     * @return importe total del IVA de los productos digitales
     */
    private double calcularIva(Pedido pedido) {
        return pedido.calcularIva("GENERAL");
    }
}