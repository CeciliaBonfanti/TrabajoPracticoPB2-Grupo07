package ar.edu.unlam.sistemaDeGestionDeCine;

public class Bebida extends ProductoCandyBar {

	public Bebida(String idProducto, String nombreProducto, Double precio, Integer stock) {
		super(idProducto, nombreProducto, precio, stock);
	}

	@Override
	public Double calcularPrecioVenta() {
		return getPrecio();	
		}
}
