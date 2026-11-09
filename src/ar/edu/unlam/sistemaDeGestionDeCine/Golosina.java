package ar.edu.unlam.sistemaDeGestionDeCine;

public class Golosina extends ProductoCandyBar {

	public Golosina(String idProducto, String nombreProducto, Double precio, Integer stock) {
		super(idProducto, nombreProducto, precio, stock);
	}

	@Override
	public Double calcularPrecioVenta() {
		return getPrecio();
	}

}
