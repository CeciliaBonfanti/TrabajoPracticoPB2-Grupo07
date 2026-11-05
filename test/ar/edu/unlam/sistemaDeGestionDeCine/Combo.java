package ar.edu.unlam.sistemaDeGestionDeCine;

import java.util.ArrayList;
import java.util.List;

public class Combo extends ProductoCandyBar {
	private List<ProductoCandyBar> productos;
	private Double porcentajeDescuento;

	public Combo(String idProducto, String nombreProducto, Double porcentajeDescuento) {
		// Pasamos precio 0.0 y stock 0 al super ya que el precio se calcula en calcularPrecioVenta() y el stock depende de los productos elegidos
		super(idProducto, nombreProducto, 0.0, 0);
		this.porcentajeDescuento = porcentajeDescuento;
		this.productos = new ArrayList<>();
	}

	public void agregarProducto(ProductoCandyBar producto) {
		this.productos.add(producto);
	}

	@Override
	public Double calcularPrecioVenta() {
		Double sumaPrecios = 0.0;
		for (ProductoCandyBar producto : productos) {
			sumaPrecios += producto.calcularPrecioVenta();
		}
		return sumaPrecios * (1 - (porcentajeDescuento / 100.0));
	}

	public List<ProductoCandyBar> getProductos() {
		return productos;
	}

	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
}