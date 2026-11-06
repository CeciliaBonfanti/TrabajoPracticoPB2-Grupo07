package ar.edu.unlam.sistemaDeGestionDeCine;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

import org.junit.Test;

public class TestCine {

// 1
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
		// 4º entrada que NO me va a dejar vender porque capacidadMaxima = 3
		funcion.venderEntrada();

	}

	@Test

	public void queSePuedaRegistrarUnaBebidaEnElCine() {
		Cine cine = new Cine();

		String idProducto = "BEB-01";
		String nombreProducto = "Coca-Cola";
		Double precio = 2500.0;
		Integer stock = 10;
		ProductoCandyBar bebida = new Bebida(idProducto, nombreProducto, precio, stock);

		Boolean seRegistro = cine.registrarProducto(bebida);
		assertTrue(seRegistro);
	}

	// 6. 6. Validar que instanciar una Bebida devuelva su precio base
	// correctamente. (BONFANTI)
	@Test
	public void queUnaBebidaDevuelvaSuPrecioBaseCorrectamente() {
		Cine cine = new Cine();

		String idProducto = "BEB-01";
		String nombreProducto = "Coca-Cola";
		Double precio = 2500.0;
		Integer stock = 10;
		ProductoCandyBar bebida = new Bebida(idProducto, nombreProducto, precio, stock);

		cine.registrarProducto(bebida);

		Double precioBase = cine.obtenerPrecioProducto(bebida);
		assertEquals(2500.0, precioBase, 0.0);
	}

	// 7. Validar que instanciar una Golosina devuelva su precio base
	// correctamente.(BONFANTI)
	@Test
	public void queSePuedaRegistrarUnaGolosinaEnElCine() {
		Cine cine = new Cine();

		String idProducto = "GOL-01";
		String nombreProducto = "M&M";
		Double precio = 1800.0;
		Integer stock = 15;
		ProductoCandyBar golosina = new Golosina(idProducto, nombreProducto, precio, stock);

		Boolean seRegistro = cine.registrarProducto(golosina);
		assertTrue(seRegistro);
	}

	@Test
	public void queUnaGolosinaDevuelvaSuPrecioBaseCorrectamente() {
		Cine cine = new Cine();

		String idProducto = "GOL-01";
		String nombreProducto = "M&M";
		Double precio = 1800.0;
		Integer stock = 15;
		ProductoCandyBar golosina = new Golosina(idProducto, nombreProducto, precio, stock);

		cine.registrarProducto(golosina);

		Double precioBase = cine.obtenerPrecioProducto(golosina);
		assertEquals(1800.0, precioBase, 0.0);
	}

	// 8. Validar que el stock inicial de un ProductoCandyBar se asigne y se lea
	// correctamente. (BONFANTI)
	@Test
	public void queSePuedaObtenerElStockInicialDeUnProductoCandyBar() {
		Cine cine = new Cine();

		String idProducto = "BEB-01";
		String nombreProducto = "Coca-Cola";
		Double precio = 2500.0;
		Integer stockInicial = 15;
		ProductoCandyBar bebida = new Bebida(idProducto, nombreProducto, precio, stockInicial);

		cine.registrarProducto(bebida);

		Integer stockObtenido = cine.obtenerStockProducto(bebida);
		assertEquals(15, stockObtenido, 0.0);
	}

	// 9. Validar que un Combo calcule su precio sumando sus productos internos y
	// aplicando el descuento. (BONFANTI)
	@Test
	public void queUnComboCalculeSuPrecioSumandoSusProductosYAplicandoElDescuento() {
		Cine cine = new Cine();

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

	// 11. Validar que al vender un producto simple (Bebida/Golosina), el sistema
	// descuente 1 unidad de su stock. (BONFANTI)
	@Test
	public void queAlVenderUnProductoSimpleSeDescuenteUnaUnidadDeSuStock() throws StockInsuficienteException {
		Cine cine = new Cine();

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

	// 12. Validar que al vender un Combo, el sistema descuente stock a todos los
	// productos individuales que lo componen. (BONFANTI)
	@Test
	public void queAlVenderUnComboSeDescuenteStockATodosLosProductosComponentes() throws StockInsuficienteException {
		Cine cine = new Cine();

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

	// 13. (Excepci�n) Validar el lanzamiento de StockInsuficienteException al
	// vender un producto con stock cero. (BONFANTI)
	@Test(expected = StockInsuficienteException.class)
	public void queAlVenderUnProductoSinStockLanceStockInsuficienteException() throws StockInsuficienteException {
		Cine cine = new Cine();

		String idProducto = "BEB-01";
		String nombreProducto = "Coca-Cola";
		Double precio = 2500.0;
		Integer stockInicial = 0;
		ProductoCandyBar bebida = new Bebida(idProducto, nombreProducto, precio, stockInicial);

		cine.registrarProducto(bebida);
		cine.venderProducto(bebida);
	}

}
