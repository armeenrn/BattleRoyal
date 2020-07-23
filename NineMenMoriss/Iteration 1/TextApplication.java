/**
 * @author daniel
 */

import java.util.Scanner;
//random comment test

public class TextApplication {
	private Game gameToPlay;
	
	public TextApplication() {
		
	}
	
	public TextApplication(Game game) {
		gameToPlay = game;
		
	}
	
	public void run() {
		Game game;
		String next = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Nine-men Morris!");
		System.out.println("Please enter one of the following options:");
		System.out.println("PLAY, QUIT");
		next = input.nextLine();		
		String names[] = new String[2];
		names[0] = "alpha";
		names[1] = "beta";
		int mode = 0;
	
		do {
			if (next.equalsIgnoreCase("PLAY")) {
				game = new Game(names, mode);
				TextApplication app = new TextApplication(game);
				app.gameToPlay.play(); // one game is played
				
				// game is over
				System.out.println("Thank you for playing. Enter PLAY if you want to play again,"
						+ " or QUIT to quit the game.");				
			}
			else {
				System.out.println("Invalid input. Please try again:");				
			}
			
			next = input.nextLine();
		} while (!next.equalsIgnoreCase("QUIT"));
	}
		
	
	public static void main(String[] args) {
		
		TextApplication initial = new TextApplication();
		initial.run();
	}	


}
