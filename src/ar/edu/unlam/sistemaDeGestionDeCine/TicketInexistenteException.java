package ar.edu.unlam.sistemaDeGestionDeCine;

public class TicketInexistenteException extends RuntimeException{
	public TicketInexistenteException(String mensaje) {
        super(mensaje);
    }
}
