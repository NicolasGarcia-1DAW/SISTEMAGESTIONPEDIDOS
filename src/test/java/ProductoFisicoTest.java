import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoFisicoTest {

    @Test
    void calcularPrecioFinalProductoFisico() {

        ProductoFisico producto = new ProductoFisico("Teclado", 50.0, 5.0);

        double resultado = producto.calcularPrecioFinal();

        assertEquals(55.0, resultado);
        assertTrue(resultado > 50.0);
        assertNotEquals(50.0, resultado);
    }
}