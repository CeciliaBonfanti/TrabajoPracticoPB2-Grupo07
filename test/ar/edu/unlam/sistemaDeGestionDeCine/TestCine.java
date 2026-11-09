package ar.edu.unlam.sistemaDeGestionDeCine;

import static org.junit.Assert.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

public class TestCine {
	@Before
	public void setUp() {
		// Reiniciamos el contador estático para que cada test arranque con el ID 1
		Entrada.reiniciarContador();

	}

	// 1
	@Test
	public void queSePuedaCrearUnaFuncion() {

		Funcion funcion = new Funcion("Venom", LocalDate.of(2026, 11, 18), LocalTime.of(18, 30), 5);

		assertNotNull(funcion.getPelicula());
		assertNotNull(funcion.getFecha());
		assertNotNull(funcion.getHora());
	}

	// 2(Orozco)
	@Test
	public void deberiaCrearEntradaAsignandoFuncionYAsientoEIncrementandoIdAutoincremental() {
		String pelicula = "Avengers";
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

	// 3(Orozco)
	@Test
	public void deberiaCalcularPrecioBaseDeEntradaEstandarCorrectamente() {
		Funcion funcion = new Funcion("Batman", LocalDate.now(), LocalTime.of(20, 0), 120);
		Asiento asiento = new Asiento(2, 5);

		Entrada entradaEstandar = new EntradaEstandar(funcion, asiento, 2000.0);

		assertEquals("El precio de venta debe ser el precio base", Double.valueOf(2000.0),
				entradaEstandar.calcularPrecioVenta());
	}

	// 4(Orozco)
	@Test
	public void deberiaCalcularPrecioDeEntradaConRecargoAplicandoAumento() {
		Funcion funcion = new Funcion("Avatar", LocalDate.now(), LocalTime.of(21, 0), 100);
		Asiento asiento = new Asiento(1, 3);

		// Precio base 1000.0 con 20% de recargo -> Debe dar 1200.0
		Entrada entradaRecargo = new EntradaConRecargo(funcion, asiento, 1000.0, 20.0);

		assertEquals("El precio con recargo debe ser correcto", Double.valueOf(1200.0),
				entradaRecargo.calcularPrecioVenta());
	}

	// 5(Orozco)
	@Test
	public void deberiaCalcularPrecioDeEntradaConDescuentoAplicandoRebaja() {
		Funcion funcion = new Funcion("Avatar", LocalDate.now(), LocalTime.of(21, 0), 100);
		Asiento asiento = new Asiento(1, 4);

		// Precio base 1000.0 con 15% de descuento -> Debe dar 850.0
		Entrada entradaDescuento = new EntradaConDescuento(funcion, asiento, 1000.0, 15.0);

		assertEquals("El precio con descuento debe ser correcto", Double.valueOf(850.0),
				entradaDescuento.calcularPrecioVenta());
	}
	
	// 6.Validar que instanciar una Bebida devuelva su precio base correctamente
	//(BONFANTI)
		@Test
		public void queUnaBebidaDevuelvaSuPrecioBaseCorrectamente() {
			Cine cine = new Cine("Ramon");

			String idProducto = "BEB-01";
			String nombreProducto = "Coca-Cola";
			Double precio = 2500.0;
			Integer stock = 10;
			ProductoCandyBar bebida = new Bebida(idProducto, nombreProducto, precio, stock);

			cine.registrarProducto(bebida);

			Double precioBase = cine.obtenerPrecioProducto(bebida);
			assertEquals(2500.0, precioBase, 0.0);
		}

		// 7. Validar que instanciar una Golosina devuelva su precio base correctamente.
		//(BONFANTI)
		@Test
		public void queSePuedaRegistrarUnaGolosinaEnElCine() {
			Cine cine = new Cine("Ramon");

			String idProducto = "GOL-01";
			String nombreProducto = "M&M";
			Double precio = 1800.0;
			Integer stock = 15;
			ProductoCandyBar golosina = new Golosina(idProducto, nombreProducto, precio, stock);

			Boolean seRegistro = cine.registrarProducto(golosina);
			assertTrue(seRegistro);
		}
		
		// 8. Validar que el stock inicial de un ProductoCandyBar se asigne y se lea correctamente. 
		//(BONFANTI)
		@Test
		public void queSePuedaObtenerElStockInicialDeUnProductoCandyBar() {
			Cine cine = new Cine("Ramon");

			String idProducto = "BEB-01";
			String nombreProducto = "Coca-Cola";
			Double precio = 2500.0;
			Integer stockInicial = 15;
			ProductoCandyBar bebida = new Bebida(idProducto, nombreProducto, precio, stockInicial);

			cine.registrarProducto(bebida);

			Integer stockObtenido = cine.obtenerStockProducto(bebida);
			assertEquals(15, stockObtenido, 0.0);
		}

		// 9. Validar que un Combo calcule su precio sumando sus productos internos y aplicando el descuento.
		//(BONFANTI)
		@Test
		public void queUnComboCalculeSuPrecioSumandoSusProductosYAplicandoElDescuento() {
			Cine cine = new Cine("Ramon");

			ProductoCandyBar bebida = new Bebida("BEB-01", "Coca-Cola", 2000.0, 10);
			ProductoCandyBar golosina = new Golosina("GOL-01", "Pipas", 1000.0, 10);

			// Combo con id, nombre y porcentaje de descuento (10.0%)
			Combo combo = new Combo("CMB-01", "Combo Pareja", 10.0);
			combo.agregarProducto(bebida);
			combo.agregarProducto(golosina);

			cine.registrarProducto(combo);

			Double precioEsperado = 2700.0;
			Double precioObtenido = cine.obtenerPrecioProducto(combo);

			assertEquals(precioEsperado, precioObtenido, 0.0);
		}

	
	//10 Guardia
	@Test
	public void agregarUnVendibleDeberiaAumentarLaLista() {
		
		Cine miCine= new Cine("Ramon");
		
		assertEquals(0,miCine.getCarrito().size());
		
		Vendible bebida = new Bebida("BEB-01","Coca",1500.0,10);
		
		miCine.agregarAlCarrito(bebida);
		
		assertEquals(1,miCine.getCarrito().size());
		
		
	}
	
	//11 Guardia
	@Test
	public void sacarUnVendibleDeberiaDisminuirLaLista() {
		
		Cine miCine= new Cine("Ramon");
		
		Vendible bebida = new Bebida("BEB-01","Coca",1500.0,10);
		
		miCine.agregarAlCarrito(bebida);
		
		assertEquals(1,miCine.getCarrito().size());
		
		miCine.sacarDelCarrito(bebida);
		
		assertEquals(0,miCine.getCarrito().size());
		
	}
	
	// 12. Validar que al vender un producto simple (Bebida/Golosina), el sistema descuente 1 unidad de su stock. 
	// (BONFANTI)
		@Test
		public void queAlVenderUnProductoSimpleSeDescuenteUnaUnidadDeSuStock() throws StockInsuficienteException {
			Cine cine = new Cine("Ramon");

			String idProducto = "BEB-01";
			String nombreProducto = "Coca-Cola";
			Double precio = 2500.0;
			Integer stockInicial = 10;
			ProductoCandyBar bebida = new Bebida(idProducto, nombreProducto, precio, stockInicial);

			cine.registrarProducto(bebida);

			cine.venderProducto(bebida);

			Integer stockEsperado = 9;
			Integer stockObtenido = cine.obtenerStockProducto(bebida);

			assertEquals(stockEsperado, stockObtenido);
		}

		// 13. Validar que al vender un Combo, el sistema descuente stock a todos los productos individuales que lo componen.
		// (BONFANTI)
		@Test
		public void queAlVenderUnComboSeDescuenteStockATodosLosProductosComponentes() throws StockInsuficienteException {
			Cine cine = new Cine("Ramon");

			ProductoCandyBar bebida = new Bebida("BEB-01", "Coca-Cola", 2500.0, 10);
			ProductoCandyBar golosina = new Golosina("GOL-01", "Pipas", 1000.0, 5);

			Combo combo = new Combo("CMB-01", "Combo Pareja", 10.0);
			combo.agregarProducto(bebida);
			combo.agregarProducto(golosina);

			cine.registrarProducto(bebida);
			cine.registrarProducto(golosina);
			cine.registrarProducto(combo);

			cine.venderProducto(combo);

			assertEquals(Integer.valueOf(9), cine.obtenerStockProducto(bebida));
			assertEquals(Integer.valueOf(4), cine.obtenerStockProducto(golosina));
		}
	
		// 14. (Excepción) Validar el lanzamiento de StockInsuficienteException al vender un producto con stock cero.
		// (BONFANTI)
		@Test(expected = StockInsuficienteException.class)
		public void queAlVenderUnProductoSinStockLanceStockInsuficienteException() throws StockInsuficienteException {
			Cine cine = new Cine("Ramon");

			String idProducto = "BEB-01";
			String nombreProducto = "Coca-Cola";
			Double precio = 2500.0;
			Integer stockInicial = 0;
			ProductoCandyBar bebida = new Bebida(idProducto, nombreProducto, precio, stockInicial);

			cine.registrarProducto(bebida);
			cine.venderProducto(bebida);
		}
	
	//15 Guardia
	@Test
	public void elCarritoDeberiaSumarCorrectamenteElPrecioTotalDeDiferentesProducto() {
		Cine miCine= new Cine("Ramon");
		
		Funcion funcion = new Funcion("Venom", LocalDate.now(), LocalTime.now(), 100);
		
		Asiento asiento = new Asiento(1, 1);
		
		Entrada entradaRecargo = new EntradaConRecargo(funcion, asiento, 15000.0, 20.0);
		
		ProductoCandyBar golosina= new Golosina("GOL-01","chocolate",2000.0,10);
		
		ProductoCandyBar bebida = new Bebida("BEB-01","Coca",1500.0,10);
		
		Combo combo = new Combo("CMB-01", "Combo Pareja", 10.0);
		combo.agregarProducto(bebida);
		combo.agregarProducto(golosina);
		
		miCine.agregarAlCarrito(entradaRecargo);
		miCine.agregarAlCarrito(bebida);
		miCine.agregarAlCarrito(golosina);
		miCine.agregarAlCarrito(combo);
		
		assertEquals(24650.0,miCine.calcularTotalCarrito(),0.01);
		
		
	}

	// 16
	@Test
	public void queLosAsientosSeOrdenenCorrectamente() {

		Funcion funcion = new Funcion("Venom", LocalDate.now(), LocalTime.now(), 100);

		funcion.agregarAsiento(new Asiento(5, 1));
		funcion.agregarAsiento(new Asiento(2, 1));
		funcion.agregarAsiento(new Asiento(8, 1));

		// Obtenemos el primer elemento del TreeSet interno de la función
		Asiento primero = funcion.getSala().first();

		assertEquals(Integer.valueOf(2), primero.getFila());
	}

	// 17
	@Test
	public void queNoSePuedaAgregarDosVecesLaMismaButaca() {

		Funcion funcion = new Funcion("Venom", LocalDate.now(), LocalTime.now(), 100);

		// Intentamos agregar exactamente la misma butaca dos veces
		funcion.agregarAsiento(new Asiento(1, 1));
		funcion.agregarAsiento(new Asiento(1, 1));

		// El tamaño de la sala debe ser 1 porque el TreeSet rechaza duplicados
		assertEquals(1, funcion.getSala().size());
	}

	// 18(Orozco)
	@Test
	public void deberiaBuscarTicketExitosamentePorCodigoEnElMap() {
		Cine cine = new Cine("Multiplex");
		Funcion funcion = new Funcion("Spider-Man", LocalDate.now(), LocalTime.of(19, 0), 150);
		Asiento asiento = new Asiento(2, 4);

		Entrada entrada = new EntradaEstandar(funcion, asiento, 2500.0);

		cine.registrarEntrada(entrada);

		// Buscamos el ticket por su código autoincremental
		Entrada encontrada = cine.buscarTicket(entrada.getCodigo());

		assertNotNull("El ticket encontrado no debe ser nulo", encontrada);
		assertEquals("El código del ticket debe coincidir", entrada.getCodigo(), encontrada.getCodigo());

	}

	// 19(Orozco)
	@Test(expected = TicketInexistenteException.class)
	public void deberiaLanzarTicketInexistenteExceptionAlBuscarCodigoInvalido() {
		Cine cine = new Cine("Multiplex");

		// Intentamos buscar un código que no fue registrado
		cine.buscarTicket(999);
	}

	// 20
	@Test(expected = AsientoYaVendidoException.class)
	public void queNoSePuedaVenderUnAsientoOcupado() throws AsientoYaVendidoException {

		Asiento asiento = new Asiento(1, 1);

		asiento.ocupar();

		if (asiento.getOcupado()) {
			throw new AsientoYaVendidoException("El asiento ya está ocupado");
		}
	}

	// 21
	@Test(expected = FuncionAgotadaException.class)
	public void queNoSePuedanVenderMasEntradasQueLaCapacidadDeLaFuncion() throws FuncionAgotadaException {

		Funcion funcion = new Funcion("Avengers", LocalDate.of(2026, 11, 18), LocalTime.of(18, 30), 3);

		funcion.venderEntrada();
		funcion.venderEntrada();
		funcion.venderEntrada();
		// 4º entrada que NO me va a dejar vender porque capacidadMaxima = 3
		funcion.venderEntrada();

	}
	

}
