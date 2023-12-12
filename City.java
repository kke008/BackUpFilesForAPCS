/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Karen Ke
 *	@since	December 7, 2023
 */
public class City implements Comparable<City> {
	
	// fields
	private String name;
	private String state;
	private String type;
	private int population;
	
	// constructor
	public City(String nameIn, String stateIn, String typeIn, int popIn) {
		name = nameIn;
		state = stateIn;
		type = typeIn;
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
			
		else if (this.state.equals(other.state) == false)
			return (this.state - other.state);
			
		else
			return (this.name - other.name);
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
			 if (getState.charAt(j) != other.getState().charAt(i))
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
		return type;
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
