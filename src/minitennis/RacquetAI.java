package minitennis;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class RacquetAI {
    private static final int Y = 50;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 10;
    int x = 200;
    int xa = 2;
    int score = 0;
    private Main game;

    public RacquetAI(Main game){
        this.game = game;
    }

    public void paint(Graphics2D g){
        g.fillRect(x, Y ,WIDTH,HEIGHT);
    }

    public void move(int xb){
        if(x + xa > 0 && x + xa < game.getWidth()-WIDTH) {
            if (xb > x) {
                x = x + xa;
            } else if (xb < x) {
                x = x + -xa;
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }
    public int getBottomY(){
            return Y;
        }
    }


