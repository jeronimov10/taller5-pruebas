package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class ProductoMenuTest {
	
	private ProductoMenu producto;
	
	
	@BeforeEach
	void setup() {
		producto = new ProductoMenu("Hamburguesa", 15000);
		
	}
	
	@AfterEach
	void tearDown() {
		producto = null;
	}
	
	
	@Test
	void testGetNombre() {
		assertEquals("Hamburguesa", producto.getNombre(), "El nombre del producto no es el esperado");
		
	
	}
	
	@Test
	void testGetPrecio() {
		assertEquals(15000, producto.getPrecio(), "El precio del producto del producto no es el esperado");
		
		
	}
	
	@Test
	void testGenerarTextoFactura() {
		String factura = "Hamburguesa\n            15000\n";
		assertEquals(factura, producto.generarTextoFactura(), "El texto de la factura no es el esperado ");
	}

}
