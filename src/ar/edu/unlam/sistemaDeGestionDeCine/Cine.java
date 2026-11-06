package ar.edu.unlam.sistemaDeGestionDeCine;


import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;


public class Cine {
	
	private String nombre;
    private Map<Integer, Entrada> boleteria; // Map exigido por la cátedra

    public Cine(String nombre) {
        this.nombre = nombre;
        this.boleteria = new HashMap<>();
    }
    
 // Método para registrar/vender la entrada y guardarla en el Map
    public void registrarEntrada(Entrada entrada) {
        this.boleteria.put(entrada.getCodigo(), entrada);
    }

    // Test 17: Búsqueda directa O(1) por código Integer dentro del Map
    public Entrada buscarTicket(Integer codigo) {
        if (!this.boleteria.containsKey(codigo)) {
            // Test 18: Lanza la excepción si el código no existe[cite: 18]
            throw new TicketInexistenteException("El ticket con código " + codigo + " no existe en el sistema.");
        }
        return this.boleteria.get(codigo);
    }
    


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
    
    public void venderProducto(ProductoCandyBar producto) throws StockInsuficienteException {
		producto.descontarStock(1);
	}
}

