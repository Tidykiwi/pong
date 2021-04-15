import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;

public class PongPanel extends JPanel implements ActionListener, KeyListener {

	private final static Color BACKGROUND_COLOR = Color.WHITE;
	private final static int TIMER_DELAY = 5;
	private final static int BALL_MOVEMENT_SPEED = 2;
	private final static int POINTS_TO_WIN = 3;
	private final static int SCORE_FONT_SIZE = 50;
	private final static int SCORE_X_TEXT = 100; 
	private final static int SCORE_Y_TEXT = 100;
	private final static String SCORE_FONT_FAMILY = "Serif";
	private final static int WIN_FONT_SIZE = 50;
	private final static int WIN_X_TEXT = 200;
	private final static int WIN_Y_TEXT = 200;
	private final static String WIN_FONT_FAMILY = "Serif";
	private final static String WIN_MESSAGE = "Win!";
	
	
	int player1Score, player2Score = 0;
	Player gameWinner;
	Ball ball;
	GameState gameState = GameState.Initialising;
	Paddle paddle1, paddle2;
	
	public PongPanel() {
		setBackground(BACKGROUND_COLOR);
		Timer timer = new Timer(TIMER_DELAY, this);
			timer.start();
			addKeyListener(this);
			setFocusable(true);
	}
	
	public void createObjects() {
		ball = new Ball(getWidth(), getHeight());
		paddle1 = new Paddle(Player.One, getWidth(), getHeight());
		paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
	}
	
	public void moveObject (Sprite sprite) {
		sprite.setxPosition(sprite.getxPosition() + sprite.getxVelocity(), getWidth());
		sprite.setyPosition(sprite.getyPosition() + sprite.getyVelocity(), getHeight());
	}
	
	public void checkWallBounce() {
		if(ball.getxPosition() <= 0) {
			// Hit left side of the screen
			addScore(Player.Two);
			resetBall();
		} else if(ball.getxPosition() >= getWidth() - ball.getWidth()) {
			// Hit right side of the screen
			addScore(Player.One);
			resetBall();
		}
		if(ball.getyPosition() <= 0 || ball.getyPosition() >= getHeight() - ball.getHeight()) {
			// Hit top or bottom of the screen
			ball.setyVelocity(-ball.getyVelocity());
		}
	}
	
	public void checkPaddleBounce() {
		if(ball.getxVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
			ball.setxVelocity(BALL_MOVEMENT_SPEED);
		}
		if(ball.getxVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
			ball.setxVelocity(-BALL_MOVEMENT_SPEED);
		}
	}
	
	public void resetBall() {
		ball.resetToInitialPosition();
	}
	
	public void addScore(Player player) {
		if(player == Player.One) {
			player1Score ++;
		} else if(player == Player.Two) {
			player2Score ++;
		}
	}
	
	public void checkWin() {
		if(player1Score == 3) {
			gameWinner = Player.One;
			gameState = GameState.GameOver;
		} else if(player2Score == 3) {
			gameWinner = Player.Two;
			gameState = GameState.GameOver;
		}
	}
	
	private void paintSprite(Graphics g, Sprite sprite) {
		g.setColor(sprite.getColour());
		g.fillRect(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());   
	}
	
	private void update() {
		switch(gameState) {
			case Initialising: {
				createObjects();
				gameState = GameState.Playing;
				ball.setxVelocity(BALL_MOVEMENT_SPEED);
				ball.setyVelocity(BALL_MOVEMENT_SPEED);
				break;
			}
			case Playing: {
				moveObject(paddle1);
				moveObject(paddle2);
				moveObject(ball);
				checkWallBounce();
				checkPaddleBounce();
				checkWin();
				break;
			}
			case GameOver: {
				break;
			}
		}
		
	}
	
	private void paintDottedLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create(); 
			Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
			g2d.setStroke(dashed);
			g2d.setPaint(Color.BLACK);
			g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
			g2d.dispose();
	}
	
	private void paintScores(Graphics g) {
		Font scoreFont = new Font(SCORE_FONT_FAMILY, Font.BOLD, SCORE_FONT_SIZE);
		String leftScore = Integer.toString(player1Score);
		String rightScore = Integer.toString(player2Score);
		g.setFont(scoreFont);
		g.drawString(leftScore, SCORE_X_TEXT, SCORE_Y_TEXT);
		g.drawString(rightScore, getWidth() - SCORE_X_TEXT, SCORE_Y_TEXT);
	}
	
	private void paintWin(Graphics g) {
		Font winnerFont = new Font(SCORE_FONT_FAMILY, Font.BOLD, WIN_FONT_SIZE);
		g.setFont(winnerFont);
		if(gameWinner == Player.One) {
				g.drawString(WIN_MESSAGE, WIN_X_TEXT, WIN_Y_TEXT);
		} else if (gameWinner == Player.Two) {
			g.drawString(WIN_MESSAGE, getWidth() - WIN_X_TEXT, WIN_Y_TEXT);

		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintDottedLine(g);
		if(gameState != GameState.Initialising) {
			paintSprite(g, ball);
			paintSprite(g, paddle1);
			paintSprite(g, paddle2);
			paintScores(g);
		}
		if(gameState == GameState.GameOver) {
			paintWin(g);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_UP) {
			paddle2.setyVelocity(-2);
		} else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setyVelocity(2);
		}
		if(event.getKeyCode() == KeyEvent.VK_W) {
			paddle1.setyVelocity(-2);
		} else if (event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setyVelocity(2);
		}		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setyVelocity(0);
		}
		if(event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setyVelocity(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		update();
		repaint();
		
	}

}
