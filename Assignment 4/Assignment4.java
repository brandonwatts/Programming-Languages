import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Assignment4 {
	


	public static void main(String args[]) throws IOException {

		String fileName = "counties.txt";
		String peopleFileName = "people.txt";

		Stream<String> streamPeopleFile = Files.lines(Paths.get(peopleFileName));
		Stream<String> streamFile = Files.lines(Paths.get(fileName));

		Stream<City> streamCity = streamFile
				.map(line -> {String [] l=line.split(",");return new City(l[0],l[1],l[2]);} );
		
		Stream<Person> streamPeople = streamPeopleFile
				.map(line -> {String [] l=line.split(",");return new Person(l[0],l[1],l[2],l[3]);} );
				
		List<City> listCity = streamCity.collect(Collectors.toList());	
		
		List<Person> listPeople = streamPeople.collect(Collectors.toList());
		
		Map<String, List<City>> zipByCounty = listCity.stream()
				.collect(Collectors.groupingBy(p -> p.county);
		
		
		Map<Long, List<Person>> personByZip = listPeople.stream()
				.collect(Collectors.groupingBy(p -> p.zip));
		
		
		
		zipByCounty.forEach((zip, p) -> System.out.format("%s: %s\n", zip, p));
		
		//personByZip.forEach((zip, p) -> System.out.format(" %s: %s\n", zip, p));



	}

}