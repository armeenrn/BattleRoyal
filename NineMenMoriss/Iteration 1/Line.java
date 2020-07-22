public class Line {
	private String name;
	private Point endPoint1;
	private Point midPoint;
	private Point endPoint2;
	private boolean isFilled;
	private Point[] points;
	
	public Line(String name, Point endPoint1, Point midPoint, Point endPoint2) {
		this.name = name;
		isFilled = false;
		points = new Point[3];
		
		this.endPoint1 = endPoint1;
		points[0] = this.endPoint1;
		this.midPoint = midPoint;
		points[1] = this.midPoint;
		this.endPoint2 = endPoint2;
		points[2] = this.endPoint2;
	}
	
	public String toString() {
		return name;
	}
	
	public Point[] getPoints() {
		return points;
	}
	
	public Point getEndPoint1() {
		return endPoint1;
	}
	
	public Point getEndPoint2() {
		return endPoint2;
	}
	
	public Point getMidPoint() {
		return midPoint;
	}
	
	public boolean getFilled(Player player) {		
		if (endPoint1.getOccupiedPlayer() != null) {
			if (endPoint1.getOccupiedPlayer() == midPoint.getOccupiedPlayer()) {
				if (midPoint.getOccupiedPlayer() == endPoint2.getOccupiedPlayer()) {
					return true;
				}
				
				else {
					return false;
				}
			}
			
			else {
				return false;
			}		
		}
		
		else {
			return false;
		}
	
	}

	
	public boolean doesLineContain(Stone stoneToCheck) {
		boolean contains = false;
		
		if (endPoint1.getOccupiedStone() == stoneToCheck) {
			contains = true;
		}
		
		else if (midPoint.getOccupiedStone() == stoneToCheck) {
			contains = true;
		}
		
		else if (endPoint2.getOccupiedStone() == stoneToCheck) {
			contains = true;
		}
		
		return contains;
	}
	
	public boolean doesLineContainPoint(Point pointToCheck) {
		boolean contains = false;
		
		if (endPoint1 == pointToCheck) {
			contains = true;
		}
		
		else if (midPoint == pointToCheck) {
			contains = true;
		}
		
		else if (endPoint2 == pointToCheck) {
			contains = true;
		}
		
		return contains;
	}
	
	
	public void setFilled(boolean filled) {
		isFilled = filled;
	}
	
}





//
//public class Line {
//	private Slot[] slots;
//	
//	public Line(Slot slot1, Slot slot2, Slot slot3) {
//		
//		slots = new Slot[3];
//		
//		slots[0] = slot1;
//		slots[1] = slot2;
//		slots[2] = slot3;
//	}
//	
//	public Slot[] getSlots() {
//		return slots;
//	}
//	
//	public boolean isInLine(Slot slotToCheck) {
//		boolean answer = false;
//		for (Slot eachSlot : slots) {
//			
//			if (eachSlot == slotToCheck) {
//				answer = true;
//			}
//		}
//		
//		return answer;
//	}
//	
//	public String toString() {
//		String result = "";
//		for (Slot eachSlot : slots) {
//			result = result + eachSlot.toString();
//		}
//		
//		return result;
//	}
//}
