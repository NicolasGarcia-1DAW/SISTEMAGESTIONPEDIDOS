package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tests de Integracion")
public class GestionPedidosIntegrationTest {

    @Test
    @DisplayName("Calculo correcto de total con productos fisicos y digitales")
    void deberiaCalcularTotalCorrectamente() {

        Cliente cliente =
                new Cliente("Juan", "juan@email.com", "Calle 1");

        Pedido pedido = new Pedido(cliente);

        pedido.agregarProducto(
                new ProductoFisico("Teclado", 50.0, 5.0));

        pedido.agregarProducto(
                new ProductoDigital("Curso", 30.0, 500));

        double total = pedido.calcularTotal();

        assertEquals(85.0, total);
    }
}