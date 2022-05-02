package MVC;


import java.util.ArrayList;

import Elements.Element;
import Elements.Robot;

public class Controller {
	//according to the MVC pattern, the controller navigates between view and model
	private Model model;
	private View view;

	//step counter is used to distinguish between different stages of the game initiation
	private int step = 0;
	
	
	
	public Controller() {
		//model is initiated
		model = new Model();
		//view is initialized depending using the updateView() method
		updateView();
	}
	

	public void updateView() {
		if(step == 0) {
			//step 0 starts the program
			//controller passes itself to view upon initialization
			view = new WelcomeView(this);
			step++;
		}
		else if(step == 1){
			//in step 1, the view depends on whether a new game is started or the previous game is loaded
			if(model.resumeGame()){
				//load game is attempted
				int error = tryLoad();
				//tryLoad() returns an error code
				if(error == 0){
					//load successfull, game can be set up
					model.loadGame();
					//old view window is disposed
					view.dispose();
					//game view initialized, game is started
					view = new GameView(this);
					step++;
				}
				else{
					//load unsuccessfull, program is ended
					view.showWarning("Load game failed!");
					view.dispose();
				}
			}
			else {
				//new game is started
				model.newGame();
				//old view is disposed
				view.dispose();
				//game view initialized
				view = new GameView(this);
				step++;
			}
		} else {
			//for steps > 1, current view is updated
				view.update();
			}




	}

	public void setSingle(boolean b) {
		//specifies single player mode (true or false)
		model.setSingle(b);
	}

	public int addName(String name) {
		//passes robot names from user input
		if(model.getRobotNames().contains(name)) {
			//robot name already exists in the game
			return 1;
		}
		else if(name == null || name.trim().length() == 0) {
			//no input
			return 2;

		}
		else {
			//valid input, name is added
			model.robotsAdd(name);
			return 0;
		}

	}

	public String getLastName() {
		//returns previously input name to display on GUI
		if(!model.getRobotNames().isEmpty()){
			return model.getRobotNames().get(model.getRobotNames().size() - 1);
		}
		//in case no names are stored, return null
		return null;
	}

	public boolean getSingle() {
		//returns is single player (true or false)
		return model.getSingle();
	}

	public boolean allNames() {
		//determines whether names have been collected for all robots
		return model.allNames();
	}

	public void setDifficulty(String level) {
		//passes input difficulty from view to model
		model.setDifficulty(level);
	}

	public void setGameResume(boolean b) {
		//saves whether game has been restarted or just initialized
		model.setResumeGame(b);
	}


	public int tryLoad(){
		//returns error code
		return model.loadGame();
	}


	public String[][] loadBoard(){
		//matrix of dimensions x dimensions is allocated to store element signatures for GUI representation
		String[][] board = new String[getDimensions()][getDimensions()];
		//iterate through elements on the board
		for(Element e : model.getElements()){

			int x = e.getCoordinates().getx();
			int y = e.getCoordinates().gety();
			try{
				if(!(board[x][y] == null)) board[x][y] += e.message();
				else board[x][y] = e.message();
			}
			//index out of bounds is handled to avoid program crashing
			catch (IndexOutOfBoundsException ex){
				view.showWarning("Load Elements failed: Element "+e.message()+" at coordinates ("+e.getCoordinates().getx()+","+e.getCoordinates().gety()+")");
			}
		}
		return board;
	}

	public int getDimensions() {
		return model.getDimensions();
	}


	public String[] getCards() {
		return model.getCardTitles();
	}

	public void drawCards() {
		//passes draw cards command from UI
		model.drawCards();
	}

	public void setCardToPosition(String title, int position) {
		//passes ordering of cards from UI to model
		if(position >= model.getCardTitles().length){
			view.showWarning("you selected all cards already");
			return;
		}
		//view reply depends on error code passed by model
		int error = model.setCardToPosition(title,position);
		if (error == 1){
			view.showWarning("Card couldn't be set to this position!");
		}
	}

	public boolean isOrdered() {
		//passes whether all cards are ordered
		return model.isOrdered();
	}


	public void playCardsOneByOne() {
		int c = model.getCardTitles().length;
		//plays all cards in the hand
		for(int i = 0; i < c; i++) {
			String card = model.playCard();
		}
		//view can be cast to GameView, as this method is only called in the gameplay part of the program,
		//not during the set up
		if(view instanceof GameView){
			((GameView) view).updateBoard();
			((GameView) view).updateStats();
		}

		view.showScore(getSingle() || (model.getRound() % 2 == 0));
		if (model.nextRound()) {
			//score is only show after each round (both players played their turn)
			view.update();
			} else {
				//in case there is a winner, the game is ended
				view.winner();
				view.dispose();
			}
		}


	public int getScore() {
		//returns score of active player
		return model.getScore();
	}

	public int getCheckpointCounter() {
		//returns checkpoint counter of active player
		return model.getCheckpoints();
	}

	public void saveGame() {
		//attempt to save game in current directory
		if(model.saveGame() == 0){
			//if saving was sucessfull, exit program
			System.exit(0);
		}
		else{
			view.showWarning("Saving failed");
		}
	}

	public int getNumCheckpoints() {
		//returns active number of checkpoints
		return model.getNumCheckpoints();
	}

	public void getStats() {
		//returns relevant statistics of all robots in the game, adds to view accordingly
		for(Robot r : model.getRobots()){
			int[] stats = {r.getScore(),r.getCheckCount()};
			view.addStats(r.getName(),stats);
		}
	}

	public String getActiveName() {
		//returns name of active robot
		return model.getActiveName();
	}

	public String getWinner() {
		//returns name of winner (in case game is ended)
		return model.getWinner();
	}
}
