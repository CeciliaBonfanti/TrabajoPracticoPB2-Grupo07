package ar.edu.unlam.sistemaDeGestionDeCine;

public abstract class ProductoCandyBar implements Vendible{
	String idProducto;
	String nombreProducto;
	Double precio;
	Integer stock;
	
	public ProductoCandyBar(String idProducto, String nombreProducto, Double precio, Integer stock) {
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.precio = precio;
		this.stock = stock;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}
}
