package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class RobustezTest {

    @Test
    void pedidoSinProductosDebeLanzarExcepcion() {

        Cliente cliente = new Cliente("JR-1", "Juan Rodriguez", 3, true, "España");

        Pedido pedido = new Pedido("P1", cliente);

        assertThrows(IllegalStateException.class, pedido::calcularTotal);
    }

    @Test
    void clienteConNombreVacioDebeLanzarExcepcion() {

        assertThrows(IllegalArgumentException.class, () -> new Cliente("1", "", 2, false, "España"));
    }
}