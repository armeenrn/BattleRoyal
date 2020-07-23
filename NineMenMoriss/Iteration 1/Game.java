import java.util.ArrayList;
import java.util.Random;

public class Game {
	private double BOARDWIDTH = 30;
	private double CENTER_X = 0;
	private double CENTER_Y = 0;
	private Player player1;
	private Player player2;
	private int goFirst;
	private Board gameBoard;
	
	public Game(String[] playerNames, int mode) {
		Random rand = new Random();
		int goFirst = 0;
		
		while (goFirst == 0) {
			goFirst = rand.nextInt(3); 
		}
		
		if (goFirst == 1) {
			player1 = new HumanPlayer(playerNames[0], 1);
			player2 = new AIPlayer(playerNames[1], 2);	
		}
		
		if (goFirst == 2) {
			player1 = new HumanPlayer(playerNames[1], 1);
			player2 = new AIPlayer(playerNames[0], 2);	
			
		}
		
		gameBoard = new Board(BOARDWIDTH, CENTER_X, CENTER_Y);	
		
		
		
	}
	
	public Board getGameBoard() {
		return gameBoard;
	}
	
	public Player selectFirstPlayer() {
		return player1;
	}
	
	public Player selectSecondPlayer() {
		return player2;
	}

	
	public Line getFilledLine() {
		return null;
	}
	
	
	
	
	
	public int determinePlayerMove(HumanPlayer playerToCheck) {
		if (playerToCheck.getNumberOfStonesRemaining() > 0) {
			return 1;
		}
		
		else {
			return 2;
		}
	}
	
	public Stone checkIfPointIsUnOccupied(Point point) {
		return point.getOccupiedStone();
	}
	
	public void determineHumanPlayerOptions(HumanPlayer player) {
		System.out.println("before move, #lines is :" + getNumberOfLinesFormed(player));
		if (determinePlayerMove(player) == 1) {
			
			Double[] coordinates = player.selectDestination();
			while (checkIfPointIsUnOccupied(gameBoard.getPointByCoordinates(coordinates[0], coordinates[1])) != null) {
				System.out.println("This point is occupied by: " + gameBoard.getPointByCoordinates(coordinates[0], coordinates[1]).getOccupiedPlayer().toString());
				coordinates = player.selectDestination();
			}
			player.moveStone(null, gameBoard.getPointByCoordinates(coordinates[0], coordinates[1]), 1);
		}
		
		else if (determinePlayerMove(player) == 2) {
			displayAvailableStones(player);
			Stone selectedStone = player.selectStoneToMove(getAvailableStonesToMove(player));
			
			Double[] coordinates = player.selectDestination();
			while (checkIfAjdacent(selectedStone, gameBoard.getPointByCoordinates(coordinates[0], coordinates[1])) == false) {
				System.out.println("Invalid Point");
				coordinates = player.selectDestination();
			}
			
			player.moveStone(selectedStone, gameBoard.getPointByCoordinates(coordinates[0], coordinates[1]), 2);
		}
		
		System.out.println("after move, #lines is :" + getNumberOfLinesFormed(player));
	}
	
	
	public void displayStones() {
		for (Stone eachStone : player1.getStones()) {
			if (eachStone.getLocation() != null) {
				System.out.println(eachStone.getOwner().toString() + ": " + eachStone.getLocation().toString());
			}
		}
	}
	
	
	public void displayAvailableStones(HumanPlayer player) {
		int counter = 1;
		for (Stone eachStone : getAvailableStonesToMove(player)) {
			System.out.println(counter + "." + eachStone.getOwner().toString() + ": " + eachStone.getLocation().toString());
			counter++;
		}
	}
	
	public ArrayList<Point> getAdjacentPoints(Stone stoneToMove) {
		ArrayList<Point> adjacentPoints = new ArrayList<Point>();
		for (Square eachSquare: gameBoard.getSquares()) {
			for (Line eachLine : eachSquare.getLines()) {
				for (Point eachPoint : eachLine.getPoints()) {
					if (checkIfAjdacent(stoneToMove, eachPoint) == true) {
						if (adjacentPoints.contains(eachPoint) == false) {
							adjacentPoints.add(eachPoint);
						}
					}
				}
			}
		}
		
		return adjacentPoints;
	}
	
	public void displayAdjacentPoints(Stone stoneToMove) {
		int counter = 1;
		for (Point eachPoint : getAdjacentPoints(stoneToMove)) {
			System.out.println(counter + ". " + eachPoint.toString());
			counter++;
		}
	}
	
	public ArrayList<Stone> getAvailableStonesToMove(HumanPlayer player) {
		ArrayList<Stone> stonesAvailable = new ArrayList<Stone>();
		boolean shouldAdd = true;
		
		for (Stone eachStone : player.getStones()) {
			for (Line eachLine : getFilledLine(player)) {
				if (eachLine.doesLineContain(eachStone) == true) {
					shouldAdd = false;
				}
			}
			
			if (shouldAdd == true) {
				stonesAvailable.add(eachStone);
			}
		}
		
		if (stonesAvailable.size() == 0) {
			stonesAvailable = player.getStones();
		}
		
		return stonesAvailable;
	}
	
	
	public ArrayList<Line> getFilledLine(HumanPlayer player) {
		ArrayList<Line> filledLines = new ArrayList<Line>();
		for (Square eachSquare : gameBoard.getSquares()) {
			for (Line eachLine : eachSquare.getLines()) {
				if (eachLine.getFilled(player) == true) {
					filledLines.add(eachLine);
				}
					
			}
		}
		
		return filledLines;
	}
	
	public int getNumberOfLinesFormed(HumanPlayer player) {
		for (Line eachLine : getFilledLine(player)) {
			System.out.println(eachLine.toString());
		}
		return getFilledLine(player).size();
	}
	
	public boolean checkIfAjdacent(Stone stoneToSelect, Point pointToSelect) {
		boolean answer = false;
		
		if (checkIfPointIsUnOccupied(pointToSelect) == null) {
			for (Square eachSquare : gameBoard.getSquares()) {
				for (Line eachLine : eachSquare.getLines()) {
					if (eachLine.doesLineContain(stoneToSelect) == true) {
						if (eachLine.doesLineContainPoint(pointToSelect) == true) {
							if (eachLine.getEndPoint1() == stoneToSelect.getLocation()) {
								if (eachLine.getMidPoint() == pointToSelect) {
									answer = true;
								}
							}
							
							else if (eachLine.getMidPoint() == stoneToSelect.getLocation()) {
								if ((eachLine.getEndPoint1() == pointToSelect) || (eachLine.getEndPoint2() == pointToSelect)) {
									answer = true;
								}	
							}
							
							else if (eachLine.getEndPoint2() == stoneToSelect.getLocation()) {
								if (eachLine.getMidPoint() == pointToSelect) {
									answer = true;
								}	
							}
						}
					}
				}
			}
		}
		
		return answer;
	}
	
	public void play() {
		if (goFirst == 1) {
			turn(player1);
		}
		else {
			turn(player2);
		}
	}
	
	public void turn(Player currentPlayer) {
		
	}
}
