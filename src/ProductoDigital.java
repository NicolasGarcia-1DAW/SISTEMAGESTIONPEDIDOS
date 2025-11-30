

public class ProductoDigital extends Producto {
    private double tamanoDescarga;

    public ProductoDigital(String nombre, double precio, double tamanoDescarga) {
        super(nombre, precio);
        this.tamanoDescarga = tamanoDescarga;
    }

    public double getTamanoDescarga() {
        return tamanoDescarga;
    }

    public void setTamanoDescarga(double tamanoDescarga) {
        this.tamanoDescarga = tamanoDescarga;
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecio();
    }

    @Override
    public String toString() {
        return "Digital --> " + super.toString() + ", Tamaño de Descarga: " + tamanoDescarga + "MB";
    }
}
