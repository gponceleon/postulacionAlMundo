package postulacion.AlMundo.Employees;

import java.util.Random;

/**
* This class implements the thread used for the incoming calls
* 
* @author  Gabriela Ponceleon
* @version 1.0
* @since  2018-01-30
*/

public class Employee extends Thread {

	protected String typeEmployee;
	protected Boolean occupied;
	protected int employeeId;
	
	/*Constructor*/
	public Employee(String typeEmployee, int employeeId) {
		super();
		this.typeEmployee = typeEmployee;
		this.occupied = false;
		this.employeeId = employeeId;
	}
	
	/*Setters and Getters*/
	public String getTypeEmployee() {
		return typeEmployee;
	}
	public void setTypeEmployee(String typeEmployee) {
		this.typeEmployee = typeEmployee;
	}
	public Boolean getOccupied() {
		return occupied;
	}
	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	
	/**
	 * This method is used to calculate the waiting time in the call 
	 * (is random in range from 5 to 10)

	 * @return int This returns time (random from 5 to 10)
	 */
	
	private int attendTime() {
		
		Random random = new Random (System.currentTimeMillis());
		int time = random.nextInt(10 - 5 + 1) + 10;
		return time;
	}
	
	/**
	 * This method is used to force the thread to wait the time
	 * 
	 * @param time  (random from 5 to 10)
	 * 
	 * @return Nothing.
	 */
	
	private void waitInTheCall(int time) throws InterruptedException {
		Thread.sleep(time * 1000);
	}
	
	
	/**
	 * This method run the thread 
	 * @exception InterruptedException.
	 * @see InterruptedException
	 * @return Nothing.
	 */
	
	@Override
	public void run() {
		
			System.out.println("The Employee: " + this.employeeId + " has begun to attend the call");
			int time = attendTime();
			
			try {
				waitInTheCall(time);
				System.out.println("The Employee: " + this.employeeId + " has finished to attend the call");
				this.occupied=false;
				
			} catch (InterruptedException e) {
				System.out.println(e);
			}
	}
	
}
