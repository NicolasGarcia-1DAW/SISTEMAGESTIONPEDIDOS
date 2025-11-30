import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Producto> productos;
    private Cliente cliente;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.calcularPrecioFinal();
        }
        return total;
    }

    public String mostrarResumen() {
        String resumen = "Resumen del Pedido:\n";
        resumen += "Cliente: " + cliente.getNombre() + "\n";
        resumen += "Correo: " + cliente.getCorreo() + "\n";
        resumen += "Dirección: " + cliente.getDireccion() + "\n";
        resumen += "Productos: \n";

        for (Producto producto : productos) {
            resumen += producto.toString() + "\n";
        }

        resumen += "Total: " + calcularTotal() + " euros\n";
        return resumen;
    }
}
