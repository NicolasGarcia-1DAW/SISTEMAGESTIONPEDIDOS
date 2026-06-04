# Proyecto de Gestión de Pedidos

## Descripción

Proyecto desarrollado en Java que implementa un sistema de gestión para una pequeña empresa comercial que gestiona clientes, productos, pedidos, ventas y facturación.

La aplicación permite registrar clientes, añadir productos físicos y digitales a pedidos, calcular impuestos, gastos de envío y descuentos por fidelidad, así como generar una factura final con el desglose completo de la compra.

Durante la evolución del proyecto se han incorporado pruebas unitarias, pruebas de integración, pruebas de sistema (E2E) y pruebas de robustez utilizando JUnit, además de aplicar criterios de calidad de código y control de versiones mediante Git.

### Características

- **Cliente:** Almacena información del cliente como nombre, país, años de antigüedad y estado VIP, que determinan el descuento de fidelidad aplicado en cada venta.
- **Producto:** Clase base con atributos comunes a todos los productos (id, nombre y precio base). Lanza excepción si el precio es negativo.
- **Producto Físico:** Hereda de `Producto` y calcula el coste de envío según el peso del producto y el país de destino del cliente.
- **Producto Digital:** Hereda de `Producto` y aplica IVA (GENERAL 21%, REDUCIDO 10% o SUPER 4%) sobre el precio base. Incluye el tamaño de descarga pero no genera gastos de envío.
- **Pedido:** Contenedor de la intención de compra. Permite agregar y eliminar productos dinámicamente, calcular el total con IVA aplicado, y lanza excepción si se intenta calcular sin productos.
- **Tienda:** Componente orquestador central. Coordina cliente, pedido y factura, aplicando descuentos de fidelidad y generando el documento de salida.
- **Factura:** Documento generado automáticamente al finalizar una venta, con desglose de total neto, IVA, gastos de envío, descuento aplicado y total final.
---

## Funcionalidades principales

### Gestión de Clientes
- Registro de clientes con identificador único.
- Gestión de años de antigüedad.
- Gestión de clientes VIP.
- Asociación de país para cálculo de envíos.
- Cálculo automático de descuentos de fidelidad.

### Gestión de Productos
- Clase abstracta `Producto` como base común.
- Productos físicos (`ProductoFisico`) con cálculo de costes de envío según país.
- Productos digitales (`ProductoDigital`) con aplicación de distintos tipos de IVA:
  - GENERAL (21%)
  - REDUCIDO (10%)
  - SUPER (4%)

### Gestión de Pedidos
- Asociación de pedidos a un cliente.
- Incorporación dinámica de productos.
- Gestión de cantidades por producto.
- Cálculo automático del total del pedido.
- Cálculo de IVA de productos digitales.
- Cálculo de gastos de envío para productos físicos.
- Generación de resumen completo del pedido.

### Gestión de Ventas
- Clase `Tienda` encargada de coordinar todo el proceso de compra.
- Aplicación de descuentos por fidelidad.
- Validación de datos antes de procesar la venta.
- Generación automática de facturas.

### Facturación
- Generación automática de código de factura.
- Registro de fecha de emisión.
- Desglose de:
  - Total neto
  - IVA
  - Gastos de envío
  - Descuento aplicado
  - Total final
---
## Reglas de Negocio

### Coste de envío (`ProductoFisico`)
El coste de envío se calcula sumando la tarifa de zona más el peso del producto en kg:

| Destino | Tarifa base |
|---|---|
| España | 0 € |
| Francia, Italia, Portugal | 5 € |
| Resto del mundo | 10 € |

> Fórmula: `costeEnvio = tarifaZona + peso`

### IVA (`ProductoDigital`)

| Tipo | Porcentaje |
|---|---|
| GENERAL | 21% |
| REDUCIDO | 10% |
| SUPER | 4% |

### Descuento de fidelidad (`Cliente`)

| Condición | Descuento |
|---|---|
| Cliente VIP | +10% |
| Antigüedad ≥ 5 años | +5% |
| Antigüedad ≥ 2 años | +2% |

Los descuentos son acumulables. Se aplican sobre el subtotal (neto + IVA + envío).

---

## Estructura de Clases

- **Cliente:** Representa al cliente con id, nombre, país, años de antigüedad y estado VIP.
- **Producto:** Clase base para los productos, con id, nombre y precio base validado.
- **ProductoFisico:** Hereda de `Producto`, añade el atributo peso y calcula el coste de envío por zona.
- **ProductoDigital:** Hereda de `Producto`, añade el tamaño de descarga y aplica IVA configurable.
- **Pedido:** Representa la compra de un cliente, contiene una colección de productos con sus cantidades y calcula totales parciales.
- **Tienda:** Orquesta el flujo completo de venta y genera la factura final.
- **Factura:** Documento de salida con desglose completo de la transacción y código autogenerado.

---

## Estructura del Proyecto

```
src/
├── main/java/com/sistemagestionpedidos/
│   ├── Main.java
│   ├── Cliente.java
│   ├── Producto.java
│   ├── ProductoFisico.java
│   ├── ProductoDigital.java
│   ├── Pedido.java
│   ├── Tienda.java
│   ├── Factura.java
│   └── excluidosonar/          ← clases de apoyo externas, excluidas del análisis
│       ├── CalculadoraFinanciera.java
│       ├── ServicioFacturacion.java
│       ├── GestorInventario.java
│       └── README.md
└── test/java/com/sistemagestionpedidos/
    ├── MasterGroupTest.java     ← prueba maestra 
    ├── PedidoTest.java
    ├── PedidoExceptionTest.java
    ├── PedidoProductoIntegrationTest.java
    ├── ProductoFisicoTest.java
    ├── ProductoFisicoExceptionTest.java
    ├── ProductoDigitalTest.java
    ├── ProductoDigitalExceptionTest.java
    ├── ProductoExceptionTest.java
    ├── TiendaIntegrationTest.java
    ├── SistemaE2ETest.java
    └── RobustezTest.java
```

## Tecnologías utilizadas

- Java
- Maven
- JUnit 5/6
- Git
- GitHub
- SonarQube
---
## Pruebas implementadas

### Pruebas Unitarias
- Cálculo de IVA en productos digitales.
- Cálculo de precio final de productos físicos.
- Cálculo de costes de envío.
- Validación de descuentos.

### Pruebas Parametrizadas
- Verificación del cálculo de pedidos utilizando múltiples combinaciones de productos y precios.

### Pruebas de Robustez
- Cliente con nombre vacío.
- Pedido sin productos.
- Validaciones de datos incorrectos.

### Pruebas de Integración
- Comunicación entre `Pedido`, `Cliente`, `Tienda` y `Factura`.
- Validación del flujo de venta completo.

### Pruebas de Sistema (E2E)
- Simulación completa desde la creación del pedido hasta la generación de la factura.
---
## Instrucciones de Ejecución

### Requisitos previos
- Tener instalado [Java 17+](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/) para gestión de dependencias
- Un IDE como [IntelliJ IDEA](https://www.jetbrains.com/idea/) o [Visual Studio Code](https://code.visualstudio.com/)

### Compilar y ejecutar
Abre la carpeta del proyecto, accede a `src/main/java/com/sistemagestionpedidos/Main.java` y selecciona **Run** o **Execute Java** en tu IDE.

También puedes ejecutarlo desde consola con Maven:
```bash
mvn compile exec:java -Dexec.mainClass="com.sistemagestionpedidos.Main"
```

### Ejecutar los tests
```bash
mvn test
```

### Ejecutar el análisis de SonarQube
```bash
mvn sonar:sonar
```

---

## Ejemplo de Salida

```
Resumen del Pedido:
Id Pedido: 1
Cliente: Juan Rodriguez
Pais: España
Productos:
Fisico --> Producto [id=1, nombre=Teclado, precioBase=51.25], Peso: 5.4 kg | Cantidad: 1
Fisico --> Producto [id=3, nombre=Raton, precioBase=24.3], Peso: 3.28 kg | Cantidad: 1
Digital --> Producto [id=2, nombre=Curso, precioBase=58.5], Tamaño de Descarga: 5468.42 MB | Cantidad: 1
Digital --> Producto [id=4, nombre=Software, precioBase=32.4], Tamaño de Descarga: 281.52 MB | Cantidad: 1
Total: 182.54 euros
```

## Control de versiones

El desarrollo se ha realizado utilizando Git y GitHub, manteniendo un historial de commits que refleja la evolución del proyecto desde la implementación inicial hasta la incorporación de pruebas automatizadas, integración completa y estabilización final del sistema.

## Autor

Proyecto académico desarrollado por Nicolás García para la asignatura de Entornos de Desarrollo.
