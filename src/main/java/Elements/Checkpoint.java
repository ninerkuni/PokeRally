package Elements;

import Board.Board;

public class Checkpoint extends Element{

	//constructor, initiated with ID so checkpoint has id when created
	public Checkpoint(int id) {
		ID = id;
	}
	
	public int effect(int score) {
		return 0;
	}
	//overwritten method inherited from element, adds to the score and increase robot checkcounter
	@Override
	public void move(Robot robot){
		if (robot.getCheckCount() == ID) {
			robot.setScore(robot.getScore()+10);
			robot.setCheckCount(robot.getCheckCount()+1);
		}
		else if (robot.getCheckCount() > ID) {
			robot.setScore(robot.getScore()+5);
		}
	}
	//overwritten method, used for saving to distinguish obstacles saved and loading
	public String message() {
		return "Checkpoint"+(ID+1);
	}
}
