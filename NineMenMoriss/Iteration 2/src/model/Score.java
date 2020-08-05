package model;

import java.util.ArrayList;

public class Score extends Game {
	public Score(String[] playerNames, int mode) {
		super(playerNames, mode);
		// TODO Auto-generated constructor stub
	}

	private static int matchScore1 = 0;
	private static int matchScore2 = 0;
	private static int gameScore = 0;
	// private ArrayList<String> playerScore;
	// private Line eachLine;
	private Game matchNum;

	public int totalScoreOfEachMatch(HumanPlayer player1) {
		
		//scored based on number of filled lines throughout match
		if (matchNum.getWinner() != 0 && matchNum.getFilledLine(player1).isEmpty() == false) {
			int totalLines = matchNum.getFilledLine(player1).size();
			matchScore1 = totalLines * 5;
		}
		//score based on stones remaining 
		int remainingStones = player1.getNumberOfTotalStones();
		matchScore1 = remainingStones * 2;
		
		//score based on winning game
		if (getWinner() == 1) {
			matchScore1 += 10;
		}
		return matchScore1;
	}
	
	public int totalScoreOfEachMatch(AIPlayer player2) {
		
		//scored based on number of filled lines throughout match
		if (matchNum.getWinner() != 0 && matchNum.getFilledLine(player2).isEmpty() == false) {
			int totalLines = matchNum.getFilledLine(player2).size();
			matchScore2 = totalLines * 5;
		}
		//score based on stones remaining 
		int remainingStones = player2.getNumberOfTotalStones();
		matchScore2 = remainingStones * 2;
		
		//score based on winning game
		if (getWinner() == 2) {
			matchScore2 += 10;
		}
		return matchScore2;
	}

}
//display matchScore1 and matchScore1 in two textFields or texts after one of the players has won the game 
// if quit current game is pressed, create new object of Game to start new match.
//store game score on main scene 
//call totalScoreOfEachMatch method for both players after play button click
// if quit game pressed, clear gameScore and previous matcheScores. 
// After three matches declare final winner and prompt to quit game, nothing else is pressable. 