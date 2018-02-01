package postulacion.AlMundo.Dispatcher;

import postulacion.AlMundo.Employees.Director;
import postulacion.AlMundo.Employees.Employee;
import postulacion.AlMundo.Employees.Operator;
import postulacion.AlMundo.Employees.Supervisor;

/**
 * This class is used to attend the incoming calls 
 * 
 * @author Gabriela Ponceleon
 * @version 1.0
 * @since 2018-01-30
 */

public class Dispatcher {

	private static Dispatcher instance = null;
	private Employee[] employees;
	private int numEmployees;
	private int limitofCalls;
	private int calls;

	protected Dispatcher(int numEmployee, int cantCalls) {
		employees = new Employee[numEmployee];
		numEmployees = numEmployee;
		limitofCalls = cantCalls;
		calls = 0;
	}

	/* Setter and Getter */
	public Employee[] getEmployees() {
		return employees;
	}

	public void setEmployees(Employee[] employees) {
		this.employees = employees;
	}

	public int getNumEmployees() {
		return numEmployees;
	}

	public void setNumEmployees(int numEmployees) {
		this.numEmployees = numEmployees;
	}

	public int getLimitofCalls() {
		return limitofCalls;
	}

	public void setLimitofCalls(int limitofCalls) {
		this.limitofCalls = limitofCalls;
	}

	public int getCalls() {
		return calls;
	}

	public void setCalls(int calls) {
		this.calls = calls;
	}

	/**
	 * This method is used to only return one instance of Dispatcher (Singleton)
	 * range from 5 to 10)
	 * 
	 * @return Dispatcher This returns instance
	 */

	public static Dispatcher getInstance(int numEmployees, int cantCalls) {

		if (instance == null) {
			instance = new Dispatcher(numEmployees, cantCalls);
		}
		return instance;
	}

	

	/**
	 * This method is used set the employees in the array
	 * 
	 * @param int
	 *            numOp Quantity of operators
	 * @param int
	 *            numSup Quantity of supervisors
	 * @param int
	 *            numDi Quantity of directors
	 * @return nothing
	 */
	public void setEmployees(int numOp, int numSup, int numDi) {

		int indexO = (0 + numOp) - 1;
		int indexS = (numOp + numSup) - 1;
		for (int i = 0; i < numEmployees; i++) {
			if (i <= indexO) {

				Employee employeeAux = new Operator("Operador", i);
				employees[i] = employeeAux;
			} else if (i > indexO && i <= indexS) {
				Employee employeeAux = new Supervisor("Supervisor", i);
				employees[i] = employeeAux;
			} else {
				Employee employeeAux = new Director("Director", i);
				employees[i] = employeeAux;
			}
		}

	}

	/**
	 * This method return the employee free who can attend the call
	 * 
	 * @return Employee
	 */
	public Employee getEmployeeFree() {
		int index = 0;
		Employee employee = null;
		while (index < numEmployees) {
			if (!this.employees[index].getOccupied()) {
				employee = this.employees[index];
				employee.setOccupied(true);
				break;
			}

			index++;
		}

		return employee;
	}

	/**
	 * This method checks if the quantity of employee (gives for type) is greater than numEmployees
	 * 
	 * @param int
	 *            numOp Quantity of operators
	 * @param int
	 *            numSup Quantity of supervisors
	 * @param int
	 *            numDi Quantity of directors
	 * @return true/false
	 */
	
	public Boolean checkQuantity(int numOp, int numS, int numD) {
		int sum = numOp + numS + numD;
		Boolean result = sum > numEmployees ? false : true;

		return result;
	}
	
	/**
	 * this method answers the call and assigns it to a free employee
	 * @return nothing
	 */
	
	public void dispatchCall() {

		if (calls < limitofCalls) {
			Employee employee = getEmployeeFree();
			System.out.println("Assign the call to employee: " + employee.getTypeEmployee());
			employee.start();
			calls++;
		} else {
			System.out.println("Can not attend more calls");
		}

	}

}
