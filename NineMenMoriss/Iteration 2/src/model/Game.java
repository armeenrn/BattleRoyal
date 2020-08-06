package model;

import java.util.ArrayList;

public class Game {
	private int winner = 0;
	private GameShared textGameConfig;
	
	public Game() {		
		textGameConfig = new GameShared();
		textGameConfig.setGameConfig();
	}
	
	public void play() {
		int firstTurn = textGameConfig.getFirstPlayer();
		HumanPlayer player1 = textGameConfig.selectFirstPlayer();
		AIPlayer player2 = textGameConfig.selectSecondPlayer();
		
		if (firstTurn == 1) {
			System.out.println("You go first." + "\n");
		}
		
		else {
			System.out.println("The computer goes first" + "\n");
		}
		
		while (winner == 0) {
			if (firstTurn == 1) {
				System.out.println("Your turn" + "\n");
				turnHumanPlayer(player1);
				if (winner == 0) {
					System.out.println("The computer's turn" + "\n");
					turnComputer(player2);	
					displayScore();
				}
				
				else {
					break;
				}
			}
			else {
				System.out.println("The computer's turn" + "\n");
				turnComputer(player2);	

				if (winner == 0) {
					System.out.println("Your turn" + "\n");
					turnHumanPlayer(player1);
					displayScore();
				}
				
				else {
					break;
				}
			}			
		}
	}
	
	
	public void displayScore() {
		System.out.println("Your score: " + "\n" + textGameConfig.selectFirstPlayer().getNumberOfTotalStones() + " stones on board");
		System.out.println(textGameConfig.getFilledLine(textGameConfig.selectFirstPlayer()).size() + " lines formed");
		System.out.println(textGameConfig.selectFirstPlayer().getNumberOfTotalStones() + " stones remaining." + "\n");
		System.out.println("Computer score: " + "\n" + textGameConfig.selectSecondPlayer().getNumberOfTotalStones() + " stones on board");
		System.out.println(textGameConfig.getFilledLine(textGameConfig.selectSecondPlayer()).size() + " lines formed");
		System.out.println(textGameConfig.selectSecondPlayer().getNumberOfTotalStones() + " stones remaining." + "\n");
	}		
	
	public void turnHumanPlayer(HumanPlayer humanPlayer) {
		displayPointsAsList();
		int filled_Lines_At_Start_Of_Turn = textGameConfig.getFilledLine(humanPlayer).size();
		
		if (humanPlayer.getNumberOfPlacedStones() < 9) {
			Point userPoint = humanPlayer.selectDestination(textGameConfig.getPointsAsList());
			while (textGameConfig.checkIfPointIsUnOccupied(userPoint) != 0) {
				System.out.println("Another user's stone is on this point. Choose another point." + "\n");
				userPoint = humanPlayer.selectDestination(textGameConfig.getPointsAsList());
			}
			
			textGameConfig.moveStone(textGameConfig.selectFirstPlayer(), null, userPoint);
			System.out.println("Player " + textGameConfig.selectFirstPlayer().getPlayerNumber() + ": New stone placed at: " + userPoint.toString());
			System.out.println("");			
		}
		
		else if (humanPlayer.getNumberOfPlacedStones() == 0) {
			displayFreeStones(humanPlayer);
			Stone selectedStone = humanPlayer.selectStoneToMove(textGameConfig.getFreeStones(humanPlayer));
			displayAdjacentPoints(selectedStone);
			Point userPoint = humanPlayer.selectDestination(textGameConfig.getAdjacentPoints(selectedStone));
			while (textGameConfig.checkIfAjdacent(selectedStone, userPoint) == false) {
				System.out.println("Invalid Point. Choose adjcaent point." + "\n");
				userPoint = humanPlayer.selectDestination(textGameConfig.getPointsAsList());
			}
			
			textGameConfig.moveStone(humanPlayer, selectedStone, userPoint);
			System.out.println(this.toString() + ": stone moved to: " + userPoint.toString());
			System.out.println("");
		}
		
		int filled_Lines_At_End_Of_Turn = textGameConfig.getFilledLine(humanPlayer).size();
		
		if (filled_Lines_At_End_Of_Turn > filled_Lines_At_Start_Of_Turn) {
			System.out.println("You formed a new line at: " );
			System.out.print(textGameConfig.getFilledLine(humanPlayer).get(filled_Lines_At_End_Of_Turn-1));
			System.out.println("You get to remove a stone on the board from the computer." + "\n");
			displayStonesOfOpponent(textGameConfig.selectSecondPlayer());
			Stone removeStone = humanPlayer.selectStoneToRemove(textGameConfig.getStonesOfOpponent(textGameConfig.selectSecondPlayer()));
			textGameConfig.removeStone(textGameConfig.selectSecondPlayer(), removeStone);
			System.out.println(textGameConfig.selectSecondPlayer().toString() + "'s stone removed");
			System.out.println("");
		}
		
		// check AI's number of stones at the end to see if you win
		if (textGameConfig.selectSecondPlayer().getNumberOfTotalStones() <= 3) {
			winner = 1;
			System.out.println("You won");
		}
	}

//	public void turnAIPlayer(AIPlayer compPlayer) {
//		if (compPlayer.getnumberOfNewStones() > 0) {
//			compPlayer.placeStone(player2.getStones().get(player2.getStonesPlaced()), gameBoard);
//		}
//		else {
//			if (compPlayer.getNumberOfStonesRemaining() <= 3) {
//				// jump
//				compPlayer.placeStone(player2.getStones().get(player2.getStonesPlaced()), gameBoard);
//			}
//			else {
//				// move adjacent
//			}
//		}
//		// check human player's number of stones at the end to see if you win
//		if (player1.getNumberOfStonesRemaining() < 3) {
//			winner = 2;
//		}
//	}
	
	public void turnComputer(AIPlayer compPlayer) {
		displayPointsAsList();
		ArrayList<Line> filled_Lines_At_Start_Of_Turn = textGameConfig.getFilledLine(compPlayer);
		Point movePoint = compPlayer.lookForBestMove(textGameConfig.getGameBoard());
		Stone moveStone;
		
		if (compPlayer.getNumberOfPlacedStones() < 9) {
			if (movePoint == null) {
				do {
					movePoint = compPlayer.getRandomPoint(textGameConfig.getPointsAsList());					
				} while (textGameConfig.checkIfPointIsUnOccupied(movePoint) != 0);
			}
			
			textGameConfig.moveStone(compPlayer, null, movePoint);			
		}		
		else if (compPlayer.getNumberOfTotalStones() > 3) {
			
			if (movePoint == null) {
				Stone randomStone;
				
				do {
					randomStone = compPlayer.selectRandomStone(textGameConfig.getFreeStones(compPlayer));
					movePoint = compPlayer.getRandomPoint(textGameConfig.getPointsAsList());					
				} while (textGameConfig.checkIfAjdacent(randomStone, movePoint) == false);

				moveStone = randomStone;
			}
			else {
				moveStone = compPlayer.getBestMoveStone();
			}
			
			textGameConfig.moveStone(compPlayer, moveStone, movePoint);
		}
		else {
			if (movePoint == null) {
				Stone randomStone;
				
				do {
					randomStone = compPlayer.selectRandomStone(textGameConfig.getFreeStones(compPlayer));
					movePoint = compPlayer.getRandomPoint(textGameConfig.getPointsAsList());
				} while (movePoint.getOccupiedPlayer() != 0);
				
				moveStone = randomStone;
			}
			else {
				moveStone = compPlayer.getBestMoveStone();
			}

			textGameConfig.moveStone(compPlayer, moveStone, movePoint);
		}
		
		ArrayList<Line> filled_Lines_At_End_Of_Turn = textGameConfig.getFilledLine(compPlayer);
		
		if ((!(filled_Lines_At_End_Of_Turn.equals(filled_Lines_At_End_Of_Turn))) && filled_Lines_At_End_Of_Turn.size() >= filled_Lines_At_End_Of_Turn.size()) {
			System.out.println("The computer formed a new line at: " );
			System.out.print(textGameConfig.getFilledLine(compPlayer).get(filled_Lines_At_End_Of_Turn.size() - 1));
			System.out.println("The computer removes one of your stones on the board." + "\n");
			Stone removeStone = compPlayer.lookforBestRemove(textGameConfig.getGameBoard(), textGameConfig.selectFirstPlayer());
			textGameConfig.removeStone(textGameConfig.selectFirstPlayer(), removeStone);
		}
		
		// check AI's number of stones at the end to see if you win
		if (textGameConfig.selectFirstPlayer().getNumberOfTotalStones() <= 3) {
			winner = 2;
			System.out.println("The computer won.");
		}
	}
	
	
	
	
	
	public void displayPointsAsList() {
		System.out.println("GAME BOARD");
		int counter = 0;
		for (Point eachPoint : textGameConfig.getPointsAsList()) {
			String write = "";
			write = write + counter + eachPoint.toString() + " ";
			if (eachPoint.getOccupiedPlayer() != 0) {
				write = write + "Player " + eachPoint.getOccupiedPlayer() + " X";
			}
			System.out.println(write);
 			counter = counter + 1;
		}
		
		System.out.println("");
	}
			
	public void displayAdjacentPoints(Stone stoneToMove) {
		System.out.println("Adjacent Points Available");
		int counter = 1;
		for (Point eachPoint : textGameConfig.getAdjacentPoints(stoneToMove)) {
			System.out.println(counter + ". " + eachPoint.toString());
			counter++;
		}
		
		System.out.println("");
	}
	
	public void displayStonesOfOpponent(Player opponent) {
		int counter = 0;
		System.out.println("Opponent Stones that can be removed");
		for (Stone eachStone : textGameConfig.getStonesOfOpponent(opponent)) {
			System.out.println(counter + ". stone at: " + eachStone.getLocation().toString());
			counter++;
		}
		System.out.println("");
	}
	
	public void displayStones() {
		for (Stone eachStone : textGameConfig.selectFirstPlayer().getStones()) {
			if (eachStone.getLocation() != null) {
				System.out.println(eachStone.getOwner().toString() + ": " + eachStone.getLocation().toString());
			}
		}
	}
	
	public void displayFreeStones(Player player) {
		System.out.println("Stones that can be removed");
		int counter = 0;
		
		for (Stone eachStone : textGameConfig.getFreeStones(player)) {
			System.out.println(counter + ". " + eachStone.getOwner().toString() + ": " + eachStone.getLocation().toString());
			counter++;
		}
		
		System.out.println("");
	}
}
