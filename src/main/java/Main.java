package main.java;

public class Main {
    public static void main(String[] args) {

        Producto p1 = new ProductoFisico("Teclado", 51.25, 5.4);
        Producto p2 = new ProductoFisico("Raton", 24.3, 3.28);
        Producto p3 = new ProductoDigital("Videojuego", 58.5, 5468.42);
        Producto p4 = new ProductoDigital("Software", 32.4, 281.52);

        Cliente cliente = new Cliente("Juan Pérez", "juan.perez@email.com", "Calle Pablo Picasso n14");

        Pedido pedido = new Pedido(cliente);
        pedido.agregarProducto(p1);
        pedido.agregarProducto(p2);
        pedido.agregarProducto(p3);
        pedido.agregarProducto(p4);

        System.out.println(pedido.mostrarResumen());
    }
}
