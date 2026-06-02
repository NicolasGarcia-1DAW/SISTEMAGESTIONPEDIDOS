package com.sistemagestionpedidos;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ProductoDigitalTest {

    @Test
    void calcularPrecioFinalProductoDigital() {

        ProductoDigital producto = new ProductoDigital("2","Curso", 30.0, 500);

        double resultado = producto.calcularPrecioFinal();

        assertEquals(36.3, resultado, 0.01);
        assertTrue(resultado > 30);
        assertNotEquals(50.0, resultado);
    }
}