

public class Board {
	
	private Square outerSquare;
	private Square midSquare;
	private Square innerSquare;
	private Line northMidLine;
	private Line westMidLine;
	private Line eastMidLine;
	private Line southMidLine;
	
	public Board (Square outerSquare, Square midSquare, Square innerSquare, Line northMidLine, Line westMidLine, Line eastMidLine, Line southMidLine) {
		this.outerSquare = outerSquare;
		this.midSquare = midSquare;
		this.innerSquare = innerSquare;
		this.northMidLine = northMidLine;
		this.westMidLine = westMidLine;
		this.eastMidLine = eastMidLine;
		this.southMidLine = southMidLine;
	}

	public Square[] setUpBoardSquares(){
		Square[] setUp = {outerSquare, midSquare, innerSquare};
		return Square[];
		}
	}
	
	public Line[] setUpBoardLines() {
		Line[] setUp = {northMidLine, westMidLine, eastMidLine, southMidLine};
		return Line[];
	}
}
