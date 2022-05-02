package Gameplay;

import java.util.ArrayList;

import Board.Board;
import Elements.Element;
import Elements.Robot;
import Player.Deck;
import Player.Player;
import Player.Card;

public class Game {
	ArrayList<Robot> robots = new ArrayList<Robot>();
	ArrayList<Player> players = new ArrayList<Player>();
	private boolean multiplayer;
	private Board board;

	private Player player;

	private Deck deck;
	
	private int round;
	
	private String[] difficulties = {"EASY","MEDIUM","HARD"};
	
	//constructor, initiates deck and draws default cards
	public Game(){
		System.out.println("Game initiated");
		round = 0;
		deck = new Deck();
		deck.defaultDeck();
	}
	//used in GUI, returns Arraylist of element on the board
	public ArrayList<Element> getElements() {
		return board.getElementsOnBoard();
	}

	//used in GUI, returnsArraylist of element on the board
	public ArrayList<Robot> getRobots() {
		return robots;
	}
	//used in GUI, used to return true to signal the GUI that it is done
	public boolean isFinished() {
		return robots.stream().anyMatch(o -> o.winner()==true);
	}


	//used in GUI, takes in the boolean, that
	public void setMultiplayer(boolean b) {
		multiplayer = b;
		
	}
	//get multiplayer for loaded game
	public boolean getMultiplayer(){
		return multiplayer;
	}

	//returns a string List of difficulties
	public String[] getDifficulties() {
		return difficulties;
	}

	//Used to save the game, after game has been played
	public Board getBoard() {
		return board;
	}

	//used to set the board from GameStart and LoadGame
	public void setBoard(Board b) {
		board = b;
	}

	//used in GUI to print the dimensions
	public int getBoardDimensions() {
		return board.getDimensions();
	}
	
	//Used to keep track player turn
	public Player activePlayer(){
		if(multiplayer) {
			return players.get(round % 2);
		}
		else {
			return players.get(0);
		}
	}
	//prints the card for the robot
	public String[] getCards() {
		player = activePlayer();
		if(player.emptyHand()){
				player.fillHand(deck);
//				player.printHand();
				return player.getTitles();
			}
		else{
//			player.printHand();
			return player.getTitles();
			}
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
	
	//draws the card for the robot
	public void drawCards() {
		player = activePlayer();
		player.fillHand(deck);
	}
	
	//Order the cards of the robot, returns integer to check, return 0 if the card exists, it should exist
	public int setCardToPosition(String title, int position) {
		player = activePlayer();
		Card c = player.getHand().findCard(title);
		if(c != null){
			player.getHand().setPosition(c,position);
			return 0;
		}
		else return 1;
	}

	public void addRobot(Robot robot) {
		robots.add(robot);
	}
	public Player getPlayer() {
		return activePlayer();
	}
	
	//check whether the cards are ordered, return true or false, doesn't allow to play cards if hand isn't ordered
	public boolean isOrdered() {
		player = activePlayer();
		return player.getHand().isOrdered();
	}

	//plays the cards, returns card that is played
	public String playCard() {
		player = activePlayer();
		return player.play();
	}
	//increments the round variable
	public void nextRound() {
		round++;
	}

	//return the score of the player/robot
	public int getActiveScore() {
		return activePlayer().getScore();
	}

	//returns the robots checkcounter
	public int getActiveCheckpoints() {
		return activePlayer().getCheckpoints();
	}
	
	//add robot to ArrayList Robots
	public void addRobots() {
		for(Player player : players){
			robots.add(player.getRobot());
		}
	}

	//Used in GUI to print the winner
	public String getWinner() {
		for(Robot r : robots){
			if(r.winner()){
				return r.getName();
			}
		}
		return null;
	}

	//used in GUI to return the round integer
	public int getRound() {
		return round;
	}
}
