package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ProductoFisicoTest {

    @Test
    void calcularPrecioFinalProductoFisico() {

        ProductoFisico producto = new ProductoFisico(1, "Teclado", 50.0, 5.0);

        double resultado = producto.calcularPrecioFinal();

        assertEquals(50.0, resultado);
        assertTrue(resultado > 45.0);
        assertNotEquals(45.0, resultado);
    }
}