package ar.edu.unlam.sistemaDeGestionDeCine;


import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;


public class Cine {
	
	private String nombre;
    private Map<Integer, Entrada> boleteria; 
    private List<Vendible> carrito;
    private List<ProductoCandyBar> productos;

    public Cine(String nombre) {
        this.nombre = nombre;
        this.boleteria = new HashMap<>();
        this.productos = new ArrayList<>();
        this.carrito = new ArrayList<>();
    }
    
    public void registrarEntrada(Entrada entrada) {
        this.boleteria.put(entrada.getCodigo(), entrada);
    }

    // Test 17: Búsqueda directa O(1) por código Integer dentro del Map
    public Entrada buscarTicket(Integer codigo) {
        if (!this.boleteria.containsKey(codigo)) {
            // Test 18: Lanza la excepción si el código no existe
            throw new TicketInexistenteException("El ticket con código " + codigo + " no existe en el sistema.");
        }
        return this.boleteria.get(codigo);
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
    
    public void venderProducto(ProductoCandyBar producto) throws StockInsuficienteException {
		producto.descontarStock(1);
	}

	public List<Vendible> getCarrito() {
		return this.carrito;
	}
	
	
	
	

	public void agregarAlCarrito(Vendible vendible) {
		this.carrito.add(vendible);
		
	}

	public void sacarDelCarrito(Vendible vendible) {
		this.carrito.remove(vendible);
		
	}

	public double calcularTotalCarrito() {
		double total = 0.0;
	    for (Vendible producto : this.carrito) {
	        total += producto.calcularPrecioVenta();
	    }
	    return total;
	}
}

