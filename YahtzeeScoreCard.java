/**
 *	Displays a table with each player's name, the 13 categories they can
 *  score in, and their scores (if any) in each category. Sets / changes 
 *  the score in categories selected by the players.
 *
 *	@author	Karen Ke
 *	@since	September 28, 2023
 */
public class YahtzeeScoreCard {
	private final int NUM_CATEGORIES = 13;	// number of categories
	private final int INDEX_THREE_KIND = 7;	// index of Three of a Kind score
	private final int INDEX_FOUR_KIND = 8;	// index of Four of a Kind score
	private final int INDEX_FULL_HOUSE = 9;	// index of Full House score
	private final int INDEX_SMALL_STRAIGHT = 10;	// index of Small
													// Straight score
	private final int INDEX_LARGE_STRAIGHT = 11;	// index of Large
													// Straight score
	private final int INDEX_CHANCE = 12;	// index of Chance score
	private final int INDEX_YAHTZEE = 13;	// index of Yahtzee score
	private int [] scores;	// array to keep track of category scores
	
	public YahtzeeScoreCard() {
		scores = new int[NUM_CATEGORIES];
	}
	
	/**
	 *	Get a category score on the score card.
	 *	@param category		the category number (1 to 13)
	 *	@return				the score of that category
	 */
	public int getScore(int category) {
		return scores[category];
	}
	
	/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
						"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	/**
	 *  Prints the player's score
	 */
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 1; i < 14; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}


	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg) {
		boolean notTaken = false;
		if (choice > 0 && choice <= NUM_CATEGORIES && getScore(choice) != 0) {
			if (choice < INDEX_THREE_KIND)
				numberScore(choice, dg);
				
			else if (choice == INDEX_THREE_KIND)
				threeOfAKind(dg);
			
			else if (choice == INDEX_FOUR_KIND)
				fourOfAKind(dg);
				
			else if (choice == INDEX_FULL_HOUSE)
				fullHouse(dg);
				
			else if (choice == INDEX_SMALL_STRAIGHT)
				smallStraight(dg);
				
			else if (choice == INDEX_LARGE_STRAIGHT)
				largeStraight(dg);
				
			else if (choice == INDEX_CHANCE)
				chance(dg);
				
			else if (choice == INDEX_YAHTZEE)
				yahtzeeScore(dg);
			
			notTaken = true;
		}
		return notTaken;
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		scores[choice] = dg.getTotal();
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) {
		scores[INDEX_THREE_KIND] = dg.getTotal();
	}
	
	/**
	 *	Updates the scorecard for Four Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void fourOfAKind(DiceGroup dg) {
		scores[INDEX_FOUR_KIND] = dg.getTotal();
	}
	
	/**
	 *	Updates the scorecard for Full House choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void fullHouse(DiceGroup dg) {
		scores[INDEX_FULL_HOUSE] = dg.getTotal();
	}
	
	/**
	 *	Updates the scorecard for Small Straight choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void smallStraight(DiceGroup dg) {
		scores[INDEX_SMALL_STRAIGHT] = dg.getTotal();
	}
	
	/**
	 *	Updates the scorecard for Large Straight choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void largeStraight(DiceGroup dg) {
		scores[INDEX_LARGE_STRAIGHT] = dg.getTotal();
	}
	
	/**
	 *	Updates the scorecard for Chance choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void chance(DiceGroup dg) {
		scores[INDEX_CHANCE] = dg.getTotal();
	}
	
	/**
	 *	Updates the scorecard for Yahtzee choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void yahtzeeScore(DiceGroup dg) {
		scores[INDEX_YAHTZEE] = dg.getTotal();
	}

}
