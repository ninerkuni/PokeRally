package Player;

import Elements.Robot;

public class Player{


	private Hand hand;
	private Robot robot;

	public Player(Robot robot) {
		this.robot = robot;
		hand = new Hand(5);
	}

	public String play() {
		return hand.play(robot); // used
	}

	public boolean emptyHand() {
		return hand.isEmpty();
	}


	public void fillHand(Deck deck) { // used
		hand.fill(deck);
	}

	public void setOrdered(boolean b) { // used
		hand.ordered(b);
	}


	public String[] getTitles() {
		return hand.getTitles();
	}

	public Hand getHand(){
		return hand;
	}

	public int getScore() {
		return robot.getScore();
	}

	public int getCheckpoints() {
		return robot.getCheckCount();
	}

	public Robot getRobot() {
		return robot;
	}
}