package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import java.util.ArrayList;

public class ComboTest {
	
	
	
	private Combo combo;
	
	
	@BeforeEach
	void setup() {
		ArrayList<ProductoMenu> items = new ArrayList<>();
		items.add(new ProductoMenu("Papas fritas", 5000));
		items.add(new ProductoMenu("Te", 1000));
		items.add(new ProductoMenu("hamburguesa", 15000));
		combo = new Combo("Combo 1", 0.25, items);
		
	}
	
	@AfterEach
	void tearDown() {
		combo = null;
	}
	
	
	@Test
	void testGetnombre() {
		assertEquals("Combo 1", combo.getNombre(), "el nombre del combo no es el esperado");
		
	}
	
	@Test
	void testGetPrecio() {
		int precio = (int) ((5000 + 3000 + 15000) * (1-0.25));
		assertEquals(precio, combo.getPrecio(), "El precio del combo no es el esperado");
	}
	
	@Test
	void testGenerarTextoFactura() {
		String factura = "Combo Combo 1\n Descuento: 0.1\n            " + combo.getPrecio() + "\n";
		assertEquals(factura, combo.generarTextoFactura(), "La factura no es la esperada");
	}

}
