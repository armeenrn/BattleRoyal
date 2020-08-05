package application;

import java.awt.desktop.SystemEventListener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.TextFlow;
import model.Player;
import model.Point;
import model.Stone;

public class GameController {
	private Point pointSelected = null;
	private String[] names = {"A", "B"};
	private GameGUI gameToPlay = new GameGUI(names, 1, this);
	
	@FXML
	private TextField myText;
	
	@FXML
	private Ellipse aiRock1;
	
	@FXML
	private Ellipse aiRock2;
	
	@FXML
	private Ellipse aiRock3;
	
	@FXML
	private Ellipse aiRock4;
	
	@FXML
	private Ellipse aiRock5;
	
	@FXML
	private Ellipse aiRock6;
	
	@FXML
	private Ellipse aiRock7;
	
	@FXML
	private Ellipse aiRock8;
	
	@FXML
	private Ellipse aiRock9;
	
	@FXML
	private Ellipse humanRock1;
	
	@FXML
	private Ellipse humanRock2;
	
	@FXML
	private Ellipse humanRock3;
	
	@FXML
	private Ellipse humanRock4;
	
	@FXML
	private Ellipse humanRock5;
	
	@FXML
	private Ellipse humanRock6;
	
	@FXML
	private Ellipse humanRock7;
	
	@FXML
	private Ellipse humanRock8;
	
	@FXML
	private Ellipse humanRock9;
	
	
	@FXML
	private Button myStart;
	
    @FXML
    private Circle point8;

    @FXML
    private Circle point9;

    @FXML
    private Circle point4;

    @FXML
    private Circle point5;

    @FXML
    private Circle point6;

    @FXML
    private Circle point7;

    @FXML
    private Circle point24;

    @FXML
    private Circle point23;

    @FXML
    private Circle point2;

    @FXML
    private Circle point22;

    @FXML
    private Circle point3;

    @FXML
    private Circle point21;

    @FXML
    private Circle point20;

    @FXML
    private Button rulesButton;

    @FXML
    private TextFlow myTextFlow;

    @FXML
    private Circle point19;

    @FXML
    private Circle point18;

    @FXML
    private Circle point17;

    @FXML
    private Circle point16;

    @FXML
    private Circle point15;

    @FXML
    private Circle point14;

    @FXML
    private Circle point13;

    @FXML
    private Circle point12;

    @FXML
    private Circle point11;

    @FXML
    private Circle point10;

    @FXML
    private Button exitButton;

    @FXML
    private Circle pointOne;
    
    private Ellipse[] humanPlayerRocks = new Ellipse[9];
    
    private Ellipse[] aiPlayerRocks = new Ellipse[9];
    
    private Circle[] listOfCircles = new Circle[24];
	
	private boolean preventClick = false;

	
	public GameController() {
		
	}
	
	public TextField getMyText() {
		return myText;
	}
	
    @FXML
    void exitCurrentGame(ActionEvent event) {

    }

    @FXML
    void displayRules(ActionEvent event) {

    }

    @FXML
    void clickedCircle(MouseEvent event) {
    	if (preventClick == false) {

    		setCirclesToPoints();
    		Circle source = (Circle) event.getSource();
    		
        	gameToPlay.seSelectedPoint(getPointFromCircle(source));
        		
    	}
    	
    }
    
    
    public void setPreventClick(boolean value) {
    	preventClick = value;
    }

    public Point getPointFromCircle(Circle sourceCircle) {
    	Point pointToReturn = null;
    	for (Point eachPoint : gameToPlay.getPointsAsList()) {
    		if (eachPoint.getCircle() == sourceCircle) {
    			
    			pointToReturn = eachPoint;
    		}
    	}
    	
    	return pointToReturn;
    }
	
    
    @FXML
    void startGame(ActionEvent event) {
    	gameToPlay.play();
    	
    }

	
	public void turn() {
		System.out.println("Select a point on the board");
	}
	
	public void setCirclesToPoints() {
		setCircleList();

		int index = 0;
		for (Point eachPoint : gameToPlay.getPointsAsList()) {
			
			eachPoint.setCircle(listOfCircles[index]);
			
			index++;
		}
	}
	
	public void setCircleList() {
		listOfCircles[0] = pointOne;
		listOfCircles[1] = point2;
		listOfCircles[2] = point3;
		listOfCircles[3] = point4;
		listOfCircles[4] = point5;
		listOfCircles[5] = point6;
		listOfCircles[6] = point7;
		listOfCircles[7] = point8;
		listOfCircles[8] = point9;
		listOfCircles[9] = point10;
		listOfCircles[10] = point11;
		listOfCircles[11] = point12;
		listOfCircles[12] = point13;
		listOfCircles[13] = point14;
		listOfCircles[14] = point15;
		listOfCircles[15] = point16;
		listOfCircles[16] = point17;
		listOfCircles[17] = point18;
		listOfCircles[18] = point19;
		listOfCircles[19] = point20;
		listOfCircles[20] = point21;
		listOfCircles[21] = point22;
		listOfCircles[22] = point23;
		listOfCircles[23] = point24;
				
	}
	
	public void setHumanRocksList() {
		humanPlayerRocks[0] = humanRock1;
		humanPlayerRocks[1] = humanRock2;
		humanPlayerRocks[2] = humanRock3;
		humanPlayerRocks[3] = humanRock4;
		humanPlayerRocks[4] = humanRock5;
		humanPlayerRocks[5] = humanRock6;
		humanPlayerRocks[6] = humanRock7;
		humanPlayerRocks[7] = humanRock8;
		humanPlayerRocks[8] = humanRock9;
		
	}
	
	public void setHumanStonesToEllipses() {
		setHumanRocksList();
	
		int index = 0;
		for (Stone eachStone : gameToPlay.selectHumanPlayer().getStones()) {
			eachStone.setEllipse(humanPlayerRocks[index]);
			index++;
		}
	}
	
	public void moveRockToPoint(Stone stone) {
		setHumanRocksList();
		setAIRocksList();
		setHumanStonesToEllipses();
		setAIStonesToEllipses();
		
		stone.getEllipse().setLayoutX(stone.getLocation().getCircle().getLayoutX());
		stone.getEllipse().setLayoutY(stone.getLocation().getCircle().getLayoutY());
	}

	public void setAIRocksList() {
		aiPlayerRocks[0] = aiRock1;
		aiPlayerRocks[1] = aiRock2;
		aiPlayerRocks[2] = aiRock3;
		aiPlayerRocks[3] = aiRock4;
		aiPlayerRocks[4] = aiRock5;
		aiPlayerRocks[5] = aiRock6;
		aiPlayerRocks[6] = aiRock7;
		aiPlayerRocks[7] = aiRock8;
		aiPlayerRocks[8] = aiRock9;
		
	}
	
	public void setAIStonesToEllipses() {
		setAIRocksList();
		int index = 0;
		for (Stone eachStone : gameToPlay.selectAIPlayer().getStones()) {
			eachStone.setEllipse(aiPlayerRocks[index]);
			index++;
		}
	}
	
    
    

}
