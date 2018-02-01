package postulacion.AlMundo.Main;

import postulacion.AlMundo.Dispatcher.Dispatcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* Postulation Exercise for AlMundo
* 
* @author  Gabriela Ponceleon
* @version 1.0
* @since  2018-01-30
*/

public class main {

	public static void main(String[] args) {

		/*Loading the properties in the project.config file*/
		Properties prop = new Properties();
		File file = new File("src/main/resources/configurations/project.config");

		InputStream input = null;
		try {
			input = new FileInputStream(file.getAbsolutePath());
		} catch (FileNotFoundException e1) {
			System.out.println(e1);
		}

		try {
			prop.load(input);

			/*Initializing the Dispatcher*/
			
			int cantEmplo = Integer.parseInt(prop.getProperty("app.employees"));
			int calls = Integer.parseInt(prop.getProperty("app.calls"));

			Dispatcher dispatcher = Dispatcher.getInstance(cantEmplo, calls);

			int cantOper = Integer.parseInt(prop.getProperty("app.operator"));
			int cantSup = Integer.parseInt(prop.getProperty("app.supervisor"));
			int cantDir = Integer.parseInt(prop.getProperty("app.director"));

			Boolean quantity = dispatcher.checkQuantity(cantOper, cantSup, cantDir);

			if (quantity) {
				
				dispatcher.setEmployees(cantOper, cantSup, cantDir);
				
				for (int i = 0; i < calls; i++) {
					
					dispatcher.dispatchCall();
				}
			}else {
				System.out.println("The sum of type of employees is greater than quantity of employees");
			}

		} catch (IOException e) {
			System.out.println(e);
		}

	}

}
