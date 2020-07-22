import java.util.ArrayList;

public class Player {
	
	private ArrayList<Stone> stones;
	private int numberOfStonesRemaining;
	private int PLACE = 1;
	private int MOVE_ADJACENT = 2;
	private int JUMP = 3;
	
	public Player() {
		stones = new ArrayList<Stone>();	
		numberOfStonesRemaining = 9;
		stones = new ArrayList<Stone>();
		
		for (int index = 0; index < 9; index++) {
			stones.add(new Stone(this));
		}
	}
	
	public ArrayList<Stone> getStones() {
		return stones;
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
	
	
	public int getNumberOfStonesRemaining() {
		return numberOfStonesRemaining;
	}
	
	public void setNewStoneInPlace(Point place) {
		stones.get(numberOfStonesRemaining-1).setLocation(place);
		place.setOccupiedStone(stones.get(numberOfStonesRemaining-1));
		System.out.println("about to set stone to this player: " + this.toString());
		place.setOccupiedPlayer(this);
		numberOfStonesRemaining = numberOfStonesRemaining - 1;
		
	}
	
	
	public void moveAdjacent(Stone stone, Point place) {
		stone.setLocation(place);
		place.setOccupiedStone(stone);
		place.setOccupiedPlayer(this);
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
