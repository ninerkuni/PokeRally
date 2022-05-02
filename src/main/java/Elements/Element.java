package Elements;

import Board.Board;

abstract public class Element {
	protected Coordinates coordinates = new Coordinates();
	protected int index;
	protected Board board;
	protected int ID;
	protected boolean visited;
	
	public Element() {};
	
//	void setIndex(int i) {
//		this.index = i;
//	}
	
	public void setCoordinates(int x, int y) {
//		board.xcoordinates.add(x);
//		board.ycoordinates.add(y);
		System.out.println("set coordinates");
		coordinates.set(x,y);
		
	}
	
	public void setCoordinates(Coordinates n) {
//		board.xcoordinates.set(index,n.getx());
//		board.ycoordinates.set(index, n.gety());
		coordinates.set(n.getx(),n.gety());
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
//	abstract void move(Robot r, Board b);
	//method used for overwriting in subclasses
	public abstract String message();


	
	abstract int effect(int x);

	//method used for overwriting in subclasses
	public void move(Robot robot) {
//		Coordinates coordinates = new Coordinates(getCoordinates().getx(),getCoordinates().gety());
//		robot.move(coordinates);
	}
	
	public int getID(){
		return ID;
	}
	
//	public boolean isBarrier() {
//		return false;
//	}
	

	//function used for gear, to set rotation
	public void setLeft(boolean left) {
		
	}

	
}
