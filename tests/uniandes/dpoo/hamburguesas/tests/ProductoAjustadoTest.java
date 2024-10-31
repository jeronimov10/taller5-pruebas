package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	
	private ProductoAjustado productoAjustado;
	private Ingrediente ingredienteExtra;
	private Ingrediente ingredienteEliminado;
	
	
	@BeforeEach
	void setup() {
		ProductoMenu productoBase = new ProductoMenu("Hamburguesa", 15000);
		productoAjustado = new ProductoAjustado(productoBase);
		ingredienteExtra = new Ingrediente("Tocineta", 2000);
		ingredienteEliminado = new Ingrediente("Lechuga", 0);
		
		
	}
	
	@AfterEach
	void tearDown() {
		productoAjustado = null;
		ingredienteExtra = null;
		ingredienteEliminado = null;
	}
	
	@Test
	void testGetNombre() {
		assertEquals("Hamburguesa", productoAjustado.getNombre(), "El nombre del producto no es el esperado");
	}
	@Test
	void testGetPrecio() {
		int precio = 15000 + 2000;
		
		assertEquals(precio, productoAjustado.getPrecio(), "El precio del producto no es el esperado");
	}
	@Test
	void testGetFactura() {
		String factura = "Hamburguesa\n    +Tocineta                2000\n    -Lechuga\n            0\n";
		assertEquals(factura, productoAjustado.generarTextoFactura(), "La factura del producto no es la esperada");
	}

}
