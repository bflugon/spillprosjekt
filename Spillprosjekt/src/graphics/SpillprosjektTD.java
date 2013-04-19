package graphics;

import java.awt.GridLayout;

import javax.swing.JFrame;


// "Rammen" alt skal tegnes i
public class SpillprosjektTD extends JFrame {
	
	private String title = "Spillprosjekt";
	private int width = 820,
				height = 680;
	
	public SpillprosjektTD(){
		
//		Close when closed
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		Sets the size of the window
		setSize(width, height);
		
		setTitle(title);
		setResizable(false);
		
//		Place the window in the middle of the screen
		setLocationRelativeTo(null);
		
//		Divides the screen into a single frame 
		setLayout(new GridLayout(1,1,0,0));
		
//		Create the screen/image on the frame and pass frame as parameter
		Screen screen = new Screen(this);
		add(screen);
		
		setVisible(true);
	}
	
	public static void main(String[] args){
//		Create the frame and start the program
		new SpillprosjektTD();
	}
}
