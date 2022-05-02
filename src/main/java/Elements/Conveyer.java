package Elements;

public class Conveyer extends Obstacle{

	//constructor
	public Conveyer(){
		super();
	}

	//overwritten method, return decrement to score
	public int effect(int score) {
		return -1;
	}

	@Override
	public void move(Robot robot) {
		Coordinates coordinates = new Coordinates(getCoordinates().getx(),getCoordinates().gety());
		int r = (int) (2*Math.random());
		if(r==0){
			for(int i = 0; i<3; i++) {
				coordinates.movex(1);
				robot.move(coordinates);
			}
		}
		else{
			for(int i = 0; i<3; i++) {
				coordinates.movey(1);
				robot.move(coordinates);
			}
		}
	}
	

	public String message() {
		return "Conveyor";
	}
	
	@Override
	public Conveyer construct() {
		return new Conveyer();
	}
	
}
