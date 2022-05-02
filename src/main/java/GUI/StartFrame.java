package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import Gameplay.Game;


class LevelFrame extends JFrame{
	Game game;
	
	
	public LevelFrame(String title, Game game) {
		super(title);
		setSize(500,400);
	    setDefaultFont.setFont(new FontUIResource(new Font ("Orange Kid", Font.BOLD, 30)));
	    
		this.game = game;
		
		String[] optionsToChoose = game.getDifficulties();
	    
	    JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
	}
	
}


public class StartFrame extends JFrame {
	Game game = new Game();
	
	public StartFrame(String title) {
			super(title);
		    setSize(500,400);
		    setDefaultFont.setFont(new FontUIResource(new Font ("Orange Kid", Font.BOLD, 30)));
		    
		    
		    
		    JPanel panel = new JPanel();
			panel.setBackground(Color.GREEN);
			panel.setPreferredSize(new Dimension(500, 400)); 
		    
		    
		    JLabel welcome = new JLabel("Start a new game");
			welcome.setHorizontalAlignment(SwingConstants.CENTER);
			welcome.setFont(new Font("Orange Kid", Font.BOLD, 50));
			welcome.setForeground(Color.WHITE);

			panel.add(welcome);
			
			JButton singlePlayer = new JButton("Single Player");
			singlePlayer.addActionListener(
					e ->{
						System.out.println("single player");
						game.setMultiplayer(false);
						this.dispose();
					}	
					);
			panel.add(singlePlayer,BorderLayout.CENTER);
			
			
			JButton multiPlayer = new JButton("Multiplayer");
			multiPlayer.addActionListener(
					e ->{
						System.out.println("multiplayer");
						game.setMultiplayer(true);
						this.dispose();
					}	
					);
			panel.add(multiPlayer,BorderLayout.CENTER);
			
			this.add(panel);
			
			this.pack();
			
		  }
}




