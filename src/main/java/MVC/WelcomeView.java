package MVC;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class WelcomeView extends View {
	private boolean single;
	private boolean newGame;
	private boolean done;
	private boolean resume;
	private boolean allNames;
	private String difficulty;
	
	private ArrayList<String> robots;
	
	JButton mainMenu = new JButton("Back to Menu");
	
	public WelcomeView(Controller controller) {
		super(controller);
		mainMenu.addActionListener(
				e ->{welcome();});
		welcome();
	}
	
	
	
	private void welcome() {
		int row = 0;
		robots = new ArrayList<String>();
		
		
		rally.remove(panel);
		panel.removeAll();
		rally.repaint();
		
		
		JLabel welcome = new JLabel("Welcome to Robot Rally!!!");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("Orange Kid", Font.BOLD, 50));
		welcome.setForeground(Color.WHITE);
		

		panel.add(welcome,titleRow(c,row));
		row++;
		
		
		JButton newGame = new JButton("New Game");
		newGame.addActionListener(
				e ->{
					rally.remove(panel);
					panel.removeAll();
					newGame();
				}	
				);
		
		
	
		panel.add(newGame,rowLeft(c,row));
		
		
		JButton resumeGame = new JButton("Resume Game");
		resumeGame.addActionListener(
				e ->{
					rally.remove(panel);
					panel.removeAll();
					resumeGame();
				}	
				);
		
		panel.add(resumeGame,rowRight(c,row));
		row++;
		
		
		
		
		panel.add(mainMenu,mainMenu(c,row));
		
		
		
		rally.add(panel);
		rally.pack();
		
	}
	
	private void newGame() {
		controller.setGameResume(false);
		rally.repaint();
		int row = 0;
		
	    JLabel welcome = new JLabel("Start a new game");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("Orange Kid", Font.BOLD, 50));
		welcome.setForeground(Color.WHITE);
		
		panel.add(welcome,titleRow(c,row));
		row++;
		
		
		JButton singlePlayer = new JButton("Single Player");
		singlePlayer.addActionListener(
				e ->{
					System.out.println("single player");
					controller.setSingle(true);
					names();
				}	
				);
		panel.add(singlePlayer,rowRight(c,row));
		
		
		JButton multiPlayer = new JButton("Multiplayer");
		multiPlayer.addActionListener(
				e ->{
					System.out.println("multiplayer");
					controller.setSingle(false);
					names();
				}	
				);
		panel.add(multiPlayer,rowLeft(c,row));
		row++;
		
		panel.add(mainMenu,mainMenu(c,row));
		
		rally.add(panel);
		
		rally.pack();
	}
	
	private void resumeGame() {
		controller.setGameResume(true);
		controller.updateView();
	}
	
	private void askForName(int row) {
		JTextField robot = new JTextField(16);
		panel.add(robot,rowLeft(c,row));
		
		JButton named = new JButton("Add");
		named.addActionListener(
				e ->{
						int error = controller.addName(robot.getText());
						JFrame warning = new JFrame();
						if(error == 1) {
					        JOptionPane.showMessageDialog(warning, "Robot already exists!");
						}
						else if(error == 2) {
							JOptionPane.showMessageDialog(warning, "Please insert a name!");
						}
						else if(error == 0){
							robot.setVisible(false);
							named.setVisible(false);
							JLabel success = new JLabel("Robot "+controller.getLastName()+" added!");
							success.setHorizontalAlignment(SwingConstants.CENTER);
							success.setForeground(Color.WHITE);
							panel.add(success, titleRow(c, row));

						}
				}	
				);
		panel.add(named,rowRight(c,row));
	}
	



	private void names() {
		int row = 0;
		
		rally.remove(panel);
		panel.removeAll();
		rally.repaint();
		
		JLabel names = new JLabel("Name your robot");
		names.setFont(new Font("Orange Kid", Font.BOLD, 40));
		names.setHorizontalAlignment(SwingConstants.CENTER);
		names.setForeground(Color.WHITE);
		
		
		panel.add(names,titleRow(c,row));
		row++;
		
		
		
		if(controller.getSingle()) {
			
			JLabel singleRobot = new JLabel("What's your robot called?");
			singleRobot.setHorizontalAlignment(SwingConstants.CENTER);
			singleRobot.setForeground(Color.WHITE);
			
			
			panel.add(singleRobot,titleRow(c,row));
			row++;
			
			askForName(row);
			row++;
			
			
	
		}
		else {
			//stuff for robot 1
			JLabel robot1 = new JLabel("Player 1: What's your robot called?");
			robot1.setHorizontalAlignment(SwingConstants.CENTER);
			robot1.setForeground(Color.WHITE);
			
			panel.add(robot1,titleRow(c,row));
			row++;
			
			askForName(row);
			row++;

			//stuff for robot 2
			JLabel robot2 = new JLabel("Player 2: What's your robot called?");
			robot2.setHorizontalAlignment(SwingConstants.CENTER);
			robot2.setForeground(Color.WHITE);
			
			panel.add(robot2,titleRow(c,row));
			row++;
			
			askForName(row);
			row++;
			
		}
		
		//add next button
		JButton next = new JButton("Next");
		next.addActionListener(
						e -> {if(controller.allNames()) levels();});
		
		panel.add(next,rowRight(c,row));
		
		//add main menu at the bottom
		panel.add(mainMenu,mainMenu(c,row));
		
		//assemble the panel	
		rally.add(panel);
		
		rally.pack();
		
	
	}
	
	private void levelButton(String level, int row) {
		JButton button = new JButton(level);
		button.addActionListener(
						e -> {	controller.setDifficulty(level);
								controller.updateView();});
		c = titleRow(c,row);
		c.ipady = 30;
		panel.add(button,c);
	}
	
	
	private void levels() {
		int row = 0;
		
		
		rally.remove(panel);
		panel.removeAll();
		rally.repaint();
		
		JLabel names = new JLabel("Choose a difficulty");
		names.setFont(new Font("Orange Kid", Font.BOLD, 40));
		names.setHorizontalAlignment(SwingConstants.CENTER);
		names.setForeground(Color.WHITE);
		
		
		
		panel.add(names,titleRow(c,row));
		row++;
		
		ArrayList<String> difficulties = new ArrayList<String>(Arrays.asList("EASY","MEDIUM","HARD"));
		
		for(String l : difficulties) {
			levelButton(l,row);
			row++;
		}
		
		panel.add(mainMenu,mainMenu(c,row));
		
		rally.add(panel);
		rally.pack();
	}
	
	public boolean isDone() {
		return done;
	}
	
	public boolean isNewGame() {
		return !resume;
	}

	public boolean isSingle() {
		return single;
	}

	public String getDifficulty() {
		return difficulty;
	}


	public ArrayList<String> getRobots() {
		return robots;
	}
	
	
}
