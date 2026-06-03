package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tests de Integracion")
public class PedidoProductoIntegrationTest {

    @Test
    @DisplayName("Calculo correcto de total con productos fisicos y digitales")
    void deberiaCalcularTotalCorrectamente() {

        Cliente cliente = new Cliente("JR-1", "Juan Rodriguez", 3, true, "España");

        Pedido pedido = new Pedido("P1|JR-1", cliente);

        pedido.agregarProducto(new ProductoFisico("1","Teclado", 50.0, 5.0));
        pedido.agregarProducto(new ProductoDigital("2","Curso", 30.0, 500));

        double total = pedido.calcularTotal();

        assertEquals(85.0, total);
    }
}