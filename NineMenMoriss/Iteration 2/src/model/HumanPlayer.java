package model;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player {
	
	private String name;
	
	public HumanPlayer(String name, int num, boolean goFirst) {
		super(name, num, goFirst);
		this.name = name;
	}
	
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
	
	public String toString() {
		return name;
	}
	
}
