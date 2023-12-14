/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Karen Ke
 *	@since	December 7, 2023
 */
public class City implements Comparable<City> {
	
	private String name;	// name of city
	private String state;	// state that city is in
	private String designation;		// type of city it is
	private int population;		// population of city
	
	// constructor
	public City(String nameIn, String stateIn, String typeIn, int popIn) {
		name = nameIn;
		state = stateIn;
		designation = typeIn;
		population = popIn;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	 public int compare(City other) {
		 if (this.population != other.population)
			return (this.population - other.population);
			
		else if (this.state.equals(other.state) == false) {
			int i = 0;
			while (i < other.state.length() && i < state.length()) {
				char letter = state.charAt(i);
				char otherLetter = other.state.charAt(i);
				if (letter != otherLetter)
					return ((int)(letter) - (int)(otherLetter));
			}
			if (i == state.length())	// if name of other state is longer
				return -1;
			else
				return 1;
		}
			
		else {
			int j = 0;
			while (j < name.length() && j < other.name.length()) {
				char letter = name.charAt(j);
				char otherLetter = other.name.charAt(j);
				if (letter != otherLetter)
					return ((int)(letter) - (int)(otherLetter));
			}
			if (j == name.length())	// if name of other name is longer
				return -1;
			else
				return 1;
		}
	 }
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	 public boolean equals(City other) {
		 boolean isEqual = true;
		 for (int i = 0; i < getName().length(); i++) {
			 if (getName().charAt(i) != other.getName().charAt(i))
				isEqual = false;
		 }
		 
		 for (int j = 0; j < getState().length(); j++) {
			 if (getState().charAt(j) != other.getState().charAt(j))
				isEqual = false;
		 }
		 
		 return isEqual;
	 }
	
	/**	Accessor methods */
	public String getName() {
		return name;
	}
	
	public String getState() {
		return state;
	}
	
	public String getType() {
		return designation;
	}
	
	public int getPopulation() {
		return population;
	}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
