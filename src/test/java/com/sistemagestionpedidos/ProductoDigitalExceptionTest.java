package com.sistemagestionpedidos;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ProductoDigitalExceptionTest {

    @Test
    void tamanoNegativo() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new ProductoDigital("2","Curso", 30.0, -500));
    }
}