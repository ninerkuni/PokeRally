package GUI;

import java.awt.Font;
import javax.swing.*;


public class SwingDemo {
	   public static void main(String args[]) {
	      JFrame frame = new JFrame("Label Example");
	      JLabel label;
	      label = new JLabel("First Label");
	      label.setBounds(50, 50, 100, 30);
	      label.setFont(new Font("Orange Kid", Font.PLAIN, 18));
	      frame.add(label);
	      frame.setSize(300,300);
	      frame.setLayout(null);
	      frame.setVisible(true);
	   }
	}