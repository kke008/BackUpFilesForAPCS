import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *	Population - uses a file with the name, state, population, and designation
 * 				of over 30,000 cities in the US.
 * 		Program prints a list of cities and their information based on user input.
 * 		Ex. printing a list of the 50 most populous cities.
 * 		Program creates lists by creating and sorting a List of all the cities
 * 		with insertion, selection, and merge sorts. The amount of time it takes
 * 		to sort is also recorded and reported to the user.
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Karen Ke
 *	@since	December 7, 2023
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	public Population() {
		cities = new ArrayList<City>();
	}
	
	public static void main(String[] args) {
		Population pop = new Population();
		pop.run();
	}
	
	/** Reads the usPopData2017.txt file and uses it to fill in List<City> cities.
	 *  Also uses deliminiter "[\t\n]"
	 */
	public void fillCities() {
		Scanner reader = FileUtils.openToRead(DATA_FILE);
		int a = 0;
		while(reader.hasNext()) {
			Scanner d = new Scanner(reader.nextLine());
			d.useDelimiter("[\t\n]");
			String state = d.next();
			String city = d.next();
			String type = d.next();
			int population = Integer.parseInt(d.next());
			cities.add(new City(city, state, type, population));
			d.close();
		}
		reader.close();
	}	
	
	/** prints introduction then asks user to choose an option from the 
	 *  menu. Calls appropriate methods for appropriate response.
	 */
	public void run() {
		fillCities();	// making list of citiesn
		
		// printing welcome information for the user / prompting the user
		printIntroduction();
		System.out.printf("%d cities in database\n", cities.size());
		printMenu();
		
		int choice = 0;
		while (choice != 9) {	// responding to user's choice:
			choice = Prompt.getInt("\nEnter selection");
			if (choice != 9) {
				long startMillisec = System.currentTimeMillis();	// timer
				if (choice == 1)	// prints 50 least populous cities
					printByPopulation(true);
				else if (choice == 2)
					printByPopulation(false);
					
				else if (choice == 3)	// prints cities whose names are among the
										// first 50 alphabetically
					printByName(true);
				else if (choice == 4)
					printByName(false);
					
				else if (choice == 5)	// prints most populous cities in given state
					mostPopulousCities();
					
				else if (choice == 6)	// prints all cities of same name
					listCities();
				
				long endMillisec = System.currentTimeMillis();
				System.out.printf("\nElapsed time: %d milliseconds\n\n", 
					endMillisec - startMillisec + 1);
			}
		}
		// printing ending message:
		System.out.println("\n\nThanks for using Population!");
	}
	
	/** Prints 50 least populous cities.
	 *  @param least	true if user wants least populous cities
	 * 					false if user wants most populous cities
	 */
	public void printByPopulation(boolean least) {
		if (least) {
			ascendingPopulation(cities);
			System.out.println("Fifty least populous cities");
		}
		else {
			descendingPopulation(cities);
			System.out.println("Fifty most populous cities");
		}
		
		System.out.printf("     %-22s %-22s %-12s %12s\n", "State", "City", 
			"Type", "Population");
		
		for (int i = 0; i < 50; i++) {
			City city = cities.get(i);
			System.out.printf(i + 1 + ":   " + city.toString());
		}	
	}
	
	/** Prints 50 cities sorted by name.
	 * 	@param first	true if user wants first 50 cities
	 * 					false if user wants last 50 cities
	 */
	public void printByName(boolean first) {
		if (first) {
			ascendingName(cities);
			System.out.println("Fifty cities sorted by name\n");
		}
		
		else {
			descendingName(cities);
			System.out.println("Fifty cities sorted by name descending\n");
		}
		
		System.out.printf("     %-22s %-22s %-12s %12s\n", "State", "City", 
			"Type", "Population");
			
		for (int i = 0; i < 50; i++) {
			City city = cities.get(i);
			System.out.printf(i + 1 + ":   " + city.toString()); 
		}	
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param list		list of City objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	public void swap(List<City> list, int x, int y) {
		City atX = list.get(x);
		City atY = list.get(y);
		list.remove(x);
		list.add(x, atY);
		list.remove(y);
		list.add(y, atX);
	}
	
	/** Sorts cities in ascending order by population using selection sort.
	 *  @param list		list of cities to sort
	 */
	public void ascendingPopulation (List<City> list) {
		for (int lengthOfUnsorted = list.size(); lengthOfUnsorted > 1; lengthOfUnsorted--) {
			int indexOfMax = 0;
			for (int inner = 1; inner < lengthOfUnsorted; inner++) {
				if (list.get(inner).getPopulation() > list.get(indexOfMax).getPopulation())
					indexOfMax = inner;
			}
			swap(list, indexOfMax, lengthOfUnsorted - 1);
		}
	}
	
	
	/** Sorts cities in descending order by population using merge sort.
	 * 	@param list		list of cities to sort
	 */
	public void descendingPopulation(List<City> list) {	//////////////////////////////////////////////////////////////////////////
		List<City> temp = list;
		if (temp.size() == 2 && temp.get(1).getPopulation() > temp.get(0).getPopulation())
			swap(temp, 0, 1);
			
		else if (temp.size() > 2) {
			int mid = (temp.size() - 1) / 2;
			List<City> half1 = new ArrayList<City>();
			for (int i = 0; i <= mid; i++)
				half1.add(temp.get(i));
			
			List<City> half2 = new ArrayList<City>();
			for (int j = mid + 1; j < temp.size(); j++)
				half2.add(temp.get(j));
			
			descendingPopulation(half1);
			descendingPopulation(half2);
			 
			merge(temp, half1, half2, 'p');
		 }
		 
		 for (int k = 0; k < list.size(); k++)
			list.set(k, temp.get(k));
	}
	
	/** Merges the two arrays (the halves in merge sort)
	 *  @param list			the array to be merged		
	 *  @param half1		one of the two arrays to be merged
	 *  @param half2		the other of the two arrays to be merge
	 *  @param popOrName	whether sorting based on population or name
	 */
	public void merge(List<City> list, List<City> half1, List<City> half2, char popOrName) {
		List<City> merged = new ArrayList<City>();
		int ind1 = 0;
		int ind2 = 0;
		while (ind1 < half1.size() && ind2 < half2.size()) {
			int diff = 0;
			if (popOrName == 'p')
				diff = half1.get(ind1).compareTo(half2.get(ind2));
			else if (popOrName == 'n') {
				int z = 0;
				String name1 = half1.get(ind1).getName();
				String name2 = half2.get(ind2).getName();
				while (diff == 0 && z < name1.length() && z < name2.length()) {
					diff = name1.charAt(z) - name2.charAt(z);	
					z++;
				}
			}
				
			if (diff > 0) {
				merged.add(half1.get(ind1));				
				ind1++;
			}
			else if (diff < 0) {
				merged.add(half2.get(ind2));
				ind2++;
			}
			else {
				merged.add(half1.get(ind1));
				merged.add(half2.get(ind2));
				ind1++;
				ind2++;
			}
		}
		
		if (ind1 == half1.size()) {
			for (int a = half2.size() - 1; a >= ind2; a--) {
				merged.add(half2.get(a));
			}
		}
		else if (ind2 == half2.size()) {
			for (int b = half1.size() - 1; b >= ind1; b--) {
				merged.add(half1.get(b));
			}
		}
		
		for (int z = 0; z < merged.size(); z++) {
			list.set(z, merged.get(z));
		}
	}
	
	/** Sorts cities in ascending order by name using insertion sort.
	 *  @param list		list of cities to sort
	 */
	public void ascendingName(List<City> list) {
		for (int i = 1; i < list.size(); i++) {
			City temp = list.get(i);
			int index = i;
			while (index > 0 && temp.getName().compareTo(list.get(index - 1).getName()) < 0)
				index--;
			list.set(index, temp);
		} 
	}
	
	/** Sorts cities in descending order by name using merge sort.
	 *  @param list		list of cities to sort
	 */
	public void descendingName(List<City> list) {	//////////////////////////////////////////////////////////////////////////
		List<City> temp = list;
		if (temp.size() == 2 && temp.get(1).getName().compareTo(temp.get(0).getName()) > 0)
			swap(temp, 0, 1);
			
		else if (temp.size() > 2) {
			int mid = (temp.size() - 1) / 2;
			List<City> half1 = new ArrayList<City>();
			for (int i = 0; i <= mid; i++)
				half1.add(temp.get(i));
			
			List<City> half2 = new ArrayList<City>();
			for (int j = mid + 1; j < temp.size(); j++)
				half2.add(temp.get(j));
			
			descendingName(half1);
			descendingName(half2);
			merge(temp, half1, half2, 'n');
		 }
		 
		 for (int k = 0; k < list.size(); k++)
			list.set(k, temp.get(k));
	}
	
	/** Prints at most 50 of the most populous cities in a given state.
	 */
	public void mostPopulousCities() {
		String stateName = "";
		List<City> inState = new ArrayList<City>();
		while (inState.size() == 0) { // making list of cities in state
			stateName = Prompt.getString("Enter state name (ie. Alabama)");
			System.out.println();
			
			for (int i = 0; i < cities.size(); i++)
				if (cities.get(i).getState().equals(stateName))
					inState.add(cities.get(i));
			
			if (inState.size() == 0)
				System.out.println("ERROR: " + stateName + " is not valid");
		}
		
		ascendingPopulation(inState);
		System.out.printf("Fifty most populous cities in %s\n", stateName);
		System.out.printf("     %-22s %-22s %-12s %12s\n", "State", "City", 
			"Type", "Population");
			
		int limit = 50;
		if (inState.size() < 50)
			limit = inState.size();
		
		for (int j = 0; j < limit; j++) {
			City city = inState.get(inState.size() - 1 - j);
			System.out.println(j + 1 + ":   " + city.toString()); 
		}
	}
	
	/** Prints all the cities of a given name, sorted by population.
	 */
	public void listCities() {
		String cityName = "";
		List<City> ofName = new ArrayList<City>();
		do{
			cityName = Prompt.getString("Enter city name");
			System.out.println();
			
			for (int i = 0; i < cities.size(); i++)	{	// making list of cities with same name
				if (cities.get(i).getName().equals(cityName))
					ofName.add(cities.get(i));
			}
			
			if (ofName.size() == 0)
				System.out.println("ERROR: " + cityName + " is not valid");
		} while (ofName.size() == 0);
		
		ascendingPopulation(ofName);
		System.out.printf("City %s by population\n", cityName);
		System.out.printf("     %-22s %-22s %-12s %12s\n", "State", "City", 
			"Type", "Population");
			
		for (int j = 0; j < ofName.size(); j++) {
			City city = ofName.get(ofName.size() - 1 - j);
			System.out.println(j + 1 + ":   " + city.toString()); 
		}
	}
}
