package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class SistemaE2ETest {

    @Test
    void flujoCompletoCompraHastaFactura() {

        Cliente cliente = new Cliente("JR-1", "Juan Rodriguez", 5, true, "España");

        Pedido pedido = new Pedido("P1", cliente);

        pedido.agregarProducto(new ProductoFisico("1", "Teclado", 50, 2));

        pedido.agregarProducto(new ProductoDigital("2", "Curso", 30, 500));

        Tienda tienda = new Tienda();

        Factura factura = tienda.realizarVenta(cliente, pedido);

        assertNotNull(factura);
        assertTrue(factura.getTotalFinal() > 0);
    }
}