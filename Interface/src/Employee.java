
public abstract class Employee implements Payable {

	private String firstName;
	private String lastName;
	private String socialSecurityNumber;
	
	public Employee(String first, String last, String ssn) {
		firstName = first;
		lastName = last;
		socialSecurityNumber = ssn;
	}
	
	public void setFirstName(String fn) {
		firstName = fn;
	}
	
	public void setLastName(String ln) {
		lastName = ln;
	}
	
	public void setSocialSecurityNumber(String ssn) {
		socialSecurityNumber = ssn;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}	
	
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s\nsocial security number : %s",getFirstName(),getLastName(),getSocialSecurityNumber());
	}

//	@Override
//	public double getPaymentAmount() {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
