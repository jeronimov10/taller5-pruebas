package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RestauranteTest {
	private Restaurante restaurante;
	
	
	@BeforeEach
	void setup() {
		restaurante = new Restaurante();
	}
	
	@AfterEach
	void tearDown() {
		restaurante = null;
	}
	
	
	@Test
	void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
		restaurante.iniciarPedido("Jacobo Zarruk", "Carrera 3#76-35");
		assertNotNull(restaurante.getPedidoEnCurso(), "El pedido deberia estar inicializado");
		
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
            restaurante.iniciarPedido("Sofia Zarruk", "carrera 3#76-35");
        }, "Se debería lanzar una excepción si ya hay un pedido en curso.");
		
	}
	
	@Test
	void testCerrarYGuardarPedido() throws YaHayUnPedidoEnCursoException, IOException {
		restaurante.iniciarPedido("Jeronimo Vasquez", "City U");
		Pedido pedidoEnCurso = restaurante.getPedidoEnCurso();
		File factura = new File("factura_" + pedidoEnCurso.getIdPedido() + ".txt");
		ProductoMenu producto = new ProductoMenu("Hamburguesa", 15000);
	    pedidoEnCurso.agregarProducto(producto);
	    
	    try {
            try {
				restaurante.cerrarYGuardarPedido();
			} catch (NoHayPedidoEnCursoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            assertNull(restaurante.getPedidoEnCurso(), "El pedido en curso debería ser null después de cerrarlo.");
            assertTrue(factura.exists(), "El archivo de la factura debería existir.");
        } finally {
            factura.delete();
        }

        
        assertThrows(NoHayPedidoEnCursoException.class, () -> {
            restaurante.cerrarYGuardarPedido();
        }, "Se debería lanzar una excepción si no hay un pedido en curso.");

		
	}
	
	@Test
	void testGetPedidos() throws YaHayUnPedidoEnCursoException, IOException {
		restaurante.iniciarPedido("Jacobo Zarruk", "City U");
		Pedido pedidoEnCurso = restaurante.getPedidoEnCurso();
		pedidoEnCurso.agregarProducto(new ProductoMenu("Hamburguesa", 15000));
		try {
			restaurante.cerrarYGuardarPedido();
		} catch (NoHayPedidoEnCursoException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(1, restaurante.getPedidos().size(), "Debería haber un pedido cerrado en el historial.");
	}
	
	@Test
    void testGetMenuBase() {
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 15000);
        ProductoMenu producto2 = new ProductoMenu("Papas Fritas", 5000);
        restaurante.getMenuBase().add(producto1);
        restaurante.getMenuBase().add(producto2);

        assertEquals(2, restaurante.getMenuBase().size(), "El menú base debería contener dos productos.");
        assertTrue(restaurante.getMenuBase().contains(producto1), "El menú base debería contener la Hamburguesa.");
        assertTrue(restaurante.getMenuBase().contains(producto2), "El menú base debería contener las Papas Fritas.");
    }

    @Test
    void testGetMenuCombos() {
        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(new ProductoMenu("Hamburguesa", 15000));
        Combo combo = new Combo("Combo 1", 0.1, items);
        restaurante.getMenuCombos().add(combo);

        assertEquals(1, restaurante.getMenuCombos().size(), "El menú de combos debería contener un combo.");
        assertEquals("Combo 1", restaurante.getMenuCombos().get(0).getNombre(), "El combo debería llamarse 'Combo 1'.");
    }

    @Test
    void testGetIngredientes() {
        Ingrediente ingrediente1 = new Ingrediente("Tocineta", 2000);
        Ingrediente ingrediente2 = new Ingrediente("Lechuga", 800);
        restaurante.getIngredientes().add(ingrediente1);
        restaurante.getIngredientes().add(ingrediente2);

        assertEquals(2, restaurante.getIngredientes().size(), "La lista de ingredientes debería contener dos ingredientes.");
        assertTrue(restaurante.getIngredientes().contains(ingrediente1), "La lista de ingredientes debería contener Tomate.");
        assertTrue(restaurante.getIngredientes().contains(ingrediente2), "La lista de ingredientes debería contener Lechuga.");
    }
	
	
	

}
