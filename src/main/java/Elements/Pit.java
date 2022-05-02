package Elements;

public class Pit extends Obstacle{
	public int effect(int score) {
		return -score;
	}

	public String message() {
		return "Pit";
	}

	//method refer to method for reseting robot
	@Override
	public void move(Robot robot) {
		robot.reset();
	}
	@Override
	public Pit construct() {
		return new Pit();
	}
	
}