package ar.edu.unlam.sistemaDeGestionDeCine;

import static org.junit.Assert.*;

//test a realizar 2,3,4,5,17,18

import org.junit.Test;

public class TestCine {

	@Test
	public void deberiaCrearEntradaAsignandoFuncionYAsientoEIncrementandoIdAutoincremental() {
		Pelicula pelicula = new Pelicula("Avengers");
		LocalDate fecha = LocalDate.now();
		LocalTime hora = LocalTime.of(18, 0);
		Funcion funcion = new Funcion(pelicula, fecha, hora, 100);

		Asiento asiento1 = new Asiento(1, 1);
		Asiento asiento2 = new Asiento(1, 2);

		Entrada entrada1 = new EntradaEstandar(funcion, asiento1, 1500.0);
		Entrada entrada2 = new EntradaEstandar(funcion, asiento2, 1500.0);

		assertNotNull("La entrada 1 no debe ser nula", entrada1);
		assertEquals("La función debe coincidir", funcion, entrada1.getFuncion());
		assertEquals("El asiento debe coincidir", asiento1, entrada1.getAsiento());

		assertEquals("El código de la primera entrada debe ser 1", Integer.valueOf(1), entrada1.getCodigo());
		assertEquals("El código de la segunda entrada debe ser 2", Integer.valueOf(2), entrada2.getCodigo());
	}

	@Test
	public void deberiaCalcularPrecioBaseDeEntradaEstandarCorrectamente() {
		Pelicula pelicula = new Pelicula("Batman");
		Funcion funcion = new Funcion(pelicula, LocalDate.now(), LocalTime.of(20, 0), 120);
		Asiento asiento = new Asiento(2, 5);

		Entrada entradaEstandar = new EntradaEstandar(funcion, asiento, 2000.0);

		assertEquals("El precio de venta debe ser el precio base", Double.valueOf(2000.0),
				entradaEstandar.calcularPrecioVenta());
	}

	@Test
	public void deberiaCalcularPrecioDeEntradaConRecargoAplicandoAumento() {
		Pelicula pelicula = new Pelicula("Avatar");
		Funcion funcion = new Funcion(pelicula, LocalDate.now(), LocalTime.of(21, 0), 100);
		Asiento asiento = new Asiento(1, 3);

		// Precio base 1000.0 con 20% de recargo -> Debe dar 1200.0
		Entrada entradaRecargo = new EntradaConRecargo(funcion, asiento, 1000.0, 20.0);

		assertEquals("El precio con recargo debe ser correcto", Double.valueOf(1200.0),
				entradaRecargo.calcularPrecioVenta());
	}

	@Test
	public void deberiaCalcularPrecioDeEntradaConDescuentoAplicandoRebaja() {
		Pelicula pelicula = new Pelicula("Avatar");
		Funcion funcion = new Funcion(pelicula, LocalDate.now(), LocalTime.of(21, 0), 100);
		Asiento asiento = new Asiento(1, 4);

		// Precio base 1000.0 con 15% de descuento -> Debe dar 850.0
		Entrada entradaDescuento = new EntradaConDescuento(funcion, asiento, 1000.0, 15.0);

		assertEquals("El precio con descuento debe ser correcto", Double.valueOf(850.0),
				entradaDescuento.calcularPrecioVenta());
	}

	@Test
	public void deberiaBuscarTicketExitosamentePorCodigoEnElMap() {
		Cine cine = new Cine("Multiplex");
		Pelicula pelicula = new Pelicula("Spider-Man");
		Funcion funcion = new Funcion(pelicula, LocalDate.now(), LocalTime.of(19, 0), 150);
		Asiento asiento = new Asiento(2, 4);

		Entrada entrada = new EntradaEstandar(funcion, asiento, 2500.0);

		// Registramos la entrada en el cine (la guarda en el Map)
		cine.registrarEntrada(entrada);

		// Buscamos el ticket por su código autoincremental
		Entrada encontrada = cine.buscarTicket(entrada.getCodigo());

		assertNotNull("El ticket encontrado no debe ser nulo", encontrada);
		assertEquals("El código del ticket debe coincidir", entrada.getCodigo(), encontrada.getCodigo());
		System.out.println("hola");
	}

	@Test(expected = TicketInexistenteException.class)
	public void deberiaLanzarTicketInexistenteExceptionAlBuscarCodigoInvalido() {
		Cine cine = new Cine("Multiplex");

		// Intentamos buscar un código numérico falso que no fue registrado
		cine.buscarTicket(999);
	}

}
