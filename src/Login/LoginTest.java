package Login;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;


public class LoginTest {

	@Test
	public void test() throws SQLException  {
		LoginController con = new LoginController();
		
		int validacion = con.IngresarBtn("luisa","luisa1234");
		
		assertEquals(2,validacion,0.0001);
	}

}