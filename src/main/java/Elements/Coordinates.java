package Elements;


public class Coordinates {
	private int[] coordinates;

	// initializes coordinates
	public Coordinates() {
		coordinates = new int[2];
	}

	// sets new coordinates
	public Coordinates(int x, int y) {
		coordinates = new int[] {x,y};
	}
	
	public void set(int x, int y){
		coordinates[0] = x; 
		coordinates[1] = y;
	}
	
	public int getx() {
		return coordinates[0];
	}
	
	public int gety() {
		return coordinates[1];
	}
	
	public int diffx(Coordinates c) {
		return (c.getx()-this.getx());
	}
	
	public int diffy(Coordinates c) {
		return (c.gety()-this.gety());
	}
	
	
	public void movex(int move) {
		this.set(getx()+move, gety());
	}
	
	public void movey(int move) {
		this.set(getx(), gety()+move);
	}

	@Override
    public boolean equals(Object obj) {
    	if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Coordinates coordinates = (Coordinates) obj;
        if(this.coordinates == null || coordinates.coordinates == null) {
        	return false;
        }
        
        return (this.getx()==coordinates.getx()&&this.gety()==coordinates.gety());
    }
	
	
	public void print() {
		System.out.println("("+getx()+","+gety()+")");
	}

	public int diff(Coordinates c) {
		int dx = Math.abs(this.getx()-c.getx());
		int dy = Math.abs(this.gety()-c.gety());
		return (int) Math.sqrt(dx*dx+dy*dy);
	}

	public void setRandom(int dimensions) {
		int x = (int) (dimensions*Math.random());
		int y = (int) (dimensions*Math.random());
		
		this.set(x, y);
	}
}
