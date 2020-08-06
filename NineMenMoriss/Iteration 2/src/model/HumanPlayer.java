/*
 * HumanPlayer represents the human playing the game
 */

package model;

import java.util.ArrayList;
import java.util.Scanner;

/** 
 * public class HumanPlayer sets human player moves in the text version of the game 
 * 
 * 	10.00 6 August 2020
 * 	@author Armeen Rashidian
 * 	Team D
 */

public class HumanPlayer extends Player {
	private String name;	// the name of the player
	
	/**
	 * Constructor creats an object of HumanPlayer as an extension of the PlayerClass
	 * 	@param name, the name of the player
	 * 	@param num, the number associated with the player
	 * 	@param goFirst, a boolean, true if the player goes first
	 * 	@param gameBoard, the board created
	 */
	public HumanPlayer(String name, int num, boolean goFirst, Board gameBoard) {
		super(name, num, goFirst, gameBoard);
		this.name = name;
	}
	
	/**
	 * selectStoneToMove prompts the player to move a stone on the board from a list of free stones that can be moved
	 * 	@param stonesToSelect, the player's free stones that can be moved 
	 * 	@return the stone that the player selected to move
	 */
	public Stone selectStoneToMove(ArrayList<Stone> stonesToSelect) {
		int index;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the index of the stone you wish to select.");
		index = input.nextInt();
		
		while (index < 0 || index >= stonesToSelect.size()) {
			System.out.println("Invalid stone. Enter the index of the stone you wish to move");
			index = input.nextInt();
		}
		
		return stonesToSelect.get(index);
	}
	
	/**
	 * selectDestination prompts the player to choose a point on the board on which they want to move or place a stone
	 * 	@param pointsList, the list of points that the player will choose from
	 * 	@return the point that has been selected
	 */
	public Point selectDestination(ArrayList<Point> pointsList) {
		int index = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the index of the point you wish to choose.");
		index = input.nextInt();
		
		while (index < 0 || index >= pointsList.size()) {
			System.out.println("Invalid point. Enter the index of the point you wish to choose");
			index = input.nextInt();
		}
		
		return pointsList.get(index);
	}
	
	/**
	 * selectStoneToRemove promopts the player for a stone from the opponent player to be removed from the board
	 * 	@param stonesOfOpponent, the list of the opponent stones on the board that the player chooses from
	 * 	@return the stone selected to be moved
	 */
	public Stone selectStoneToRemove(ArrayList<Stone> stonesOfOpponent) {
		int index;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the index of the stone you wish to remove.");
		index = input.nextInt();
		
		while (index < 0 || index >= stonesOfOpponent.size()) {
			System.out.println("Invalid stone. Enter the index of the stone you wish to remove.");
			index = input.nextInt();
		}
		
		return stonesOfOpponent.get(index);
	}
	
	/**
	 * toString returns the name of the player
	 * @return name, the name of the player
	 */ 
	public String toString() {
		return name;
	}
	
}
