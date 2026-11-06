package ar.edu.unlam.sistemaDeGestionDeCine;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

import org.junit.Test;

public class TestCine {

//1	
	@Test
	public void queSePuedaCrearUnaFuncion() {

		Pelicula pelicula = new Pelicula("Venom");

		Funcion funcion = new Funcion(pelicula, LocalDate.of(2026, 11, 18), LocalTime.of(18, 30), 5);

		assertNotNull(funcion.getPelicula());
		assertNotNull(funcion.getFecha());
		assertNotNull(funcion.getHora());
	}

//15
	@Test
	public void queLosAsientosSeOrdenenCorrectamente() {

		TreeSet<Asiento> asientos = new TreeSet<>();

		asientos.add(new Asiento(5, 1));
		asientos.add(new Asiento(2, 1));
		asientos.add(new Asiento(8, 1));

		Asiento primero = asientos.first();

		assertEquals(Integer.valueOf(2), primero.getFila());
	}

//16	
	@Test
	public void queNoSePuedaAgregarDosVecesLaMismaButaca() {

		TreeSet<Asiento> asientos = new TreeSet<>();

		asientos.add(new Asiento(1, 1));
		asientos.add(new Asiento(1, 1));

		assertEquals(1, asientos.size());
	}

//19	
	@Test(expected = AsientoYaVendidoException.class)
	public void queNoSePuedaVenderUnAsientoOcupado() throws AsientoYaVendidoException {

		Asiento asiento = new Asiento(1, 1);

		asiento.ocupar();

		if (asiento.getOcupado()) {
			throw new AsientoYaVendidoException("El asiento ya está ocupado");
		}
	}

//20
	@Test(expected = FuncionAgotadaException.class)
	public void queNoSePuedanVenderMasEntradasQueLaCapacidadDeLaFuncion() throws FuncionAgotadaException {

		Pelicula pelicula = new Pelicula("Avengers");

		Funcion funcion = new Funcion(pelicula, LocalDate.of(2026, 11, 18), LocalTime.of(18, 30), 3);

		funcion.venderEntrada();
		funcion.venderEntrada();
		funcion.venderEntrada();
		//4º entrada que NO me va a dejar vender porque capacidadMaxima = 3
		funcion.venderEntrada();
	}

}
