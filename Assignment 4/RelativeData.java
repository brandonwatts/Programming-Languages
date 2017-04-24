/**
*  Helper class that hold the Data to be printed to the screen information
*
* @author  Brandon Watts
* @since   2017-04-24 
*/

public class RelativeData {

	String firstName;
	String lastName;
	Double realativeIncome;
	String county;
	
	public RelativeData(String firstName, String lastName, String income, String county) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.realativeIncome = Double.parseDouble(income);
		this.county = county;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public Double getIncome() {
		return this.realativeIncome;
	}
	
	public String getCounty() {
		return this.county;
	}
	
	public String toString() {
		return this.firstName + " " + this.lastName + " " + Double.toString(this.realativeIncome) + " " + this.county;
	}
}
