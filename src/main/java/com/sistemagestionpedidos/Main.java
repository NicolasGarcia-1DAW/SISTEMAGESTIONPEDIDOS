package com.sistemagestionpedidos;

public class Main {
    public static void main(String[] args) {

        Producto p1 = new ProductoFisico("1", "Teclado", 51.25, 5.4);
        Producto p2 = new ProductoFisico("3", "Raton", 24.3, 3.28);
        Producto p3 = new ProductoDigital("2", "Curso", 58.5, 5468.42);
        Producto p4 = new ProductoDigital("4", "Software", 32.4, 281.52);

        Cliente cliente = new Cliente("JR-1", "Juan Rodriguez", 3, true, "España");

        Pedido pedido = new Pedido("P1|JR-1", cliente);
        pedido.agregarProducto(p1);
        pedido.agregarProducto(p2);
        pedido.agregarProducto(p3);
        pedido.agregarProducto(p4);

        System.out.println(pedido.mostrarResumen());
    }
}
