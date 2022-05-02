package Elements;

import java.util.Random;

public class Trampoline extends Obstacle{

    //overwritten method, decrement score
    public int effect(int score) {
        return -1;
    }

    //overwritten method, moves robot to random coordinate between 3-5 tiles away
    @Override
    public void move(Robot robot) {
        Coordinates coordinates = new Coordinates(robot.getCoordinates().getx(), robot.getCoordinates().gety());
        System.out.println("robot coordinates..");
        coordinates.print();
        int rx;
        int ry;
        int sx = new Random().nextInt(1+1) - 1;
        if (sx == 0) {sx=1;}
        else {sx= -1;}
        int sy = new Random().nextInt(1+1) - 1;
        if (sy == 0) {sx=1;}
        else {sy= -1;}
        rx = (int) ((3 + 2 * Math.random())*sx + coordinates.getx());
        ry = (int) ((3 + 2 * Math.random())*sy + coordinates.gety());
        while (!(robot.possibleMove(rx, ry))) {
            sx = new Random().nextInt(1+1) - 1;
            if (sx == 0) {sx=1;}
            else {sx= -1;}
            sy = new Random().nextInt(1+1) - 1;
            if (sy == 0) {sx=1;}
            else {sy= -1;}
            rx = (int) ((3 + 2 * Math.random())*sx + coordinates.getx());
            ry = (int) ((3 + 2 * Math.random())*sy + coordinates.gety());
        }
        coordinates.set(rx, ry);
        robot.move(coordinates);
    }

    //overwritten method, returns string used for saving the game
    public String message() {
        return "Trampoline";
    }

    //overwritten method, used in factory when random obstacle is initialized.
    @Override
    public Trampoline construct() {
        return new Trampoline();
    }

}


//public class Trampoline extends Obstacle {
//
//	@Override
//    public String message() {
//		return "Trampoline";
//	}
//
//	@Override
//	public int effect(int x) {
//		return 0;
//	}
//
//	@Override
//	public void move(Robot robot) {
//		Coordinates coordinates = new Coordinates(getCoordinates().getx(),getCoordinates().gety());
//		int rx; int ry;
//		rx = (int) (1+2*Math.random());
//		ry = (int) (Math.sqrt(9-rx*rx)+5*Math.random());
//		//robot has to be moved at least 3
//		while(!(robot.possibleMove(rx,ry) && Math.abs(rx-coordinates.getx())>2 && Math.abs(ry-coordinates.gety())>2)) {
//			rx = (int) (1+2*Math.random());
//			ry = (int) (Math.sqrt(9-rx*rx)+5*Math.random());
//
//		}
//		coordinates.set(rx, ry);
//		robot.move(coordinates);
//	}
//
//
//
//}
