import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tests de Integración: Flujo de Pedidos y Productos")
class GestionPedidosIntegrationTest {

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        // Inicializamos el escenario: Un pedido vacío antes de cada test
        pedido = new Pedido();
    }

    @Test
    @DisplayName("Integración: Cálculo de total con múltiples tipos de productos")
    void deberiaCalcularTotalConProductosMixtos() {
        // 1. Arrange (Preparar)
        // Creamos productos reales para ver cómo interactúan con el pedido
        Producto libro = new ProductoFisico("Libro Java", 30.0, 1.5); // 30 + (1.5 * 2) = 33.0
        Producto curso = new ProductoDigital("Curso Online", 50.0, "C-202"); // 50.0 + (50*0.21) = 60.5

        // 2. Act (Actuar)
        // Aquí ocurre la integración: Pedido recibe y almacena objetos Producto
        pedido.agregarProducto(libro);
        pedido.agregarProducto(curso);
        double totalObtenido = pedido.calcularTotalPedido();

        // 3. Assert (Verificar)
        // El total esperado es la suma de los cálculos internos de cada producto
        double totalEsperado = 33.0 + 60.5;
        
        assertEquals(totalEsperado, totalObtenido, "El pedido no integró correctamente los precios finales de los productos");
    }

    @Test
    @DisplayName("Integración: Pedido vacío y persistencia de estado")
    void deberiaDarCeroSiNoHayProductos() {
        assertEquals(0.0, pedido.calcularTotalPedido(), 
            "Un pedido recién creado debería tener un total de 0.0");
    }

    @Test
    @DisplayName("Integración: Robustez ante productos nulos")
    void noDeberiaFallarSiSeAgregaProductoNulo() {
        // Test de integración para asegurar que el sistema no "explota" (NullPointerException)
        // al interactuar con datos erróneos.
        assertDoesNotThrow(() -> {
            pedido.agregarProducto(null);
            pedido.calcularTotalPedido();
        }, "El sistema debería filtrar productos nulos en la integración");
    }
}