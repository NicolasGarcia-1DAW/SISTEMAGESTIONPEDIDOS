package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tests de Integracion")
public class PedidoProductoIntegrationTest {

    @Test
    @DisplayName("Calculo correcto de total con productos fisicos y digitales")
    void deberiaCalcularTotalCorrectamente() {

        Cliente cliente = new Cliente(1, "Juan Rodriguez", 3, true, "España");

        Pedido pedido = new Pedido(1, cliente);

        pedido.agregarProducto(new ProductoFisico(1,"Teclado", 50.0, 5.0), 1);
        pedido.agregarProducto(new ProductoDigital(2,"Curso", 30.0, 500), 1);

        double total = pedido.calcularTotal();

        assertEquals(86.3, total);
    }
}