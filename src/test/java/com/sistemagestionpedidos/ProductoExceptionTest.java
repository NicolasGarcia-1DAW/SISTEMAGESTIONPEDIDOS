package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ProductoExceptionTest {

    @Test
    void crearProductoFisicoConPrecioNegativo() {

        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> new ProductoFisico("1", "ProductoError", -10, 5)
        );

        assertEquals(
            "El precio no puede ser negativo",
            exception.getMessage()
        );
    }

    @Test
    void crearProductoDigitalConPrecioNegativo() {

        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> new ProductoDigital("2", "ProductoError", -10, 100)
        );

        assertEquals(
            "El precio no puede ser negativo",
            exception.getMessage()
        );
    }
}