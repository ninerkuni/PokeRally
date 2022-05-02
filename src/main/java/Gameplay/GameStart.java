package Gameplay;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import Board.Board;
import Board.ObstacleFactory;
import Elements.Robot;
import Player.Deck;
import Player.Player;
import Player.Hand;


public class GameStart {
    private int obstacles;
    private int checkpoints;

	private ArrayList<String> names;
    private Board board;
	private Game game;
    private ObstacleFactory factory;
//    private ArrayList<Robot> robots = new ArrayList<Robot>();
	private String hardness;
    
    //constructer initiates Board, Game, ObstacleFactory, ArrayList for names and fills the factory with obstacles
    public GameStart(){
		System.out.println("gamestart initiated");

		board = Board.getBoard();
		game = new Game();
		factory = new ObstacleFactory();
		factory.defaultFill();

		names = new ArrayList<>();
	}
    
    int amountOfPlayers;
    
    public void setDifficulty(String difficulty) {
    	switch(difficulty) {
    		case ("EASY"):
    			obstacles = (int) (6 + 4 * Math.random());
    		    checkpoints = 3;
				hardness = "EASY";
    		    break;
    		case ("MEDIUM"):
    			obstacles = (int) (8 + 6 * Math.random());
    		    checkpoints = 3;
				hardness = "MEDIUM";
    		    break;
    		case ("HARD"):
    			obstacles = (int) (14 + 6 * Math.random());
    		    checkpoints = 2;
				hardness = "HARD";
    		    break;
    	}
    }
    

    public void setAmountOfPlayers(int amountOfPlayers) {
		this.amountOfPlayers = amountOfPlayers;
	}

	//starts the game, initiates the robots, players, sets the multiplayer and sets the board up
	public Game start() {
		System.out.println("GameStart.start()...");
		if(amountOfPlayers == 1) {
			System.out.println("one player");
			Robot robot = new Robot(names.get(0),board);
			Player player = new Player(robot);
			robot.setSignature("Robot1_");
			board.setUp(robot, obstacles, checkpoints, factory);
			game.setMultiplayer(false);
			game.addPlayer(player);
			game.addRobots();

		}
		else if (amountOfPlayers == 2) {
			System.out.println("two players");
			Robot robot1 = new Robot(names.get(0),board);
			robot1.setSignature("Robot1_");
			Player player1 = new Player(robot1);
			Robot robot2 = new Robot(names.get(1),board);
			robot2.setSignature("Robot2_");
			Player player2 = new Player(robot2);
			board.setUp(robot1, robot2, obstacles, checkpoints, factory);
			game.setMultiplayer(true);
			game.addPlayer(player1);
			game.addPlayer(player2);
			//try to find a different way of accessing the robots (is it necessary to have an array of robots and
			// an array of players
			game.addRobots();

		}
		game.setBoard(board);
		return game;
	}


	public Board getBoard() {
		return board;
	}

    public int getAmountofPlayers() {
		return amountOfPlayers;
    }

	//Used in GUI, adds the name to GameStart for initiation robots from Model
	public void addName(String name) {
		names.add(name);
	}

	//Used in GUI, gets the names given in Model by the player
	public ArrayList<String> getRobotNames() {
		return names;
	}

	public String getDifficulty() { return hardness;}

	public int getAmountOfPlayers() {
		return amountOfPlayers;
	}
}