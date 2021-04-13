import javax.swing.JFrame;

public class Pong extends JFrame {
	
	public Pong( ) {
		setTitle("myPong");
		setSize(800, 600);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		
		new Pong();

	}

}
