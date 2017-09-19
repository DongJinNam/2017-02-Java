
public class SalariedEmployee extends Employee {

	private double weeklySalary;
	
	public SalariedEmployee(String first,String last,String ssn,double salary) {
		// TODO Auto-generated constructor stub
		super(first,last,ssn);
		weeklySalary = salary;
	}
	
	public void setWeeklySalary(double salary) {
		weeklySalary = salary;
	}
	
	public double getWeeklySalary() {
		return weeklySalary;
	}
	
	@Override
	public double getPaymentAmount() {
		// TODO Auto-generated method stub
		return getWeeklySalary();
	}
	
	@Override
	public String toString() {
		return String.format("salaried employee: %s\n%s : $%,.2f",super.toString(),"weekly salary",getWeeklySalary());
	}

}
