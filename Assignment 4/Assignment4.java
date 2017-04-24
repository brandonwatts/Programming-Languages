import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* This assignment is showcaseing the use of Java 8 streams and lambda expressions.
*
* @author  Brandon Watts
* @since   2017-04-24 
*/
public class Assignment4 {

	public static void main(String args[]) throws IOException {
		
		/*** The files are read in as command line arguements ***/
		String countiesFile = args[0];
		String peopleFile = args[1];

		/*** Those files are then turned into 2 streams ***/
		Stream<String> streamPeopleFile = Files.lines(Paths.get(peopleFile));
		Stream<String> streamCityFile = Files.lines(Paths.get(countiesFile));

		/*** Those 2 streams are then converted to two streams of people and cities ***/
		Stream<City> streamCity = streamCityFile.map(line -> {
			String[] l = line.split(",");
			return new City(l[0], l[1], l[2]);
		});

		Stream<Person> streamPeople = streamPeopleFile.map(line -> {
			String[] l = line.split(",");
			return new Person(l[0], l[1], l[2], l[3]);
		});

		/*** We then turn those streams into lists ***/
		List<City> listCity = streamCity.collect(Collectors.toList());
		List<Person> listPeople = streamPeople.collect(Collectors.toList());

		/*** We create a HashMap that allows us to retrieve the city objects by thier zip ***/
		Map<String, List<City>> countyByState = listCity.stream().collect(Collectors.groupingBy(p -> p.county));
		
		/*** We create a HashMap that allows us to retrieve the city objects by thier zip ***/
		Map<Long, List<City>> zipByCounty = listCity.stream().collect(Collectors.groupingBy(p -> p.zip));

		/*** We create a HashMap that allows us to retrieve the Average income from each county ***/
		Map<Object, Double> avgIncomePerCounty = listPeople.stream().collect(Collectors.groupingBy(
				p -> zipByCounty.get(p.getZip()).get(0).county, Collectors.averagingDouble(Person::getIncome)));

		/*** We create a new stream of the desired output ***/
		Stream<RelativeData> streamR = listPeople.stream()
				.map(p -> p.firstName + " " + p.lastName + " "
						+ (p.income - avgIncomePerCounty.get(zipByCounty.get(p.zip).get(0).county)) + " "
						+ zipByCounty.get(p.zip).get(0).county)
				.map(line -> {
					String[] l = line.split(" ");
					return new RelativeData(l[0], l[1], l[2], l[3]);
				});

		/*** Comparators used to rank the output ***/
		Comparator<RelativeData> byRelativeIncome = ((e2, e1) -> e1.getIncome().compareTo(e2.getIncome()));
		Comparator<RelativeData> byFamilyName = ((e1, e2) -> e1.getLastName().compareTo(e2.getLastName()));
		Comparator<RelativeData> byFirstName = ((e1, e2) -> e1.getFirstName().compareTo(e2.getFirstName()));
		Comparator<RelativeData> byCountyName = ((e1, e2) -> e1.getCounty().compareTo(e2.getCounty()));
		
		/*** We then sort the output, remove the people who do not live in VA, and print it to the screen **/
		streamR.sorted(
				byRelativeIncome.thenComparing(byFamilyName.thenComparing(byFirstName.thenComparing(byCountyName))))
				.filter(p -> {return countyByState.get(p.county).get(0).state.equals("VA");})
				.forEach(x -> System.out.println(x));
	}
}