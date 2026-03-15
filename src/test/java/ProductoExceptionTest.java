import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoExceptionTest {

    @Test
    void crearProductoConPrecioNegativo() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Producto("ProductoError", -10);
        });

        assertEquals("El precio no puede ser negativo", exception.getMessage());
    }
}