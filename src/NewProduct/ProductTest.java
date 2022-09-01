package NewProduct;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

public class ProductTest {
    
    @Test
	public void test() throws SQLException {
		NewProducto prod= new NewProducto();
		
		int validacion = prod.clickNuevo( "princesa de mario bross", "4569", "5000", "500", "30", "50");
		
		assertEquals(2,validacion,0.0001);
	}

}
