package ar.edu.unlam.sistemaDeGestionDeCine;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCine {

	//6. 6. Validar que instanciar una Bebida devuelva su precio base correctamente. (BONFANTI)
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
	
	// 7. Validar que instanciar una Golosina devuelva su precio base correctamente.(BONFANTI)
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
		
		// 8. Validar que el stock inicial de un ProductoCandyBar se asigne y se lea correctamente. (BONFANTI)
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
		
		// 9. Validar que un Combo calcule su precio sumando sus productos internos y aplicando el descuento. (BONFANTI)
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

}
