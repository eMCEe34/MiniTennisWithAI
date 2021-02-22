package minitennis;

import java.awt.*;

public class Ball {
    private static final int DIAMETER = 30;
    int x = 0;
    int y = 0;
    int xa = 1;
    int ya = 1;
    private Main game;

    //Constructor that sets the game variable here to our Main class
    public Ball(Main game){
        this.game=game;
    }

    //move function used for the ball's movement, game over, and collision
    public int move(){
        //If the ball hits the left wall, bounce to the right
        if(x + xa < 0)
            xa = game.speed;
        //If the ball hits the right wall, bounce to the left
        else if(x + xa > game.getWidth() - DIAMETER)
            xa = -game.speed;
        //If the ball hits the ceiling, bounce down
        else if(y + ya < 0)
            ya = game.speed;
        //If the ball hits the floor, getting past the racquet, gameOver() is called
        else if(y + ya > game.getHeight() - DIAMETER)
            game.gameOver();
        //If the ball and racquet collide, bounce the ball back up and increase the ball speed
        else if(collision()){
            ya = -game.speed;
            y = game.racquet.getTopY() - DIAMETER;
            game.speed++;
        }else if(collisionAI()){
            ya = game.speed;
            y = game.racquetAi.getBottomY() - DIAMETER;
            game.speed++;
        }
        //Sets movement direction and speed of the ball
        x=x+xa;
        y=y+ya;

        return x;
    }

    //Function used to detect if the ball and racquet collide
    private boolean collision(){
        return game.racquet.getBounds().intersects(getBounds());
    }

    private boolean collisionAI(){
        return game.racquetAi.getBounds().intersects(getBounds());
    }

    //Function used to create the ball sprite
    public void paint(Graphics2D g){
        g.fillOval(x,y,DIAMETER,DIAMETER);
    }

    //Function used to set the boundaries of the racquet to use for collision
    public Rectangle getBounds(){
        return new Rectangle(x,y,DIAMETER,DIAMETER);
    }
}
