package ar.edu.unlam.sistemaDeGestionDeCine;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

public class Funcion {

	private Pelicula pelicula;
	private LocalDate fecha;
	private LocalTime hora;
	private Integer entradasVendidas;
	private Integer capacidadMaxima;

	private TreeSet<Asiento> sala;

	public Funcion(Pelicula pelicula, LocalDate fecha, LocalTime hora, Integer capacidadMaxima) {

		this.pelicula = pelicula;
		this.fecha = fecha;
		this.hora = hora;
		this.capacidadMaxima = capacidadMaxima;
		this.entradasVendidas = 0;

		this.sala = new TreeSet<>();
	}

	public Pelicula getPelicula() {
		return this.pelicula;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}

	public LocalTime getHora() {
		return this.hora;
	}

	public TreeSet<Asiento> getSala() {
		return this.sala;
	}
	
	public void agregarAsiento(Asiento asiento) {
	    this.sala.add(asiento);
	}

	public void venderEntrada() throws FuncionAgotadaException {

		if (this.entradasVendidas >= this.capacidadMaxima) {

			throw new FuncionAgotadaException("La función está agotada");
		}

		this.entradasVendidas++;
	}
}
