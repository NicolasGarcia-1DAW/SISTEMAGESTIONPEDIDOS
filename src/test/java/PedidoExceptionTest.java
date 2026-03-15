import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoExceptionTest {

    
    @Test
    void calcularTotalPedidoSinProductos() {

        Cliente cliente = new Cliente("Juan", "juan@email.com", "Calle 1");

        Pedido pedido = new Pedido(cliente);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            pedido.calcularTotal();
        });

        assertEquals("El pedido no tiene productos", exception.getMessage());
    }
}