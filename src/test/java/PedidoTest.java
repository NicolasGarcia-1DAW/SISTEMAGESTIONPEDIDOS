import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    @ParameterizedTest
    @CsvSource({
            "50,5,30,85",
            "20,3,10,33",
            "100,10,50,160"
    })
    void calcularTotalPedido(double precioProducto1, double envio, double precioProducto2, double totalEsperado) {

        Cliente cliente = new Cliente("Juan", "juan@email.com", "Calle 1");

        Pedido pedido = new Pedido(cliente);

        ProductoFisico p1 = new ProductoFisico("Producto1", precioProducto1, envio);
        ProductoDigital p2 = new ProductoDigital("Producto2", precioProducto2, 200);

        pedido.agregarProducto(p1);
        pedido.agregarProducto(p2);

        double total = pedido.calcularTotal();

        assertEquals(totalEsperado, total);
    }
}