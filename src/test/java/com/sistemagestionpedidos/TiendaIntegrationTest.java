package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tests de Integracion Tienda")
public class TiendaIntegrationTest {

    @Test
    @DisplayName("La tienda debe generar una factura correctamente")
    void realizarVentaGeneraFactura() {

        Cliente cliente = new Cliente("JR-1", "Juan Rodriguez", 3, true, "España");

        Pedido pedido = new Pedido("P1", cliente);

        pedido.agregarProducto(new ProductoFisico("1", "Teclado", 50.0, 2.0));

        pedido.agregarProducto(new ProductoDigital("2", "Curso", 30.0, 500));

        Tienda tienda = new Tienda();

        Factura factura = tienda.realizarVenta(cliente, pedido);

        assertNotNull(factura);
        assertNotNull(factura.getCodigoFactura());
        assertTrue(factura.getTotalFinal() > 0);
    }
}