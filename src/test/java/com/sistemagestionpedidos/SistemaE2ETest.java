package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * Cubre el flujo completo de compra desde la creación
 * del pedido hasta la generación de la factura final.
 */
public class SistemaE2ETest {

    @Test
    void flujoCompletoCompraHastaFactura() {

        Cliente cliente = new Cliente(1, "Juan Rodriguez", 5, true, "España");

        Pedido pedido = new Pedido(1, cliente);

        pedido.agregarProducto(new ProductoFisico(1, "Teclado", 50, 2), 1);
        pedido.agregarProducto(new ProductoDigital(2, "Curso", 30, 500), 2);

        Tienda tienda = new Tienda();

        Factura factura = tienda.realizarVenta(cliente, pedido);

        assertNotNull(factura);

        double totalNeto = 122.60;
        double totalIva = 12.60;
        double totalEnvio = 2.00;

        double subtotal = totalNeto + totalIva + totalEnvio;
        double descuento = subtotal * 0.15;
        double totalFinal = subtotal - descuento;

        assertEquals(totalNeto, factura.getTotalNeto());
        assertEquals(totalIva, factura.getTotalIva());
        assertEquals(totalEnvio, factura.getTotalEnvio());
        assertEquals(descuento, factura.getDescuento());
        assertEquals(totalFinal, factura.getTotalFinal());
    }
}