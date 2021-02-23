package minitennis;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
    private static final int Y = 375;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 10;
    int x = 200;
    int xa = 0;
    int score = 0;
    private Main game;

    //Constructor that sets the game variable here to our Main class
    public Racquet(Main game){
        this.game = game;
    }

    //Function used to set the movement of the racquet
    public void move(){
        //Racquet is not allowed to move past the left and right wall
        if (x + xa > 0 && x + xa < game.getWidth()-WIDTH)
            x = x + xa;
    }

    //Function used to create the racquet sprite
    public void paint (Graphics2D g){
        g.fillRect(x, Y,WIDTH,HEIGHT);
    }

    //Function is used to set the racquet speed to zero when a key is released
    public void keyReleased(KeyEvent e){
        xa = 0;
    }

    //Function is used to the move the racquet left and right when
    //the left and right arrow keys are pressed
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = -1;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = 1;
    }

    //Function used to set the boundaries of the racquet used for collision
    public Rectangle getBounds(){
        return new Rectangle(x,Y,WIDTH,HEIGHT);
    }

    //Function used to get the location of the very top of the racquet
    //so the ball bounces off of it properly
    public int getTopY(){
        return Y - HEIGHT;
    }
}
