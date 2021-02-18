package minitennis;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel {

	//Creates ball object
	Ball ball = new Ball(this);
	//Creates racquet object
	Racquet racquet = new Racquet(this);
	//Creates racquetAi object
	RacquetAI racquetAi = new RacquetAI(this);
	int speed = 1;

	//Getter for speed and score (same value)
	private int getScore(){
		return speed - 1;
	}

	//Constructor that creates events for key presses
	public Main(){
		//Creates the listener for key presses
		addKeyListener(new KeyListener() {

			//Currently unused event for typed words
			@Override
			public void keyTyped(KeyEvent e) {
			}

			//Event for any key that is pressed
			@Override
			public void keyPressed(KeyEvent e) {
				//Calls the racquets keyPressed function
				racquet.keyPressed(e);
			}

			//Event for any key that is released
			@Override
			public void keyReleased(KeyEvent e) {
				//Calls the racquets keyReleased function
				racquet.keyReleased(e);
			}
		});
		setFocusable(true);
	}

	// move function used to call the move functions on the ball and racquet
	private void move(){
		ball.move();
		racquet.move();
		racquetAi.move(ball.move());
	}

	//Function used to create game sprites
	@Override
	public void paint(Graphics g) {
		//Used so a trail isn't left behind when a sprite moves
		super.paint(g);
		//Casts parameter g into a Graphics2D variable
		Graphics2D g2d = (Graphics2D) g;
		//Used to smooth the sprites out using antialiasing
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		//Calls the paint function on the ball and passes in g2d
		ball.paint(g2d);
		//Calls the paint function on the racquet and passes in g2d
		racquet.paint(g2d);
		racquetAi.paint(g2d);

		//Sets font color to gray
		g2d.setColor(Color.GRAY);
		//Sets font to Verdana Bold at a size of 30
		g2d.setFont(new Font("Verdana", Font.BOLD,30));
		//Places text in the top left corner of the screen
		g2d.drawString(String.valueOf(getScore()),10,30);
	}

	//Function used to end the game
	public void gameOver(){
		//Shows a dialog box displaying the player score and a button to close the game
		JOptionPane.showMessageDialog(this, "Your score is: " + getScore(), "Game Over",JOptionPane.YES_NO_OPTION);
		//Closes the game
		System.exit(ABORT);
	}

	//Main function used to create the game window
    public static void main(String[] args) throws InterruptedException {
		//Creates the frame of the window and titles it "Mini Tennis"
	    JFrame frame = new JFrame("Mini Tennis");
	    //Calls Main constructor and creates an object called game
	    Main game = new Main();
	    //The game object is placed inside the frame
	    frame.add(game);
	    //Size of the window(frame) is 300x300
	    frame.setSize(480,480);
	    //The game window is visible
	    frame.setVisible(true);
	    //Able to close the game and stop all processes
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    while(true){
	    	//Calls the move function on game
	    	game.move();
	    	//Calls the paint function on the game (repaint is used so the sprites are redrawn when they move)
	    	game.repaint();
	    	//Game loop set to 10 milliseconds
	    	Thread.sleep(10);
		}
    }
}
