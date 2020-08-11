/**
 * Has all the text prompts for the game
 * 
 * @author armeen rashidian
 * @version 1.0
 * 10.00 6 August 2020
 * Team D
 */
package application;

import java.util.Scanner;
import model.Game;

public class TextApplication {
	/**
	 * Once the game is run, it will run for one game and these are all the prompts
	 * for the rule window and play window depending on the events that occur during
	 * the game.
	 */
	public void run() {
		Game game;
		String next = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Nine-men Morris!");
		System.out.println("Please enter one of the following options:");
		System.out.println("PLAY, RULES, QUIT");
		next = input.nextLine();

		do {
			if (next.equalsIgnoreCase("PLAY")) {
				game = new Game();
				game.play(); // one game is played

				// game is over
				System.out.println(
						"Thank you for playing. Enter PLAY if you want to play again," + " or QUIT to quit the game.");
			} else if (next.equalsIgnoreCase("RULES")) {
				System.out.println("HOW TO PLAY\n********************");
				System.out.println("Stage 1: PLACE :");
				System.out.println("1. Place one stone at any vacant point on the board per turn.");
				System.out.println("2. Repeat step 1 until you place all the stones in your possession on the board.");
				System.out.println("Stage 2: MOVE :");
				System.out.println("1. Move one stone to an adjacent vacant space per turn.");
				System.out.println("Stage 3: JUMP :");
				System.out.println("1. Activates once the player has 3 stones left on the board during Stage 2.");
				System.out.println("2. The player may choose a stone and place it at any vacant point on the board.");
				System.out.println("********************");
				System.out.println("HOW TO WIN\n********************");
				System.out.println("During any stage in the game, you can form a \"mill.\"");
				System.out.println("A \"mill\" is formed by placing 3 stones along one single line on the board.");
				System.out.println(
						"When a mill is formed, the player must remove one of the opponent's stones on the board.");
				System.out.println(
						"The player wins once the opponent has 2 stones left on the board during or after Stage 2.");
				System.out.println("The player also wins if the opponent is not able to make a valid move.");
				System.out.println("********************");
			} else {
				System.out.println("Invalid input. Please try again:");
			}

			next = input.nextLine();
		} while (!next.equalsIgnoreCase("QUIT"));
		input.close();
	}

	/**
	 * A run of the game is initialized.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		TextApplication initial = new TextApplication();
		initial.run();
	}

}
