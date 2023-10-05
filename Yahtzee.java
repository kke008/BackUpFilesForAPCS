import java.util.Scanner;

/**
 *	Introduce the game here
 *
 *	@author	Karen Ke
 *	@since	September 28, 2023
 */
 
public class Yahtzee {
	private final int ROUNDS = 13;	// 13 rounds per game
	private final int MAX_HOLD_ROUNDS = 3;	// max num of times player
											// can hold dice
	private YahtzeePlayer player1;	// Player 1 info
	private DiceGroup p1Dice;	// Player 1's dice
	private YahtzeePlayer player2;	// Player 2 info
	private DiceGroup p2Dice;	// Player 2's dice
	private Scanner readWait;	// Scanner to wait for players to hit enter
	
	public Yahtzee() {
		 player1 = new YahtzeePlayer();
		 p1Dice = new DiceGroup();
		 player2 = new YahtzeePlayer();
		 p2Dice = new DiceGroup();
		 readWait = new Scanner(System.in);
	}
	
	public static void main(String [] args) {
		Yahtzee yz = new Yahtzee();
		yz.printHeader();
		yz.getPlayerNames();
		yz.playGame();
		//yz.winner();
	}
	
	public void printHeader() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
	}
	
	/**	Gets the players names and prompts them to roll the dice to determine
	 * 	who will go first. The player who goes first will be player 1 and
	 * 	the player who goes second will be player 2.
	 */
	public void getPlayerNames() {
		String p1Name = "";	// name of player who goes first (temporarily 
							// player who gives name first)
		p1Name = Prompt.getString("Player 1, please enter your first name ");
		
		String p2Name = "";	// name of player who goes second (temporarily 
							// player who gives name second)
		p2Name = Prompt.getString("\n\nPlayer 2, please enter your first name ");
		
		int p1Total = 0;
		int p2Total = 0;
		while (p1Total == p2Total) {
			System.out.printf("\n\nLet's see who will go first. %s please hit " +
				"enter to roll the dice :", p1Name);
			while (!readWait.hasNextLine()) {}
			readWait.nextLine();
			p1Dice.rollDice();
			p1Dice.printDice();
			p1Total = p1Dice.getTotal();
			
			System.out.printf("\n\n%s, it's your turn. Please hit enter to roll" +
				" the dice :", p2Name);
			while (!readWait.hasNextLine()) {}
			readWait.nextLine();
			p2Dice.rollDice();
			p2Dice.printDice();
			p2Total = p2Dice.getTotal();
			
			if (p1Total == p2Total)
				System.out.printf("\n\nWhoops, we have a tie (both rolled " +
					"%d). Looks like we'll have to try that again...", p1Total);
		}
		
		System.out.printf("\n\n%s, you rolled a sum of %d, and %s, you " +
			"tolled a sum of %d.", p1Name, p1Total, p2Name, p2Total);
		
		if (p1Total < p2Total) {
			String tempName = p1Name;
			p1Name = p2Name;
			p2Name = tempName;
		}
		
		System.out.printf("\n%s, since your sum was higher, you'll " +
				"roll first.\n\n", p1Name);
		
		player1.setName(p1Name);
		player2.setName(p2Name);
		
	}
	/*
	class PlayerListener implements KeyListener {
		public void keyPressed(KeyEvent evt) {
			int code = evt.getKeyCode();
			if (code == VK_ENTER)
		}
	}
	*/
	
	/** Runs 13 rounds of the game.
	 */
	public void playGame() {
		for (int played = 1; played <= ROUNDS; played++) {
			System.out.printf("Round %d of %d rounds.\n\n", played, ROUNDS);
			playRound(player1, p1Dice);
			playRound(player2, p2Dice);
		}
	}
	
	/**	Method where each round of the game  is played. The player rolls 
	 *  their dice a max of 3 times and chooses which dice to hold each 
	 *  time, then they choose which category to put their score in. 
	 */
	public void playRound(YahtzeePlayer player, DiceGroup dg) {
			System.out.printf("%s, it's your turn to play. Please hit " +
				"enter to roll the dice :\n\n", player.getName());
			while (!readWait.hasNextLine()) {}
			readWait.nextLine();
			dg.rollDice();
			dg.printDice();
			for (int i = 0; i < MAX_HOLD_ROUNDS; i++) {
				String hold = "";
				hold = Prompt.getString("Which di(c)e would you like to keep? " +
					"Enter the values you'd like to 'hold' without spaces. " +
					"For examples, if you'd like to 'hold' die 1, 2, and 5, " +
					"enter 125\n(enter -1 if you'd like to end the turn)\n");
				if (Integer.parseInt(hold) != -1) {
					dg.rollDice(hold);
					dg.printDice();
				}
			}
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	/** Totals players' scores to figure out who won, and tells them.
	 */
	//public void winner() {
		
	//}
}
