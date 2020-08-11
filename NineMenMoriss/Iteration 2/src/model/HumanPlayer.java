/*
 * HumanPlayer represents the human playing the game
 */

package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * public class HumanPlayer sets human player moves in the text version of the
 * game
 * 
 * 10.00 6 August 2020
 * 
 * @author Armeen Rashidian Team D
 */

public class HumanPlayer extends Player {
	private String name; // the name of the player

	/**
	 * Constructor creates an object of HumanPlayer as an extension of the
	 * PlayerClass
	 * 
	 * @param name,      the name of the player
	 * @param num,       the number associated with the player
	 * @param goFirst,   a boolean, true if the player goes first
	 * @param gameBoard, the board created
	 * @throws Exception
	 */
	public HumanPlayer(String name, int num, boolean goFirst, Board gameBoard) throws Exception {
		super(name, num, goFirst, gameBoard);
		this.name = name;
	}

	/**
	 * selectStoneToMove prompts the player to move a stone on the board from a list
	 * of free stones that can be moved
	 * 
	 * @param stonesToSelect, the player's free stones that can be moved
	 * @return the stone that the player selected to move
	 */
	public Stone selectStoneToMove(ArrayList<Stone> stonesToSelect) {
		int index;
		index = -1;
		Scanner input = new Scanner(System.in);

		while (index < 0 || index >= stonesToSelect.size()) {
			System.out.println("Enter the index of the stone you wish to select");
			
			try {
				index = input.nextInt();	
				if (index < 0 || index >= stonesToSelect.size()) {
					throw new Exception();
				}
			} catch (InputMismatchException nfe) {
				System.out.println("Error: InputMismatchException. Must enter integer");
				input.next();
					
			} catch (Exception e) {
				System.out.println("Invalid stone. Must enter an index from the list.");
			}	
		}

		return stonesToSelect.get(index);
	}

	/**
	 * selectDestination prompts the player to choose a point on the board on which
	 * they want to move or place a stone
	 * 
	 * @param pointsList, the list of points that the player will choose from
	 * @return the point that has been selected
	 */
	public Point selectDestination(ArrayList<Point> pointsList) {
		int index = -1;
		Scanner input = new Scanner(System.in);

		while (index < 0 || index >= pointsList.size()) {
				System.out.println("Enter the index of the point you wish to choose");
				try {
					index = input.nextInt();	
					if (index < 0 || index >= pointsList.size()) {
						throw new Exception();
					}
				} catch (InputMismatchException nfe) {
					System.out.println("Error: InputMismatchException. Must enter integer");
					index = -1;
					input.next();
					
				} catch (Exception e) {
					System.out.println("Invalid point. Must be a point between index 0 to 23.");
				
				}
				
			}

		return pointsList.get(index);
	}

	/**
	 * selectStoneToRemove prompts the player for a stone from the opponent player
	 * to be removed from the board
	 * 
	 * @param stonesOfOpponent, the list of the opponent stones on the board that
	 *                          the player chooses from
	 * @return the stone selected to be moved
	 */
	public Stone selectStoneToRemove(ArrayList<Stone> stonesOfOpponent) {
		int index;
		index = -1;
		Scanner input = new Scanner(System.in);
		
		while (index < 0 || index >= stonesOfOpponent.size()) {
			System.out.println("Enter the index of the stone you wish to remove.");
			
			try {
				index = input.nextInt();	
				if (index < 0 || index >= stonesOfOpponent.size()) {
					throw new Exception();
				}
			} catch (InputMismatchException nfe) {
				System.out.println("Error: InputMismatchException. Must enter integer");
				input.next();
					
			} catch (Exception e) {
				System.out.println("Invalid stone. Must enter an index from the list.");
			}	
		}
		
		return stonesOfOpponent.get(index);
	}

	/**
	 * toString returns the name of the player
	 * 
	 * @return name, the name of the player
	 */
	public String toString() {
		return name;
	}

}
