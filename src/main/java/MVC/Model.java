package MVC;

import Elements.Element;
import Elements.Robot;
import Gameplay.Game;
import Gameplay.GameStart;
import LoadSave.LoadGame;
import LoadSave.SaveGame;

import java.util.ArrayList;

public class Model {

	private ArrayList<String> robots;
	private Game game;
	private GameStart start;

	private boolean resume;

	private boolean single;

	public Model() {
		//list of robot names is initated (necessary before instances of game or gamestart are initiated)
		robots = new ArrayList<String>();
//		start = new GameStart();
	}




	public void setDifficulty(String difficulty) {
		//set difficulty
		start.setDifficulty(difficulty);
	}


	public void setSingle(boolean b) {
		//pass information about single-/ multiplayer mode
		single = b;
		if(b){
			start.setAmountOfPlayers(1);
		}
		else{
			start.setAmountOfPlayers(2);
		}

	}

	public ArrayList<String> getRobotNames() {
		//return list of robot names
		return robots;
	}

	public void robotsAdd(String name) {
		//adds robots name to list of names
		robots.add(name);
		start.addName(name);
	}

	public boolean getSingle() {
		if(resume){
			return !game.getMultiplayer();
		}
		else return (start.getAmountofPlayers() == 1);
	}

	public boolean allNames() {
		//checks if enough names have been added to initiate game
		return (start.getAmountofPlayers() == start.getRobotNames().size());
	}

	public boolean resumeGame() {
		//tells if old game is loaded
		return resume;
	}

	public int setResumeGame(boolean b) {
		//stores information about load
		resume = b;
		if(b) {
			return loadGame();
		}
		else{
			start = new GameStart();
		}
		return 2;
	}



	public ArrayList<Element> getElements() {
		//returns all elements in the game
		return game.getElements();
	}

	public int getDimensions() {
		//returns dimensions of the board
		return game.getBoardDimensions();
	}

	public int loadGame(){
		//
		LoadGame saved = new LoadGame();
		int error = saved.tryLoad();
		if(error == 0){
			game = saved.load();
			return 0;
		}
		else{
			return 1;
		}
	}

	public void newGame() {
		game = start.start();
	}


	public String[] getCardTitles() {
		return game.getCards();
	}

	public void drawCards() {
		game.drawCards();
	}

	public int setCardToPosition(String title, int position) {
		return game.setCardToPosition(title,position);
	}

	public boolean isOrdered() {
		return game.isOrdered();
	}

	public String playCard() {
		return game.playCard();
	}

	public boolean nextRound() {
		if(game.isFinished()) return false;
		else{
			game.nextRound();
			return true;
		}
	}

	public int getScore() {
		return game.getActiveScore();
	}

	public int getCheckpoints() {
		return game.getActiveCheckpoints();
	}

	public int saveGame() {
		SaveGame saveGame = new SaveGame(game);
		return saveGame.save();
	}

	public int getNumCheckpoints() {
		return game.getBoard().getCheck();
	}

	public ArrayList<Robot> getRobots() {
		return game.getRobots();
	}

	public String getActiveName() {
		return game.activePlayer().getRobot().getName();
	}

	public String getWinner() {
		return game.getWinner();
	}

	public int getRound() {
		return game.getRound();
	}
}
