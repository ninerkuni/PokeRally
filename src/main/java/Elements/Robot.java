package Elements;


import Board.Board;


public class Robot extends Element {
	private String name;
	private int score;
	private Coordinates start; 
	private int checkCount;
	private Board board;
	

	private int orientation;

	private String signature;


	

	
	public Robot(String name, Board board) {
		this.name = name;
		this.board = board;
//		direction = Direction.N;
		orientation = 0;
//		direction = Dir.get(0);
		score = 0;
	}
	
	public Robot(Board board) {
		this.board = board;
//		direction = Direction.N;
		orientation = 0;
//		direction = Dir.get(0);
		score = 0;
	}
	
	public void reset() {
//		direction = Direction.N;
		orientation = 0;
		score = 0;
		System.out.print("Move to start ");
		start.print();
		move(start);
	}
	
	public void setStart(int x, int y) {
		start = new Coordinates(x,y);
		coordinates = new Coordinates(x,y);
	}
	
	public Coordinates getStart() {
		return start;
	}

	
	public int getNumCheckpoints() {
		return board.getCheck();
	}
	
	boolean possibleMove(int x, int y) {
		int bound = board.getDimensions();
		return (x < bound && x >= 0 && y < bound && y >= 0);
		
		}
	
	public int next_x() {
		int x = coordinates.getx();
		System.out.println("next x:"+(x-((orientation-2)%2)));
		return x-((orientation-2)%2);

	}
	
	public int next_y() {
		int y = coordinates.gety();
		System.out.println("next y:"+(y+((orientation-1)%2)));
		return y+((orientation-1)%2);
	}
	

	

	public int getCheckCount() {
		return checkCount;
	}
	
	public void setCheckCount(int c) {
		checkCount = c;
	}
	




	public String hit(Element e) {
		score += e.effect(score);
		System.out.println("Element hit"+ e.message());
		e.move(this);
		return e.message();
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int i) {
		score = i;
	}
	
	//the move method, initiates the process of moving a robot
	public boolean moved() {
		return !move(new Coordinates(next_x(),next_y()));
	}
	
	//move from the element class, could be used to make one robot push another
	@Override
	public void move(Robot robot) {
		Coordinates coordinates = new Coordinates(getCoordinates().getx(),getCoordinates().gety());
		for(int i = 0; i < 2;i++) {
			coordinates.movey(-(robot.getOrientation()-1)%2);
			coordinates.movex((robot.getOrientation()-2)%2);
			robot.move(coordinates);
		}
		
	}
	
	
	//effect when one robot hits another
	public int effect(int x) {
		return -2;
	}
	
	public String turnL() {
		orientation = (orientation+3)%4;
		return "turned left!";
	}
	
	public String turnR() {
		orientation = (orientation+1)%4;
		return "turned right!";
	}
	
	public int getOrientation() {
		return orientation;
	}

	public String message() {
		return signature+(orientation);
	}
//	
	public void setOrientation(int d) {
		orientation = d;
	}

	public boolean winner() {
		if(checkCount == board.getCheck()) return true;
		else return false;
	}

	public boolean move(Coordinates coordinates) {
		System.out.println("Robot.move()");
		if(possibleMove(coordinates.getx(),coordinates.gety())) {
			System.out.println("Possible move:");
			coordinates.print();
			System.out.println("Board is empty at next coordinates: "+board.isEmpty(coordinates));
			if(!board.isEmpty(coordinates)) {
				Element e = board.getElement(coordinates);
				setCoordinates(coordinates.getx(),coordinates.gety());
				System.out.println("Element hit :" +hit(e));
			}
			else{
				setCoordinates(coordinates.getx(),coordinates.gety());
			}

		}
		System.out.println("New coordinates robot:");
		this.coordinates.print();
		return(getCoordinates() == coordinates);
		
	}

	//method used to return the name of the winner
    public String getName() {
		return name;
    }

	//used for loading the game from text file
	public void setSignature(String s) {
		signature = s;
	}


}