package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
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

class ButtonListener implements ActionListener { 
	private int c = 0;
	public void actionPerformed(ActionEvent e) {
		c++; 
		System.out.println(e.getActionCommand() + " - " + c);
		}
	}

//class ClosingButton implements ActionListener { 
//	public void actionPerformed(ActionEvent e) {
//			System.out.println("window closed");
//			frame.dispose();
//		}
//	}
class WelcomeFrame extends JFrame{
	public WelcomeFrame(String title) {
		super(title);
	    setSize(500,400);
	}
	    
}


public class display {
	private boolean single;
	public display() {
			
			WelcomeFrame frame = new WelcomeFrame("Game Start"); 
			
			
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(500, 400)); 
			panel.setBackground(Color.GREEN);

		    
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
		      
		    frame.setSize(500,400);
			
			
			JLabel welcome = new JLabel("Welcome to Robot Rally!!!");
			welcome.setHorizontalAlignment(SwingConstants.CENTER);
			welcome.setFont(new Font("Orange Kid", Font.BOLD, 50));
			welcome.setForeground(Color.WHITE);
			
			
			
			panel.add(welcome,BorderLayout.NORTH);
			
			
			frame.add(panel);
	
			
			class ClosingButton implements ActionListener { 
				public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				};

			
			
			JButton newGame = new JButton("New Game");
			newGame.addActionListener(
					e ->{
						single = true;
//						StartFrame start = new StartFrame("start");
//						start.setVisible(true);
//						start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.dispose();
					}	
					);
			panel.add(newGame,BorderLayout.CENTER);
			
			
			JButton resumeGame = new JButton("Resume Game");
			resumeGame.addActionListener(
					e ->{
						single = false;
//						ResumeFrame resume = new ResumeFrame("resume");
//						resume.setVisible(true);
//						resume.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.dispose();
					}	
					);
			panel.add(resumeGame,BorderLayout.CENTER);
			
			
			frame.add(panel);
//			JPanel panel2 = new JPanel();
//			panel2.setPreferredSize(new Dimension(200,100));
//			panel.add(panel2);
//			
//			JLabel lbl = new JLabel("I am a JLabel"); 
//			lbl.setHorizontalAlignment(SwingConstants.CENTER);
//			frame.add(panel, BorderLayout.CENTER);
//			frame.add(lbl, BorderLayout.EAST);
//			
//			ButtonListener l = new ButtonListener();
//			JButton b1 = new JButton("close"); 
//			b1.setActionCommand("1stCmd"); 
//			b1.addActionListener(e -> {
//				   frame.dispose();
//			});
//			
//			panel.add(b1,BorderLayout.AFTER_LAST_LINE);
			
			
			
			frame.pack();
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			frame.setVisible(true);
			
		
			
			
		}
	public boolean isSingle() {
		return single;
	} 
		}

