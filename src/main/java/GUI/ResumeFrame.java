package GUI;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.plaf.FontUIResource;

public class ResumeFrame extends JFrame {
	public ResumeFrame(String title) {
		super(title);
	    setSize(500,400);
	    setDefaultFont.setFont(new FontUIResource(new Font ("Orange Kid", Font.BOLD, 30)));
	  }
}
