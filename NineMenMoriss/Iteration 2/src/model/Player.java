package model;

import java.util.ArrayList;

public class Player {
	private int unusedStones;
	private int stonesOnBoard;
	private ArrayList<Stone> stones;
	private int totalStones;
	private int PLACE = 1;
	private int MOVE_ADJACENT = 2;
	private int JUMP = 3;
	private String name;
	private int number;
	
	public Player(String name, int num) {
		unusedStones = 9;
		totalStones = 9;
		stones = new ArrayList<Stone>();
		stonesOnBoard = 0;
		
		for (int index = 0; index < 9; index++) {
			stones.add(new Stone(this));
		}

		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public ArrayList<Stone> getStones() {
		return stones;
	}
	
	public int getNumberOfStonesPlaced() {
		return stonesOnBoard;
	}
	
	public int getnumberOfNewStones() {
		return unusedStones;
	}
	
	public int getNumberOfTotalStones() {
		return totalStones;
	}
	
	public void moveStone(Stone stone, Point point, int moveType) {
		if ((stone == null) && (moveType == PLACE)) {
			setNewStoneInPlace(point);
		}
		
		else if (moveType == MOVE_ADJACENT) {
			moveAdjacent(stone, point);
		}
		
		else if (moveType == JUMP) {
			
		}
	}
	
	
	public void setNewStoneInPlace(Point place) {
		stones.get(unusedStones-1).setLocation(place);
		place.setOccupiedStone(stones.get(unusedStones-1));
		place.setOccupiedPlayer(this);
		unusedStones = unusedStones - 1;
		System.out.println(place.getOccupiedPlayer().toString() + ": New stone placed at: " + place.toString());
		System.out.println("");
	}
	
	
	public void moveAdjacent(Stone stone, Point place) {
		stone.setLocation(place);
		place.setOccupiedStone(stone);
		place.setOccupiedPlayer(this);
		System.out.println(this.toString() + ": stone moved to: " + place.toString());
		System.out.println("");
	}
	
	
	public void removeStone(Stone stoneToBeRemoved) {
		Point pointItIsRemoved = stoneToBeRemoved.getLocation();
		stoneToBeRemoved.setLocation(null);
		pointItIsRemoved.setOccupiedStone(null);
		pointItIsRemoved.setOccupiedStone(null);
		stonesOnBoard = stonesOnBoard - 1;
		totalStones = totalStones - 1;
		System.out.println(this.toString() + "'s stone at " + pointItIsRemoved.toString() + " removed");
		System.out.println("");
	}
	
	
	
	
	
	
	
	
}










//MY OLD code
//import java.util.Scanner;
//public class Player {
//	
//	private String name;
//	
//	private Stone[] stonesRemaining;
//	
//	private Stone[] stonesOnBoard;
//
//	public Player(String name) {
//		this.name = name;
//		stonesRemaining = new Stone[9];
//		
//		for (int index = 0; index < stonesRemaining.length; index++) {
//			stonesRemaining[index] = new Stone();
//		}
//		
//		stonesOnBoard = new Stone[9];
//	}
//	
//	public String getName() {
//		return name;
//	}
//	
//	public Stone[] getStonesRemaining() {
//		
//		return stonesRemaining;
//	}
//	
//	public Stone[] getStonesOnBoard() {
//		
//		return stonesOnBoard;
//	}
//	
//	public Stone chooseANewStone() {
//		Stone result = null;
//	
//		
//		for (int index = 0; index < stonesRemaining.length; index++) {
//			if (stonesRemaining[index] != null) {
//				result = stonesRemaining[index];
//				stonesRemaining[index] = null;
//				index = stonesRemaining.length;
//			}
//		}
//		
//		if (result != null) {
//			addStoneToBoardList(result);
//		}
//		
//		return result;
//	}
//	
//	
//	public Stone chooseExistingStone(int whatToChoose) {
//		return getStonesOnBoard()[whatToChoose];
//	}
// 	
//	
//	public void addStoneToBoardList(Stone newStone) {
//		
//		for (int index = 0; index < stonesOnBoard.length; index++) {
//			if (stonesOnBoard[index] == null) {
//				stonesOnBoard[index] = newStone;
//				index = stonesOnBoard.length;
//			}
//		}
//	}
//	
//	
//}
