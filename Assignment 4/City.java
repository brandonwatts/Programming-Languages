/**
* Helper class that hold the City information
*
* @author  Brandon Watts
* @since   2017-04-24 
*/

public class City {

	String county;
	String state;
	Long zip;
	
	public City(String county, String state, String zip) {
		this.county = county;
		this.state = state;
		this.zip = Long.parseLong(zip);
	}
	
	public String getCounty() {
		return this.county;
	}
	
	public String getState() {
		return this.state;
	}
	
	public Long getZip() {
		return this.zip;
	}
	
	public String toString() {
		return this.county + ", " + this.state + ", " + Long.toString(this.zip);
	}
}
