package minitennis;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class Main extends JPanel {

	Ball ball = new Ball(this);

	private void move(){
		ball.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
	}

    public static void main(String[] args) throws InterruptedException {
	    JFrame frame = new JFrame("Mini Tennis");
	    Main game = new Main();
	    frame.add(game);
	    frame.setSize(300,300);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    while(true){
	    	game.move();
	    	game.repaint();
	    	Thread.sleep(10);
		}
    }
}
