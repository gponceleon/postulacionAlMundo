package postulacion.AlMundo.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import postulacion.AlMundo.Dispatcher.Dispatcher;

/**
 * This class is used to test the Exercise (successful cases and only 10 calls)
 * @author Gabriela Ponceleon
 * @version 1.0
 * @since 2018-01-30
 */

public class DispatcherTestsSuccessful {

	private static Dispatcher dispatcher = null;
	private static int calls = 0;
	
	@BeforeClass
	public static void getDispatcher() {
		
		Properties prop = new Properties();
		File file = new File("src/main/resources/configurations/project.config");
		
	    InputStream input = null;
			try {
				input = new FileInputStream(file.getAbsolutePath());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
		    try {
				prop.load(input);
				
				int cantEmplo = Integer.parseInt(prop.getProperty("app.employees"));
				 calls = Integer.parseInt(prop.getProperty("app.calls"));
				
				dispatcher = Dispatcher.getInstance(cantEmplo,calls);
				
				int cantOper = Integer.parseInt(prop.getProperty("app.operator"));
				int cantSup = Integer.parseInt(prop.getProperty("app.supervisor"));
				int cantDir = Integer.parseInt(prop.getProperty("app.director"));
				
				dispatcher.setEmployees(cantOper,cantSup,cantDir);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
	
	@Before
	public void beforeEachTest() {
		System.out.println("This is executed before each Test");
	}
	
	
	@Test
	public void checkQuantity() {
		int cantOp = 5;
		int cantS = 2;
		int cantD = 3;
		
		assertEquals(true, dispatcher.checkQuantity(cantOp, cantS, cantD));

	}
	
	@Test
	public void runDispatcher() {
		for (int i = 0; i < calls; i++) {
			dispatcher.dispatchCall();
		}
	}
	
	@After
	public void attendedCalls() {
		System.out.println("Checking calls answered");
		assertEquals(10,dispatcher.getCalls());
	}
}
