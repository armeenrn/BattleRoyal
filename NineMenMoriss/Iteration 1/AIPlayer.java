import java.util.ArrayList;

public class AIPlayer extends Player {
	private int movePriority;
	private final static int NONE = 0;
	private final static int FILL_LINE_THISTURN = 1;
	private final static int STOP_OPPONENT_NEXTTURN = 2;
	private final static int TWO_STEPS_TO_FILL = 3;
	private Point pointToMoveTo;
	
	public AIPlayer(String name, int num) {
		super(name, num);
	}
	
	public void checkPriorityMove(Board board) {
	}
		
	public int checkMovement(Board board, int squareSize, int lineDirection, int pointLocInLine) {
		return 0;
	}
	
	public boolean checkFill(Board board, int squareSize, int lineDirection, int pointLocInLine) {
		return true;
	}
	
	public boolean checkStopFill(Board board, int squareSize, int lineDirection, int pointLocInLine) {
		return true;
	}
	
	public boolean checkGoForFill(Board board, int squareSize, int lineDirection, int pointLocInLine) {
		return true;
	}
	
	public void checkRemove() {
		
	}
}
