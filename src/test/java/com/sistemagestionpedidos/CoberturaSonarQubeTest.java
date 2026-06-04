package com.sistemagestionpedidos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Suite de cobertura complementaria.
 * Cubre las ramas y métodos no alcanzados por el resto de tests del proyecto,
 * con el objetivo de superar minimo el 80% de cobertura en SonarQube.
 * No duplica ningún caso ya existente en otras clases de test, son todos tests adicionales.
 * Con esto hemos conseguido un porcentaje de cobertura de 87.8%.
 */
@DisplayName("Suite de Cobertura Complementaria")
class CoberturaSonarQubeTest {

    // CLIENTE
    @Test
    @DisplayName("Cliente: constructor lanza excepción con id negativo o cero")
    void clienteConstructorIdInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente(0, "Ana", 1, false, "España"),
                "El id 0 deberia ser rechazado");
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente(-1, "Ana", 1, false, "España"),
                "El id negativo deberia ser rechazado");
    }

    @Test
    @DisplayName("Cliente: constructor lanza excepción con años de antigüedad negativos")
    void clienteConstructorAnosNegativos() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente(1, "Ana", -1, false, "España"),
                "Los años negativos deberían ser rechazados");
    }

    @Test
    @DisplayName("Cliente: setId lanza excepción con id inválido")
    void clienteSetIdInvalido() {
        Cliente cliente = new Cliente(1, "Ana", 2, false, "España");
        assertThrows(IllegalArgumentException.class,
                () -> cliente.setId(0));
        assertThrows(IllegalArgumentException.class,
                () -> cliente.setId(-5));
    }

    @Test
    @DisplayName("Cliente: getters y setters básicos funcionan correctamente")
    void clienteGettersSetters() {
        Cliente cliente = new Cliente(1, "Ana", 2, false, "España");

        cliente.setNombre("María");
        assertEquals("María", cliente.getNombre());

        cliente.setAnosAntiguedad(7);
        assertEquals(7, cliente.getAnosAntiguedad());

        cliente.setEsVip(true);
        assertTrue(cliente.isEsVip());

        cliente.setPais("Francia");
        assertEquals("Francia", cliente.getPais());
    }

    @Test
    @DisplayName("Cliente: calcularDescuentoFidelidad solo por antigüedad >= 5 sin VIP")
    void clienteDescuentoSoloAntiguedad() {
        // Cubre la rama años>=5 sin VIP (descuento = 5%)
        Cliente cliente = new Cliente(1, "Ana", 5, false, "España");
        assertEquals(0.05, cliente.calcularDescuentoFidelidad(), 0.001);
    }

    @Test
    @DisplayName("Cliente: calcularDescuentoFidelidad VIP con antigüedad menor a 2 años")
    void clienteDescuentoVipSinAntiguedad() {
        // Cubre la rama VIP + años<2 (descuento = 10%)
        Cliente cliente = new Cliente(1, "Ana", 1, true, "España");
        assertEquals(0.10, cliente.calcularDescuentoFidelidad(), 0.001);
    }

    @Test
    @DisplayName("Cliente: calcularDescuentoFidelidad sin VIP ni antigüedad relevante")
    void clienteDescuentoCero() {
        // Cubre la rama sin VIP ni antigüedad suficiente (descuento = 0%)
        Cliente cliente = new Cliente(1, "Ana", 0, false, "España");
        assertEquals(0.0, cliente.calcularDescuentoFidelidad(), 0.001);
    }

    @Test
    @DisplayName("Cliente: calcularDescuentoFidelidad con antigüedad entre 2 y 4 años sin VIP")
    void clienteDescuentoAntiguedadMedia() {
        // Cubre la rama años>=2 && años<5 sin VIP (descuento = 2%)
        Cliente cliente = new Cliente(1, "Ana", 3, false, "España");
        assertEquals(0.02, cliente.calcularDescuentoFidelidad(), 0.001);
    }

    // PRODUCTO (clase base)
    @Test
    @DisplayName("Producto: constructor lanza excepción con id inválido")
    void productoConstructorIdInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Producto(0, "Nombre", 10.0));
        assertThrows(IllegalArgumentException.class,
                () -> new Producto(-1, "Nombre", 10.0));
    }

    @Test
    @DisplayName("Producto: constructor lanza excepción con nombre vacío o nulo")
    void productoConstructorNombreInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Producto(1, "", 10.0));
        assertThrows(IllegalArgumentException.class,
                () -> new Producto(1, "   ", 10.0));
        assertThrows(IllegalArgumentException.class,
                () -> new Producto(1, null, 10.0));
    }

    @Test
    @DisplayName("Producto: setId lanza excepción con id inválido")
    void productoSetIdInvalido() {
        Producto p = new Producto(1, "Test", 10.0);
        assertThrows(IllegalArgumentException.class, () -> p.setId(0));
        assertThrows(IllegalArgumentException.class, () -> p.setId(-3));
    }

    @Test
    @DisplayName("Producto: setNombre lanza excepción con nombre vacío o nulo")
    void productoSetNombreInvalido() {
        Producto p = new Producto(1, "Test", 10.0);
        assertThrows(IllegalArgumentException.class, () -> p.setNombre(""));
        assertThrows(IllegalArgumentException.class, () -> p.setNombre("  "));
        assertThrows(IllegalArgumentException.class, () -> p.setNombre(null));
    }

    @Test
    @DisplayName("Producto: setPrecioBase lanza excepción con precio negativo")
    void productoSetPrecioNegativo() {
        Producto p = new Producto(1, "Test", 10.0);
        assertThrows(IllegalArgumentException.class, () -> p.setPrecioBase(-1.0));
    }

    @Test
    @DisplayName("Producto: calcularPrecioFinal de la clase base devuelve el precio base")
    void productoBasePrecioFinal() {
        // Cubre calcularPrecioFinal() en Producto directamente (no subclase)
        Producto p = new Producto(1, "Generico", 25.0);
        assertEquals(25.0, p.calcularPrecioFinal(), 0.001);
    }

    @Test
    @DisplayName("Producto: toString incluye id, nombre y precio base")
    void productoToString() {
        Producto p = new Producto(1, "Teclado", 50.0);
        String texto = p.toString();
        assertTrue(texto.contains("1"));
        assertTrue(texto.contains("Teclado"));
        assertTrue(texto.contains("50.0"));
    }

    // PRODUCTO FÍSICO (hereda de producto)
    @Test
    @DisplayName("ProductoFisico: setPeso lanza excepción con peso negativo")
    void productoFisicoSetPesoNegativo() {
        ProductoFisico pf = new ProductoFisico(1, "Caja", 20.0, 2.0);
        assertThrows(IllegalArgumentException.class, () -> pf.setPeso(-1.0));
    }

    @Test
    @DisplayName("ProductoFisico: getPeso devuelve el valor correcto")
    void productoFisicoGetPeso() {
        ProductoFisico pf = new ProductoFisico(1, "Caja", 20.0, 3.5);
        assertEquals(3.5, pf.getPeso(), 0.001);
    }

    @Test
    @DisplayName("ProductoFisico: calcularCosteEnvio para España es solo el peso")
    void productoFisicoEnvioEspana() {
        // Tarifa España = 0€, coste = 0 + peso
        ProductoFisico pf = new ProductoFisico(1, "Libro", 15.0, 2.0);
        assertEquals(2.0, pf.calcularCosteEnvio("España"), 0.001);
    }

    @Test
    @DisplayName("ProductoFisico: calcularCosteEnvio para Francia, Italia y Portugal es 5 + peso")
    void productoFisicoEnvioZonaMedia() {
        // Tarifa zona media = 5€, coste = 5 + peso
        ProductoFisico pf = new ProductoFisico(1, "Libro", 15.0, 2.0);
        assertEquals(7.0, pf.calcularCosteEnvio("Francia"), 0.001);
        assertEquals(7.0, pf.calcularCosteEnvio("Italia"), 0.001);
        assertEquals(7.0, pf.calcularCosteEnvio("Portugal"), 0.001);
    }

    @Test
    @DisplayName("ProductoFisico: calcularCosteEnvio para el resto del mundo es 10 + peso")
    void productoFisicoEnvioInternacional() {
        // Tarifa internacional = 10€, coste = 10 + peso
        ProductoFisico pf = new ProductoFisico(1, "Libro", 15.0, 2.0);
        assertEquals(12.0, pf.calcularCosteEnvio("Alemania"), 0.001);
        assertEquals(12.0, pf.calcularCosteEnvio("México"), 0.001);
    }

    @Test
    @DisplayName("ProductoFisico: toString incluye el peso")
    void productoFisicoToString() {
        ProductoFisico pf = new ProductoFisico(1, "Teclado", 50.0, 5.4);
        String texto = pf.toString();
        assertTrue(texto.contains("Fisico"));
        assertTrue(texto.contains("5.4"));
    }

    // PRODUCTO DIGITAL (hereda de producto)
    @Test
    @DisplayName("ProductoDigital: setTamanoDescarga lanza excepción con valor negativo")
    void productoDigitalSetTamanoNegativo() {
        ProductoDigital pd = new ProductoDigital(1, "App", 10.0, 100.0);
        assertThrows(IllegalArgumentException.class, () -> pd.setTamanoDescarga(-1.0));
    }

    @Test
    @DisplayName("ProductoDigital: getTamanoDescarga devuelve el valor correcto")
    void productoDigitalGetTamano() {
        ProductoDigital pd = new ProductoDigital(1, "App", 10.0, 250.0);
        assertEquals(250.0, pd.getTamanoDescarga(), 0.001);
    }

    @Test
    @DisplayName("ProductoDigital: aplicarIVA lanza excepción con tipo inválido")
    void productoDigitalIvaInvalido() {
        // Cubre la rama default del switch de aplicarIVA
        ProductoDigital pd = new ProductoDigital(1, "App", 10.0, 100.0);
        assertThrows(IllegalArgumentException.class,
                () -> pd.aplicarIVA("INVALIDO"));
    }

    @Test
    @DisplayName("ProductoDigital: toString incluye tamaño de descarga")
    void productoDigitalToString() {
        ProductoDigital pd = new ProductoDigital(1, "Curso", 30.0, 500.0);
        String texto = pd.toString();
        assertTrue(texto.contains("Digital"));
        assertTrue(texto.contains("500.0"));
    }

    // PEDIDO
    @Test
    @DisplayName("Pedido: constructor lanza excepción con id inválido")
    void pedidoConstructorIdInvalido() {
        Cliente cliente = new Cliente(1, "Ana", 1, false, "España");
        assertThrows(IllegalArgumentException.class,
                () -> new Pedido(0, cliente));
        assertThrows(IllegalArgumentException.class,
                () -> new Pedido(-1, cliente));
    }

    @Test
    @DisplayName("Pedido: constructor lanza excepción con cliente nulo")
    void pedidoConstructorClienteNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Pedido(1, null));
    }

    @Test
    @DisplayName("Pedido: agregarProducto lanza excepción con producto nulo")
    void pedidoAgregarProductoNulo() {
        Cliente cliente = new Cliente(1, "Ana", 1, false, "España");
        Pedido pedido = new Pedido(1, cliente);
        assertThrows(IllegalArgumentException.class,
                () -> pedido.agregarProducto(null, 1));
    }

    @Test
    @DisplayName("Pedido: agregarProducto lanza excepción con cantidad inválida")
    void pedidoAgregarCantidadInvalida() {
        Cliente cliente = new Cliente(1, "Ana", 1, false, "España");
        Pedido pedido = new Pedido(1, cliente);
        Producto p = new Producto(1, "Test", 10.0);
        assertThrows(IllegalArgumentException.class,
                () -> pedido.agregarProducto(p, 0));
        assertThrows(IllegalArgumentException.class,
                () -> pedido.agregarProducto(p, -1));
    }

    @Test
    @DisplayName("Pedido: eliminarProducto reduce correctamente la lista")
    void pedidoEliminarProducto() {
        Cliente cliente = new Cliente(1, "Ana", 1, false, "España");
        Pedido pedido = new Pedido(1, cliente);
        Producto p = new Producto(1, "Test", 10.0);
        pedido.agregarProducto(p, 2);
        assertEquals(1, pedido.getProductos().size());
        pedido.eliminarProducto(p);
        assertEquals(0, pedido.getProductos().size());
    }

    @Test
    @DisplayName("Pedido: setters de id, cliente y productos funcionan correctamente")
    void pedidoSetters() {
        Cliente cliente1 = new Cliente(1, "Ana", 1, false, "España");
        Cliente cliente2 = new Cliente(2, "Luis", 3, true, "Francia");
        Pedido pedido = new Pedido(1, cliente1);

        pedido.setIdPedido(5);
        assertEquals(5, pedido.getIdPedido());

        pedido.setCliente(cliente2);
        assertEquals(cliente2, pedido.getCliente());

        List<Producto> lista = new ArrayList<>();
        lista.add(new Producto(1, "Pack", 10.0));
        pedido.setProductos(lista);
        assertEquals(1, pedido.getProductos().size());
    }

    @Test
    @DisplayName("Pedido: setCantidades actualiza el mapa correctamente")
    void pedidoSetCantidades() {
        Cliente cliente = new Cliente(1, "Ana", 1, false, "España");
        Pedido pedido = new Pedido(1, cliente);
        Producto p = new Producto(1, "Test", 10.0);
        pedido.agregarProducto(p, 1);

        Map<Producto, Integer> nuevasCantidades = new HashMap<>();
        nuevasCantidades.put(p, 3);
        pedido.setCantidades(nuevasCantidades);

        assertEquals(3, pedido.getCantidades().get(p.getId()));
    }

    @Test
    @DisplayName("Pedido: mostrarResumen incluye los datos principales del pedido")
    void pedidoMostrarResumen() {
        Cliente cliente = new Cliente(1, "Ana", 1, false, "España");
        Pedido pedido = new Pedido(1, cliente);
        pedido.agregarProducto(new Producto(1, "Cuaderno", 5.0), 2);
        String resumen = pedido.mostrarResumen();
        assertTrue(resumen.contains("Ana"));
        assertTrue(resumen.contains("España"));
        assertTrue(resumen.contains("Cuaderno"));
        assertTrue(resumen.contains("10.0"));
    }

    @Test
    @DisplayName("Pedido: constructor con colecciones lanza excepción si productos o cantidades son nulos")
    void pedidoConstructorColeccionesNulas() {
        Cliente cliente = new Cliente(1, "Ana", 1, false, "España");
        assertThrows(IllegalArgumentException.class,
                () -> new Pedido(1, cliente, null, new HashMap<>()));
        assertThrows(IllegalArgumentException.class,
                () -> new Pedido(1, cliente, new ArrayList<>(), null));
    }

    // FACTURA
    @Test
    @DisplayName("Factura: constructor principal lanza excepción con total final negativo")
    void facturaConstructorTotalNegativo() {
        // Cubre la validación del constructor principal de Factura
        assertThrows(IllegalArgumentException.class,
                () -> new Factura(100.0, 21.0, 5.0, 0.0, -1.0));
    }

    @Test
    @DisplayName("Factura: constructor principal con valores válidos crea la factura correctamente")
    void facturaConstructorValido() {
        assertDoesNotThrow(() -> {
            Factura f = new Factura(100.0, 21.0, 5.0, 3.0, 123.0);
            assertNotNull(f.getCodigoFactura());
            assertNotNull(f.getFechaEmision());
            assertEquals(100.0, f.getTotalNeto());
            assertEquals(21.0, f.getTotalIva());
            assertEquals(5.0, f.getTotalEnvio());
            assertEquals(3.0, f.getDescuento());
            assertEquals(123.0, f.getTotalFinal());
        });
    }

    @Test
    @DisplayName("Factura: el código autogenerado empieza por FACT- y no es nulo")
    void facturaCodigoAutogenerado() {
        Factura f = new Factura(50.0, 10.5, 0.0, 0.0, 60.5);
        assertNotNull(f.getCodigoFactura());
        assertTrue(f.getCodigoFactura().startsWith("FACT-"),
                "El codigo de factura deberia empezar por FACT-");
    }

    // TIENDA
    @Test
    @DisplayName("Tienda: realizarVenta lanza excepción si cliente es nulo")
    void tiendaClienteNulo() {
        Tienda tienda = new Tienda();
        Cliente cliente = new Cliente(1, "Ana", 1, false, "España");
        Pedido pedido = new Pedido(1, cliente);
        pedido.agregarProducto(new Producto(1, "Test", 10.0), 1);
        assertThrows(IllegalArgumentException.class,
                () -> tienda.realizarVenta(null, pedido));
    }

    @Test
    @DisplayName("Tienda: realizarVenta lanza excepción si pedido es nulo")
    void tiendaPedidoNulo() {
        Tienda tienda = new Tienda();
        Cliente cliente = new Cliente(1, "Ana", 1, false, "España");
        assertThrows(IllegalArgumentException.class,
                () -> tienda.realizarVenta(cliente, null));
    }

    @Test
    @DisplayName("Tienda: venta con producto físico en zona internacional calcula envío correctamente")
    void tiendaVentaEnvioInternacional() {
        // Cubre la rama de calcularEnvio para país fuera de España/Francia/Italia/Portugal
        Tienda tienda = new Tienda();
        Cliente cliente = new Cliente(1, "Ana", 0, false, "Alemania");
        Pedido pedido = new Pedido(1, cliente);
        pedido.agregarProducto(new ProductoFisico(1, "Libro", 20.0, 1.0), 1);
        Factura factura = tienda.realizarVenta(cliente, pedido);
        assertEquals(11.0, factura.getTotalEnvio(), 0.001,
                "Envio a Alemania deberia ser 10 (zona) + 1 (peso) = 11");
    }

    @Test
    @DisplayName("Tienda: venta mixta aplica IVA solo a digitales y envío solo a físicos")
    void tiendaVentaMixta() {
        // E2E con valores concretos verificables
        Tienda tienda = new Tienda();
        Cliente cliente = new Cliente(1, "Ana", 0, false, "España");
        Pedido pedido = new Pedido(1, cliente);
        // Físico: precioBase=50, peso=2 → envío en España = 0+2 = 2€, calcularPrecioFinal=50
        // Digital: precioBase=10 → IVA general = 10*0.21 = 2.1€, calcularPrecioFinal=12.1
        pedido.agregarProducto(new ProductoFisico(1, "Teclado", 50.0, 2.0), 1);
        pedido.agregarProducto(new ProductoDigital(2, "App", 10.0, 100.0), 1);
        Factura factura = tienda.realizarVenta(cliente, pedido);
        // totalNeto = 50 + 12.1 = 62.1
        assertEquals(62.1, factura.getTotalNeto(), 0.01);
        // totalIva = 2.1
        assertEquals(2.1, factura.getTotalIva(), 0.01);
        // totalEnvio = 2.0
        assertEquals(2.0, factura.getTotalEnvio(), 0.01);
        // sin descuento (0 años, no VIP) → totalFinal = 62.1 + 2.1 + 2.0 = 66.2
        assertEquals(66.2, factura.getTotalFinal(), 0.01);
        assertFalse(factura.getCodigoFactura().isEmpty());
    }
}
