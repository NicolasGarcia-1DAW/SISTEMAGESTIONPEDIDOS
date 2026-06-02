package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ProductoFisicoExceptionTest {

    @Test
    void costeEnvioNegativo() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new ProductoFisico("1", "Teclado", 50, -5));
    }
}