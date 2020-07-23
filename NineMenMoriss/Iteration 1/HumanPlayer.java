import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player {
	
	private String name;
	private int number;
	
	public HumanPlayer(String name, int num) {
		super(name, num);
	}
	
	public Stone selectStoneToMove(ArrayList<Stone> stonesToSelect) {
		int index;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of the stone you wish to select.");
		index = input.nextInt();
		return stonesToSelect.get(index-1);
	}
	
	public Double[] selectDestination() {
		Double[] coordinates = new Double[2];
		System.out.println("Enter the x-coordinates of the point.");
		Scanner input = new Scanner(System.in);
		coordinates[0] = input.nextDouble();;
		System.out.println("Enter the y-coordinates of the point.");
		coordinates[1] = input.nextDouble();
		return coordinates;
	}
	
	public Point selectRemove(Point point) {
		return point;
	}
	
	public String toString() {
		return name;
	}
	
}
