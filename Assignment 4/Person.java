
public class Person {

	String firstName;
	String lastName;
	Long income;
	Long zip;
	
	public Person(String firstName, String lastName, String income, String zip) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.income = Long.parseLong(income);
		this.zip = Long.parseLong(zip);
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public Long getIncome() {
		return this.income;
	}
	
	public Long getZip() {
		return this.zip;
	}
	
	public String toString() {
		return this.firstName + ", " + this.lastName + ", " + Long.toString(this.income) + ", " + Long.toString(this.zip);
	}
}
