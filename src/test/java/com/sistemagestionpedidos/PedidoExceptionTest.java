package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class PedidoExceptionTest {

    
    @Test
    void calcularTotalPedidoSinProductos() {

        Cliente cliente = new Cliente(1, "Juan Rodriguez", 3, true, "España");

        Pedido pedido = new Pedido(1, cliente);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            pedido.calcularTotal();
        });

        assertEquals("El pedido no tiene productos", exception.getMessage());
    }
}