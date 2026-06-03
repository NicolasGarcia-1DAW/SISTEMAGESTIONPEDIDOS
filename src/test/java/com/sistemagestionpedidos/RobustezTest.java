package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 * Protege las validaciones de entrada del sistema y el
 * lanzamiento de excepciones ante datos inválidos.
 */
public class RobustezTest {

    @Test
    void pedidoSinProductosDebeLanzarExcepcion() {

        Cliente cliente = new Cliente(1, "Juan Rodriguez", 3, true, "España");

        Pedido pedido = new Pedido(1, cliente);

        assertThrows(IllegalArgumentException.class, pedido::calcularTotal);
    }

    @Test
    void clienteConNombreVacioDebeLanzarExcepcion() {

        assertThrows(IllegalArgumentException.class, () -> new Cliente(1, "", 2, false, "España"));
    }
}