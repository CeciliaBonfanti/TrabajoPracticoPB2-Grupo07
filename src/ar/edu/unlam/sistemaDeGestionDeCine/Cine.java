package ar.edu.unlam.sistemaDeGestionDeCine;

import java.util.HashMap;
import java.util.Map;

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
    
}
