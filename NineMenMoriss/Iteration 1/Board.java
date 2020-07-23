
public class Board {
	
	private Square outerSquare;		
	private Square midSquare;		
	private Square innerSquare;	
	
	/* probably won't need these above three, as they're already in squares list */	
	
	private Square[] squares;
	
	public Board(double range, double centerX, double centerY) {
		squares = new Square[4];
		
		outerSquare = new Square("outer", range, centerX - range/2, centerY - range/2);
		squares[0] = outerSquare;
			
		range = range * 0.75;
			
		midSquare = new Square("midSquare", range, centerX - range/2, centerY - range/2);
		squares[1] = midSquare;
			
		range = range * 0.75;
			
		innerSquare = new Square("innersquare", range, centerX - range/2, centerY - range/2);
		squares[2] = innerSquare;
			
		Line northMidLine = new Line("northMidLine", outerSquare.getNorthLine().getMidPoint(), midSquare.getNorthLine().getMidPoint(), innerSquare.getNorthLine().getMidPoint());
		Line westMidLine = new Line("westMidLine", outerSquare.getWestLine().getMidPoint(), midSquare.getWestLine().getMidPoint(), innerSquare.getWestLine().getMidPoint());
		Line eastMidLine = new Line("eastMidLine", outerSquare.getEastLine().getMidPoint(), midSquare.getEastLine().getMidPoint(), innerSquare.getEastLine().getMidPoint());
		Line southMidLine = new Line("southMidLine", outerSquare.getSouthLine().getMidPoint(), midSquare.getSouthLine().getMidPoint(), innerSquare.getSouthLine().getMidPoint());	
			
			
			
		Square pseudoSquare = new Square(northMidLine, westMidLine, eastMidLine, southMidLine);
		squares[3] = pseudoSquare;
					
		}

	public Square[] getSquares(){
		return squares;
	}
	
	
	
	public Point getPointByCoordinates(double xcor, double ycor) {
		Point answer = null;
		for (Square eachSquare : squares) {
			for (Line eachLine : eachSquare.getLines()) {
					for (Point eachPoint : eachLine.getPoints()) {
						if (eachPoint.getX() == xcor && eachPoint.getY() == ycor) {
							answer = eachPoint;
						}
					}
			}
		}
			
		return answer;
	}
		


	public void displayBoard() {
		for (Square eachSquare : squares) {
			for (Line eachLine : eachSquare.getLines()) {
				for (Point eachPoint : eachLine.getPoints()) {
					System.out.println(eachSquare.toString() + ": " + eachLine.toString() + ": " + eachPoint.toString());
				}
			}
		}
	}
			
}
