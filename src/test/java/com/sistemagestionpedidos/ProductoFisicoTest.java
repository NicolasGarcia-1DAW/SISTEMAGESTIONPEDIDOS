package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Protege el cálculo del precio final y de los costes de envío
 * de productos físicos según el país de destino.
 */
public class ProductoFisicoTest {

    @Test
    void calcularPrecioFinalProductoFisico() {

        ProductoFisico producto = new ProductoFisico(1, "Teclado", 50.0, 5.0);

        double resultado = producto.calcularPrecioFinal();

        assertEquals(50.0, resultado);
        assertTrue(resultado > 45.0);
        assertNotEquals(45.0, resultado);
    }

    @ParameterizedTest
    @CsvSource({
        "España, 2, 2",
        "Portugal, 2, 7",
        "Francia, 2, 7",
        "Italia, 2, 7",
        "Alemania, 2, 12"
    })
    void testCalcularCosteEnvioSegunPais(String pais, double peso, double esperado) {

        // Verifica las tarifas de envío por país definidas en el enunciado
        ProductoFisico producto = new ProductoFisico(1, "Teclado", 50, peso);

        assertEquals(esperado, producto.calcularCosteEnvio(pais));
    }
}