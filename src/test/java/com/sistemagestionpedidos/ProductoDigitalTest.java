package com.sistemagestionpedidos;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ProductoDigitalTest {

    @Test
    void calcularPrecioFinalProductoDigital() {

        ProductoDigital producto = new ProductoDigital("Videojuego", 40.0, 500);

        double resultado = producto.calcularPrecioFinal();

        assertEquals(40.0, resultado);
        assertTrue(resultado == 40.0);
        assertNotEquals(50.0, resultado);
    }
}