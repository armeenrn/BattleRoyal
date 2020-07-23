import java.util.Scanner;
//random comment test

public class TextApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		Game game;
		
		System.out.println("Welcome to Nine-men Morris!");
		System.out.println("Please enter one of the following options:");
		System.out.println("PLAY, QUIT");
		
		String names[] = new String[2];
		names[0] = "alpha";
		names[1] = "beta";
		int mode = 0;
		String next = input.nextLine();
		
		do {
			if (next.equalsIgnoreCase("PLAY")) {
				game = new Game(names, mode);
				game.play(); // one game is played
				
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

}
