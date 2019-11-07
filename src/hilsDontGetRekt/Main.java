package hilsDontGetRekt;

import java.awt.Point;

import javax.swing.JFrame;

public class Main extends JFrame {
	Gui g=new Gui();
	private static JFrame frame;
	public Main() {
		setSize(1600,1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(g);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		
		frame=new Main();
	}
	
	public static Point guiLocation(){
		return frame.getLocationOnScreen();
	}
	public static int width(){
		return frame.getWidth();
	}
	public static int height(){
		return frame.getHeight();
	}


}

