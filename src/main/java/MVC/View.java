package MVC;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

class setDefaultFont{
	static void setFont(FontUIResource myFont) {
	    UIManager.put("CheckBoxMenuItem.acceleratorFont", myFont);
	    UIManager.put("Button.font", myFont);
	    UIManager.put("ToggleButton.font", myFont);
	    UIManager.put("RadioButton.font", myFont);
	    UIManager.put("CheckBox.font", myFont);
	    UIManager.put("ColorChooser.font", myFont);
	    UIManager.put("ComboBox.font", myFont);
	    UIManager.put("Label.font", myFont);
	    UIManager.put("List.font", myFont);
	    UIManager.put("MenuBar.font", myFont);
	    UIManager.put("Menu.acceleratorFont", myFont);
	    UIManager.put("RadioButtonMenuItem.acceleratorFont", myFont);
	    UIManager.put("MenuItem.acceleratorFont", myFont);
	    UIManager.put("MenuItem.font", myFont);
	    UIManager.put("RadioButtonMenuItem.font", myFont);
	    UIManager.put("CheckBoxMenuItem.font", myFont);
	    UIManager.put("OptionPane.buttonFont", myFont);
	    UIManager.put("OptionPane.messageFont", myFont);
	    UIManager.put("Menu.font", myFont);
	    UIManager.put("PopupMenu.font", myFont);
	    UIManager.put("OptionPane.font", myFont);
	    UIManager.put("Panel.font", myFont);
	    UIManager.put("ProgressBar.font", myFont);
	    UIManager.put("ScrollPane.font", myFont);
	    UIManager.put("Viewport.font", myFont);
	    UIManager.put("TabbedPane.font", myFont);
	    UIManager.put("Slider.font", myFont);
	    UIManager.put("Table.font", myFont);
	    UIManager.put("TableHeader.font", myFont);
	    UIManager.put("TextField.font", myFont);
	    UIManager.put("Spinner.font", myFont);
	    UIManager.put("PasswordField.font", myFont);
	    UIManager.put("TextArea.font", myFont);
	    UIManager.put("TextPane.font", myFont);
	    UIManager.put("EditorPane.font", myFont);
	    UIManager.put("TabbedPane.smallFont", myFont);
	    UIManager.put("TitledBorder.font", myFont);
	    UIManager.put("ToolBar.font", myFont);
	    UIManager.put("ToolTip.font", myFont);
	    UIManager.put("Tree.font", myFont);
	    UIManager.put("FormattedTextField.font", myFont);
	    UIManager.put("IconButton.font", myFont);
	    UIManager.put("InternalFrame.optionDialogTitleFont", myFont);
	    UIManager.put("InternalFrame.paletteTitleFont", myFont);
	    UIManager.put("InternalFrame.titleFont", myFont);}
}


public class View {
	RallyFrame rally;
	JPanel panel;
	GridBagConstraints c;

	String path = "src/main/";
	Controller controller;

	GridBagConstraints rowRight (GridBagConstraints c,int row) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 30;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.insets = new Insets(10,10,10,10); 
		c.gridx = 2;
		c.gridy = row;
		
		return c;
	}
	
	GridBagConstraints rowLeft (GridBagConstraints c,int row) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 30;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.insets = new Insets(10,10,10,10); 
		c.gridx = 1;
		c.gridy = row;
		
		return c;
	}
	
	GridBagConstraints titleRow (GridBagConstraints c,int row) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 3;
		c.gridx = 1;
		c.gridy = row;
		
		return c;
	}
	
	
	GridBagConstraints mainMenu(GridBagConstraints c, int row) {
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_END;
		c.ipady = 0;//bottom of space
		c.insets = new Insets(50,10,10,10);  //top padding
		c.gridx = 2;
		c.gridy = row+1;
		
		return c;
	}
	 GridBagConstraints row(GridBagConstraints c,int row){
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 5;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.insets = new Insets(0,10,0,10);
		c.gridx = 0;
		c.gridy = row;
		return c;
	}

	public void showWarning(String s) {
		JFrame warning = new JFrame();
		JOptionPane.showMessageDialog(warning, s ,"warning",
				JOptionPane.PLAIN_MESSAGE);;

	}

	public void dispose() {
		rally.dispose();
	}

	public void update() {
		System.out.println("View updated");
	}

	public void updateCards() {
	}

	public void nextRound() {

	}

	public void showScore(boolean exit) {
		int row = 0;
		JFrame round = new RallyFrame("Round statistics");
		JPanel display = new DisplayPanel();


		JButton close = new JButton("Continue");
		close.addActionListener(
				e-> {
					round.dispose();
				}
		);
		JButton Exit = new JButton("Exit");
		Exit.addActionListener(
				e-> {
					exitWindow();
					round.dispose();
				}
		);

		Exit.setVisible(exit);




		JLabel player = new JLabel("Statistics for "+(controller.getActiveName()));
		player.setFont(new Font("Orange Kid", Font.BOLD, 50));
		player.setForeground(Color.WHITE);
		display.add(player,titleRow(c,row));
		row++;

		JLabel score = new JLabel("Score: "+ controller.getScore());
		display.add(score,rowLeft(c,row));

		JLabel checkpoints = new JLabel("Checkpoint counter: "+controller.getCheckpointCounter());
		display.add(checkpoints,rowRight(c,row));
		row++;

		try{
			BufferedImage Picture = ImageIO.read(new File(path+"resources/Cards/PokemonPicGUI.png"));
			JLabel illustration = new JLabel(new ImageIcon(Picture));

			display.add(illustration,titleRow(c,row));
			row++;

		}
		catch (IOException e){
			showWarning("Image couldn't be found!");
		}



		display.add(close,rowLeft(c,row));
		display.add(Exit,rowRight(c,row));
		round.add(display);
		round.setVisible(true);
		round.pack();

	}

	private void exitWindow() {
		int row = 0;
		JFrame round = new ScoreFrame("Exit Game");
		JPanel display = new DisplayPanel();

		JButton close = new JButton("Resume Game");
		close.addActionListener(
				e-> {
					round.dispose();

				}
		);
		JButton saveExit = new JButton("Save and Exit");
		saveExit.addActionListener(
				e-> {
					controller.saveGame();
					rally.dispose();
					round.dispose();
				}
		);
		JButton Exit = new JButton("Exit");
		Exit.addActionListener(
				e-> {
					rally.dispose();
					round.dispose();
				}
		);

		display.add(close,titleRow(c,row));
		row++;
		display.add(saveExit,titleRow(c,row));
		row++;
		display.add(Exit,titleRow(c,row));
		row++;
		round.add(display);
		round.pack();

	}



	public void updateStatistics() {

	}

	public void addStats(String name, int[] stats) {




	}

	public void winner() {
		JFrame winner = new RallyFrame("Game is finished");
		winner.setLayout(new GridBagLayout());
		JPanel winnerPanel = new JPanel();
		winnerPanel.setBackground(new Color(-16143860));
		winnerPanel.setForeground(Color.WHITE);

		JLabel winningRobot = new JLabel("Congrats "+controller.getWinner()+ ", you won!!");

		try{
			BufferedImage Picture = ImageIO.read(new File(path+"resources/Cards/PokemonPicGUI.png"));
			JLabel illustration = new JLabel(new ImageIcon(Picture));

			winnerPanel.add(winningRobot,titleRow(c,0));
			winnerPanel.add(illustration,titleRow(c,1));

			JButton exitButton = new JButton("Exit");
			exitButton.addActionListener(
					e->{
						System.exit(0);
					}
			);

			winnerPanel.add(exitButton,mainMenu(c,2));
			winner.add(winnerPanel);

			winner.setVisible(true);
			winner.pack();
		}
		catch (IOException e){
			System.exit(0);
		}

	}



	class DisplayPanel extends JPanel{
		public DisplayPanel(){
			super();
			setBackground(new Color(-16143860));
			setForeground(Color.WHITE);
			setLayout(new GridBagLayout());
		}


	}



	class ScoreFrame extends JFrame{
		public ScoreFrame(String title){
			super(title);
			setPreferredSize(new Dimension(300,300));
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);

		}
	}

	class RallyFrame extends JFrame{
		public RallyFrame(String title) {
			super(title);
		    setPreferredSize(new Dimension(800,800));
		    setBackground(new Color(-16143860));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		}
		    
	}



	public View(Controller controller) {
		this.controller = controller;
		rally = new RallyFrame("Poké Rally");
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		panel.setBackground(new Color(-16143860));

	    
	      //add custom font
	      try {
		          //create the font to use. Specify the size!
		      	Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/ninapeuker/Downloads/orange_kid/orange kid.ttf")).deriveFont(48f);
	    	  	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		          //register the font
	    	  	ge.registerFont(customFont);
		      } catch (IOException e) {
		          e.printStackTrace();
		      } catch(FontFormatException e) {
		          e.printStackTrace();
		      }
	      
	      //use the font
	    setDefaultFont.setFont(new FontUIResource(new Font ("Orange Kid", Font.BOLD, 30)));
	      
	    rally.add(panel);
	    rally.setVisible(true);
	    
	}





}