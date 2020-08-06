package model;

import java.util.ArrayList;

public class Score extends Game {
	public Score(String[] playerNames, int mode) {
		super(playerNames, mode);
		// TODO Auto-generated constructor stub
	}

	private static int matchScore = 0;
	//private static int matchScore2 = 0;
	private static int gameScore = 0;
	// private ArrayList<String> playerScore;
	// private Line eachLine;
	//private Game matchNum;

	public int totalScoreOfEachMatch(Player player) {
		
		//scored based on number of filled lines throughout match
		if (this.getWinner() != 0 && this.getFilledLine(player).isEmpty() == false) {
			int totalLines = this.getFilledLine(player).size();
			matchScore = totalLines * 5;
		}
		//score based on stones remaining 
		int remainingStones = player.getNumberOfStonesPlaced();
		matchScore = remainingStones * 2;
		
		//score based on winning game
		if (getWinner() == 1) {
			matchScore += 10;
		}
		gameScore += matchScore;
		return matchScore;
	}

	public int totalGameScore (Player player) {
		return gameScore;
		
	}
}
//display matchScore1 and matchScore1 in two textFields or texts after one of the players has won the game 
//if quit current game is pressed, create new object of Game to start new match.
//store game score on main scene 
//call totalScoreOfEachMatch method for both players after play button click
// if quit game pressed, clear gameScore and previous matcheScores. 
// After three matches declare final winner and prompt to quit game, nothing else is pressable. 