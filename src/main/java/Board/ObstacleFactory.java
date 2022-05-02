package Board;

import java.util.ArrayList;

import Elements.*;

public class ObstacleFactory {
	private ArrayList<Obstacle> obstacles;
	public ObstacleFactory() {
		obstacles = new ArrayList<Obstacle>();
	}
	
	public Obstacle pick() {
		if(!(obstacles.isEmpty())) {
			int rand = (int) (obstacles.size() * Math.random());
			return obstacles.get(rand).construct();
		}
		else return null;
	}
	
	public void add(Obstacle e) {
		obstacles.add(e);
	}
	
	public void print() {
		for(Obstacle o : obstacles) {
			System.out.println(o.message());
		}
	}

	public boolean contains(Obstacle e) {
		return obstacles.contains(e);
	}
	//gives default obstacles to list
	public void defaultFill() {
		Obstacle o = new Pit();
		this.add(o);
		o = new Gear();
		add(o);
		o = new Conveyer();
		add(o);
		o = new Trampoline();
		add(o);
	}
}
