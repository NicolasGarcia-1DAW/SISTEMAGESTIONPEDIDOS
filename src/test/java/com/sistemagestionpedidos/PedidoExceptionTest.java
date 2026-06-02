package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class PedidoExceptionTest {

    
    @Test
    void calcularTotalPedidoSinProductos() {

        Cliente cliente = new Cliente("Juan", "juan@email.com", "Calle 1");

        Pedido pedido = new Pedido(cliente);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            pedido.calcularTotal();
        });

        assertEquals("El pedido no tiene productos", exception.getMessage());
    }
}