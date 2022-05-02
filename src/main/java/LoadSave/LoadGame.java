package LoadSave;


import Board.*;
import Elements.*;
import Gameplay.Game;
import Player.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class LoadGame {

    private String line = "";
    private Game game;
    private Board b;


    ArrayList<String> robotData;
    ArrayList<String> checkPointData;
    ArrayList<String> obstacleData;
    ArrayList<String> boardData;

    String robotPath;
    String obstaclePath;
    String checkPointPath;
    String boardPath;


    //when using this, first call tryLoad() and handle exceptions, then call load()!!!
    public LoadGame(){
        this.robotPath = "DataOfRobot.csv";
        this.obstaclePath = "DataOfObstacle.csv";
        this.checkPointPath = "DataOfCheckPoint.csv";
        this.boardPath = "DataOfBoard.csv";
    }

    public Game load(){
        setUpForLoadedGame(robotData,obstacleData,checkPointData,boardData);
        game.setBoard(b);
        return game;
    }

    public LoadGame(String s, String r, String t, String y) {
        this.robotPath = s;
        this.obstaclePath = r;
        this.checkPointPath = t;
        this.boardPath = y;
    } // used in scenario testing

    public int tryLoad() {
        try {
            loadRobot(robotPath);
            loadObstacles(obstaclePath);
            loadCheckpoints(checkPointPath);
            loadBoard(boardPath);
        } catch (IOException e) {
            return 1;
        }
        return 0;
    }

    public void loadRobot(String robotPath) throws FileNotFoundException {

        String[] robot;
        robotData = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(robotPath));
            while ((line = br.readLine()) != null) {
                robot = line.split(",");
                robotData.add(robot[0]);
                robotData.add(robot[1]);
                robotData.add(robot[2]);
                robotData.add(robot[3]);
                robotData.add(robot[4]);
                robotData.add(robot[5]);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadObstacles(String obstaclePath) throws FileNotFoundException {
        String[] obstacles;
        obstacleData = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(obstaclePath));
            while ((line = br.readLine()) != null) {
                obstacles = line.split(",");
                obstacleData.add(obstacles[0]);
                obstacleData.add(obstacles[1]);
                obstacleData.add(obstacles[2]);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCheckpoints(String checkPointPath) throws FileNotFoundException{
        String[] checkpoints;
        checkPointData = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(checkPointPath));
            while ((line = br.readLine()) != null) {
                checkpoints = line.split(",");
                checkPointData.add(checkpoints[0]);
                checkPointData.add(checkpoints[1]);
                checkPointData.add(checkpoints[2]);
                checkPointData.add(checkpoints[3]);
            }
        System.out.println("Checkpoint Data: "+checkPointData);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadBoard(String boardPath) throws FileNotFoundException {
        String[] board;
        boardData = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(boardPath));
            while ((line = br.readLine()) != null) {
                board = line.split(",");
                boardData.add(board[0]);
                boardData.add(board[1]);
                boardData.add(board[2]);
                boardData.add(board[3]);
                boardData.add(board[4]);
            }
            System.out.println("Board Data: "+boardData);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public Game loadGame(){
//
//        setUpForLoadedGame();
//
//    }

    public void setUpForLoadedGame(ArrayList<String> robots, ArrayList<String> obstacles, ArrayList<String> checkpoints,
                                   ArrayList<String> board) {
        int dimensions = Integer.parseInt(board.get(1));
        b = Board.getBoard();
//        b = new Board(dimensions); // instantiate board
        game = new Game();
        addLoadedObstacles(obstacles); // set up obstacles
        addLoadedCheckPoints(checkpoints); // set up checkpoints
        addLoadedRobots(robots); // set up robots
    }

    private void addLoadedObstacles(ArrayList<String> obstacles) {
        for(int i = 0; i < obstacles.size(); i++) {
            if(Objects.equals(obstacles.get(i), "Pit")) {
                loadPit(obstacles, i);
            }
            else if (Objects.equals(obstacles.get(i), "Conveyer")) { // need to have conveyer in obstacle list
                loadConveyor(obstacles, i);
            }
            else if (Objects.equals(obstacles.get(i), "Trampoline")) {// need to have trampoline in obstacle list
                loadTrampoline(obstacles, i);
            }
            else if (Objects.equals(obstacles.get(i), "Gear")) { // need to have gear in obstacle list
                loadGear(obstacles, i);
            }
        }
    }

    private void loadGear(ArrayList<String> obstacles, int i) {
        Coordinates c = new Coordinates(Integer.parseInt(obstacles.get(i +1)), Integer.parseInt(obstacles.get(i +2)));
        Obstacle o = new Gear();
        o.setCoordinates(c);
        b.add(o);
    }

    private void loadTrampoline(ArrayList<String> obstacles, int i) {
        Coordinates c = new Coordinates(Integer.parseInt(obstacles.get(i+1)), Integer.parseInt(obstacles.get(i+2)));
        System.out.println("Trampoline");
        System.out.print("Coordinates: ");
        c.print();
        Obstacle o = new Trampoline();
        o.setCoordinates(c);
        b.add(o);
    }

    private void loadConveyor(ArrayList<String> obstacles, int i) {
        Coordinates c = new Coordinates(Integer.parseInt(obstacles.get(i+1)), Integer.parseInt(obstacles.get(i+2)));
        System.out.println("Conveyor");
        System.out.print("Coordinates: ");
        c.print();
        Obstacle o = new Conveyer();
        o.setCoordinates(c);
        b.add(o);
    }

    private void loadPit(ArrayList<String> obstacles, int i) {
        Coordinates c = new Coordinates(Integer.parseInt(obstacles.get(i+1)), Integer.parseInt(obstacles.get(i+2)));
        System.out.println("Pit");
        System.out.print("Coordinates: ");
        c.print();
        Obstacle o = new Pit();
        o.setCoordinates(c);
        b.add(o);
    }

    private void addLoadedRobots(ArrayList<String> robots) {
        System.out.println("Robots: "+robots);
        boolean multiplayer = false;
        if (robots.size() > 6) multiplayer = true;
        Robot robot = new Robot(robots.get(0),b);
        robot.setOrientation(Integer.parseInt(robots.get(1)));
        robot.setScore(Integer.parseInt(robots.get(2)));
        robot.setCheckCount(Integer.parseInt(robots.get(3)));
        System.out.println("Coordinates integer: "+Integer.parseInt(robots.get(4))+","+Integer.parseInt(robots.get(5)));
        Coordinates coordinates = new Coordinates(Integer.parseInt(robots.get(4)),Integer.parseInt(robots.get(5)));
        System.out.print("Coordinates class: ");
        coordinates.print();
//        robot.setCoordinates(Integer.parseInt(robots.get(4)),Integer.parseInt(robots.get(5)));
//        System.out.println("Coordinates: "+Integer.parseInt(robots.get(4))+","+Integer.parseInt(robots.get(5)));
        System.out.print("Coordinates robot: ");
        robot.getCoordinates().print();
        robot.setSignature("Robot1_");
        if(multiplayer) robot.setStart(0,0);
        else robot.setStart(b.getDimensions()/2,b.getDimensions()/2 );
        robot.setCoordinates(coordinates);
        b.add(robot);
        game.addPlayer(new Player(robot));
        game.setMultiplayer(false);

        if (multiplayer) { //singleplayer
            robot = new Robot(robots.get(6),b);
            robot.setOrientation(Integer.parseInt(robots.get(7)));
            robot.setScore(Integer.parseInt(robots.get(8)));
            robot.setCheckCount(Integer.parseInt(robots.get(9)));
            robot.setCoordinates(Integer.parseInt(robots.get(10)),Integer.parseInt(robots.get(11)));
            System.out.println("Coordinates: "+Integer.parseInt(robots.get(10))+","+Integer.parseInt(robots.get(11)));
            coordinates = new Coordinates(Integer.parseInt(robots.get(10)),Integer.parseInt(robots.get(11)));
            System.out.print("Coordinates class: ");
            coordinates.print();
//        robot.setCoordinates(Integer.parseInt(robots.get(4)),Integer.parseInt(robots.get(5)));
//        System.out.println("Coordinates: "+Integer.parseInt(robots.get(4))+","+Integer.parseInt(robots.get(5)));

            System.out.print("Coordinates robot: ");
            robot.getCoordinates().print();
            robot.setSignature("Robot2_");
            robot.setStart(b.getDimensions()-1,b.getDimensions()-1);
            robot.setCoordinates(coordinates);
            b.add(robot);
            game.addPlayer(new Player(robot));
            game.setMultiplayer(true);

        }
        System.out.println("Coordinates of elements on the board:");
        for(Element e : b.getElementsOnBoard()){
            System.out.print("Element "+e.message()+" - ");
            e.getCoordinates().print();
        }
        game.addRobots();
    }

    private void addLoadedCheckPoints(ArrayList<String> checkpoints) {
        System.out.println("Number of checkpoints: "+checkpoints.size()/4);
        for (int i = 0; i < checkpoints.size(); i++) {
            if (checkpoints.get(i).contains("Checkpoint")) {
                loadCheckPoint(checkpoints, i);
            }
        }
        System.out.println("Number of checkpoints on the board "+b.getCheck());
    }

    private void loadCheckPoint(ArrayList<String> checkpoints, int i) {
        int ID = Integer.parseInt(checkpoints.get(i + 1));
        Element c = new Checkpoint(ID);
        Coordinates cord = new Coordinates(Integer.parseInt(checkpoints.get(i + 2)), Integer.parseInt(checkpoints.get(i + 3)));
//        System.out.println("Checkpoint");
//        System.out.print("Coordinates: ");
//        cord.print();
//        System.out.println("Number of checkpoints: "+checkpoints.size()/4);
        b.setCheck(checkpoints.size()/4);
//        System.out.println("Number of checkpoints on the board");
        c.setCoordinates(cord);
        b.add(c);
    }
}

