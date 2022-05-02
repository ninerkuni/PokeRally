package Elements;

public class Obstacle extends Element {
	public int effect(int score) {
		return -3;
	}


	public String message() {
		return "obstacle";
	}
	
	public Obstacle construct() {
		return new Obstacle();
	}
}




