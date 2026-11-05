package ar.edu.unlam.sistemaDeGestionDeCine;

import java.util.ArrayList;
import java.util.List;

public class Cine {
    private List<ProductoCandyBar> productos;

    public Cine() {
        this.productos = new ArrayList<>();
    }

    public Boolean registrarProducto(ProductoCandyBar producto) {
        return this.productos.add(producto);
    }

    public Double obtenerPrecioProducto(ProductoCandyBar producto) {
        return producto.calcularPrecioVenta();
    }
    
    public Integer obtenerStockProducto(ProductoCandyBar producto) {
        return producto.getStock();
    }
}