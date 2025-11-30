# Proyecto de Gestión de Pedidos

## Descripción

Este es un proyecto en Java que simula la gestión de un pedido realizado por un cliente. El sistema permite agregar productos al pedido, calcular el precio final de los productos (tanto físicos como digitales), y mostrar un resumen detallado del pedido.

### Características:
- **Cliente:** Almacena información del cliente como nombre, correo y dirección.
- **Producto:** Define la clase base `Producto` con atributos comunes a productos físicos y digitales (nombre y precio).
- **Producto Físico:** Hereda de `Producto` y agrega un costo de envío adicional.
- **Producto Digital:** Hereda de `Producto` y maneja el tamaño de descarga, sin coste de envío adicional.
- **Pedido:** Permite agregar productos, calcular el total del pedido y mostrar un resumen con la información del cliente y los productos.

## Estructura de Clases

- **Cliente:** Representa a un cliente con su nombre, correo y dirección.
- **Producto:** Clase base para los productos, tiene un nombre y un precio.
- **ProductoFisico:** Hereda de `Producto`, añade el coste de envío al precio.
- **ProductoDigital:** Hereda de `Producto`, incluye el tamaño de descarga pero no tiene coste de envío.
- **Pedido:** Representa el pedido de un cliente y contiene una lista de productos. Calcula el total y muestra un resumen.

## Instrucciones de Ejecución

1. **Requisitos previos:**
   - Tener instalado [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) en tu máquina. 
   - Un IDE como [IntelliJ IDEA](https://www.jetbrains.com/idea/) o [Visual Studio Code (VSCode)](https://code.visualstudio.com/).

2. **Compilar y Ejecutar:**
   Abre la carpeta del proyecto, accede a src, luego Main.java, y selecciona Ejecutar o Run Java.

3. **Salida esperada:**
   El programa generará un resumen del pedido en la consola, mostrando el nombre y correo del cliente, los productos agregados, sus detalles (nombre, precio, tipo, etc.) y el total del pedido.

## Ejemplo de Salida

Resumen del Pedido:
Cliente: Juan Pérez
Correo: juan.perez@email.com
Dirección: Calle Pablo Picasso n14
Productos: 
Fisico --> Producto: Teclado, Precio: 51.25, Coste de Envío: 5.4
Fisico --> Producto: Raton, Precio: 24.3, Coste de Envío: 3.28
Digital --> Producto: Videojuego, Precio: 58.5, Tamaño de Descarga: 5468.42MB
Digital --> Producto: Software, Precio: 32.4, Tamaño de Descarga: 281.52MB
Total: 175.13 euros
