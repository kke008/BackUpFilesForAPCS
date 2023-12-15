import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *	Population - <description goes here>
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
		fillCities();
		printByPopulation(true);
		printIntroduction();
		System.out.printf("%d cities in database\n", cities.size());
		printMenu();
		int choice = 0;
		while (choice != 9) {
			choice = Prompt.getInt("\nEnter selection ");
			
			if (choice != 9) {
				///long startMillisec = System.currentTimeMillis();	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				if (choice == 1)	// print 50 least populous cities
					printByPopulation(true);
				else if (choice == 2)
					printByPopulation(false);
					
				else if (choice == 3)
					printByName(true);
				else if (choice == 4)
					printByName(false);
					
				else if (choice == 5) {
					String givenState = Prompt.getString("Enter state name" + 
						" (ie. Alabama) ");
					mostPopulousCities(givenState);
				}
					
				else if (choice == 6) {
					String givenCity = Prompt.getString("Enter city name ");
					listCities(givenCity);
				}
				
				/*
				long endMillisec = System.currentTimeMillis();
				System.out.printf("\nElapsed time: %d milliseconds\n\n", 
					endMillisec - startMillisec + 1);
					*///										!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			}
		}
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
		
		System.out.printf("%5s%-22s%-22s%-11s%11s\n", " ", "State", "City", 
			"Type", "Population");
		
		for (int i = 0; i < 50; i++) {
			City city = cities.get(i);
			System.out.printf("%5s%-22s%-22s%-11s%11d\n", i + 1 + ":", 
				city.getState(), city.getName(), city.getType(), city.getPopulation());
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
		
		System.out.printf("%5s%-22s%-22s%-11s%11s\n", " ", "State", "City", 
			"Type", "Population");
			
		for (int i = 0; i < 4; i++) {	// CHANGE BACK TO 50!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			City city = cities.get(i);
			System.out.printf("%5s%-22s%-22s%-11s%11d\n", i + 1 + ":", 
				city.getState(), city.getName(), city.getType(), city.getPopulation());
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
	private void swap(List<City> list, int x, int y) {
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
			 
			merge(temp, half1, half2);
		 }
		 
		 for (int k = 0; k < list.size(); k++)
			list.set(k, temp.get(k));
	}
	
	/** Merges the two arrays (the halves in merge sort)
	 *  @param list		the array to be merged		
	 *  @param half1	one of the two arrays to be merged
	 *  @param half2	the other of the two arrays to be merge
	 */
	public void merge(List<City> list, List<City> half1, List<City> half2) {
		List<City> merged = new ArrayList<City>();
		int ind1 = 0;
		int ind2 = 0;
		while (ind1 < half1.size() && ind2 < half2.size()) {
			if (half1.get(ind1).compareTo(half2.get(ind2)) > 0) {
				merged.add(half1.get(ind1));				
				ind1++;
			}
			else if (half1.get(ind1).compareTo(half2.get(ind2)) < 0) {
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
			while (index > 0 && temp.compareTo(list.get(index - 1)) < 0){
				list.set(index, list.get(index - 1));
				index--;
			}
			list.set(index, temp);
		} 
	}
	
	/** Sorts cities in descending order by name using merge sort.
	 *  @param list		list of cities to sort
	 */
	public void descendingName(List<City> list) {	//////////////////////////////////////////////////////////////////////////
		List<City> temp = list;
		if (temp.size() == 2 && temp.get(1).compareTo(temp.get(0)) > 0)
			swap(temp, 0, 1);
			
		else if (temp.size() > 2) {
			List<City> half1 = new ArrayList<City>();
			for (int i = 0; i < temp.size()/2; i++)
				half1.set(i, temp.get(i));
			
			List<City> half2 = new ArrayList<City>();
			for (int j = 0; j < temp.size() - temp.size()/2; j++)
				half2.set(j, temp.get(j + temp.size()/2));
			
			descendingName(half1);
			descendingName(half2);
			 
			temp = merge(half1, half2);
		 }
		 
		 for (int k = 0; k < list.size(); k++)
			list.set(k, temp.get(k));
	}
	
	/** Prints most populous city in given state.
	 *  @param stateName	name of state to search in
	 */
	public void mostPopulousCities(String stateName) {
		List<City> inState = new ArrayList<City>();
		for (int i = 0; i < cities.size(); i++) {	// making list of cities in state
			if (cities.get(i).getState().equals(stateName))
				inState.add(cities.get(i));
		}
		
		descendingPopulation(inState);
		System.out.printf("Fifty most populous cities in %s\n", stateName);
		System.out.printf("%5s%-22s%-22s%-11s%11s\n", " ", "State", "City", 
			"Type", "Population");
			
		for (int j = 0; j < 50; j++) {
			City city = cities.get(j);
			System.out.printf("%5s%-22s%-22s%-11s%11d\n", j + 1 + ":", 
				city.getState(), city.getName(), city.getType(), city.getPopulation()); 
		}
	}
	
	/** Prints all the cities of a given name, sorted by population.
	 *  @param cityName		name of city to search for
	 */
	public void listCities(String cityName) {
		List<City> ofName = new ArrayList<City>();
		do{
			for (int i = 0; i < cities.size(); i++)	{	// making list of cities with same name
				if (cities.get(i).getName().equals(cityName))
					ofName.add(cities.get(i));
			}
		} while (ofName.size() == 0);
		
		descendingPopulation(ofName);
		System.out.printf("City %s by population\n", cityName);
		System.out.printf("%5s%-22s%-22s%-11s%11s\n", " ", "State", "City", 
			"Type", "Population");
			
		for (int j = 0; j < ofName.size(); j++) {
			City city = cities.get(j);
			System.out.printf("%5s%-22s%-22s%-11s%11d\n", j + 1 + ":", 
				city.getState(), city.getName(), city.getType(), city.getPopulation()); 
		}
	}
}
