/**
 *	The object to store US state information.
 *
 *	@author	Karen Ke
 *	@since	May 21, 2024
 */
public class State implements Comparable<State>
{
	private String name;
	private String abbreviation;
	private int population;
	private int area;
	private int reps;
	private String capital;
	private int month;
	private int day;
	private int year;
	
	public State(String n, String a, int p, int ar, int r, String c, int m, int d, int y) 
	{
		name = n;
		abbreviation = a;
		population = p;
		area = ar;
		reps = r;
		capital = c;
		month = m;
		day = d;
		year = y;
	}
	
	/** Compares states by their names.
	 *  @param other		state that this state is being compared to
	 *  @param 				positive if this state is "bigger"
	 * 						negative if this state is "smaller"
	 */
	public int compareTo(State other) 
	{
		return name.compareTo(other.getName());
	}
	
	/** @return	 name of string */
	public String getName ( )
	{
		return name;
	}
	
	/** @return str		str containing all info of state */
	public String toString() 
	{
		String str = name + addSpaces(20 - name.length());
		str += abbreviation + "    ";
		str += population + addSpaces(10 - ("" + population).length());
		str += area + addSpaces(10 - ("" + area).length());
		str += reps + addSpaces(5 - ("" + reps).length());
		str += capital + addSpaces(20 - capital.length());
		str += month + addSpaces(3 - ("" + month).length());
		str += day + addSpaces(3 - ("" + day).length());
		str += year;
		
		return str;
	}
	
	/** adds spaces to the state string
	 * 	@param numSpaces		number of spaces to add
	 *  @return		new state string with the name
	 */
	public String addSpaces (int numSpaces) {
		String spaces = "";
		for (int i = 0; i < numSpaces; i++) {
			spaces += " ";			
		}
		return spaces;
	}
}
