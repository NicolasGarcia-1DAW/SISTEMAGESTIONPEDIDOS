package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PedidoTest {

    @ParameterizedTest
    @CsvSource({
            "50,5,30,86.3",
            "20,5,10,32.1",
            "100,5,50,160.5"
    })
    void calcularTotalPedido(double precioFisico, double peso, double precioDigital, double totalEsperado) {

        Cliente cliente = new Cliente("JR-1", "Juan Rodriguez", 3, true, "España");

        Pedido pedido = new Pedido("P1|JR-1", cliente);

        ProductoFisico p1 = new ProductoFisico("1","Teclado", precioFisico, peso);
        ProductoDigital p2 = new ProductoDigital("2","Curso", precioDigital, 500);

        pedido.agregarProducto(p1);
        pedido.agregarProducto(p2);

        double total = pedido.calcularTotal();

        assertEquals(totalEsperado, total, 0.01);
    }
}