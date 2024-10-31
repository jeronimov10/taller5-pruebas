package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import java.io.File;
import java.io.FileNotFoundException;

public class PedidoTest {
	
	private Pedido pedido;
	private ProductoMenu producto1;
	private ProductoMenu producto2;
	
	
	@BeforeEach
	void setup() {
		
		pedido = new Pedido("Jeronimo Vasquez", "Carrera 3 #76-35");
		producto1 = new ProductoMenu("Hamburguesa", 15000);
		producto2 = new ProductoMenu("Papas fritas", 5000);
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
	}
	
	@AfterEach
	void tearDown() {
		pedido = null;
	}
	
	
	@Test
	void testGetIdPedido() {
		assertTrue(pedido.getIdPedido() >= 0, "El id del pedido no es correcto");
		
	}
	
	@Test
	void testGetNombreCliente() {
		assertEquals("Jeronimo Vasquez", pedido.getNombreCliente(), "El nombre del cliente no es el esperado");
		
	}
	
	@Test
	void testGetPrecioTotalPedido() {
		int precio = (int) ((15000 + 5000) * 1.19);
		assertEquals(precio, pedido.getPrecioTotalPedido(), "El precio total del pedido no es el esperado");
	}
	
	@Test
	void testGenrarTextoFactura() {
		String factura =  "Cliente: Juan Perez\n" +
                "Dirección: Calle 123\n" +
                "----------------\n" +
                "Hamburguesa\n            15000\n" +
                "Papas Fritas\n            5000\n" +
                "----------------\n" +
                "Precio Neto:  20000\n" +
                "IVA:          3800\n" +
                "Precio Total: 23800\n";
		
		
		assertEquals(factura, pedido.generarTextoFactura(), "La factura no es la esperada");

	}
	
	@Test
	void testGuardarFactura() {
		File archivo = new File("Factura.txt");
		
		try {
			pedido.guardarFactura(archivo);
			assertTrue(archivo.exists(), "El archivo de factura no fue creado");
		} catch (FileNotFoundException e) {
			fail("No se pudo guardar la factura");
		} finally {
			archivo.delete();
		}
	}

}
