package ball;

import java.awt.Graphics;
import java.awt.Image;

public class Arrow implements Runnable {

    int X, Y, returnPoing;
    int numberOfArrows = 10, score = 0;
    boolean isActive = false;
    boolean isMove = false;
    
    float dy = -1;
    Ball[] balls;
    int i;

    public Arrow(int y, Ball[] b) {
        Y = y;
        returnPoing = y;
        X = Main.width / 2;
        balls = b;
    }

    public void draw(Graphics graphics, Image image) {
        graphics.drawImage(image, X-10, Y,  20, 80, null);
    }

    public void move() {
        isMove = true;
        
        int x1 = X + 10;
        int x2 = X - 10;
        
        for (i = 0; i < balls.length; i++) {

            if (balls[i].isActive && Y == balls[i].Y + 2 * balls[i].radius && x1 >= balls[i].X && x2 <= balls[i].X + 2 * balls[i].radius) {
                balls[i].isActive = false;
                score += i + 1;
                String scoreText = "" + score;
                Main.scoreLabel.setText(scoreText);
                System.out.println("You Are Hit Ball Number: " + (i + 1));
                break;
            }
        }

        
        int y1 = 40;
        Y = (int) (Y + dy);
        if (Y <= y1 || i < balls.length) {
            Y = returnPoing;
        }
        

        for (i = 0; i < balls.length && Y != returnPoing; i++) {
            if (balls[i].isActive && Y == balls[i].Y + 2 * balls[i].radius && x1 >= balls[i].X && x2 <= balls[i].X + 2 * balls[i].radius) {
                balls[i].isActive = false;
                score += i + 1;
                String scoreText = "" + score;
                Main.scoreLabel.setText(scoreText);
                System.out.println("You Are Hit Ball Number: " + (i + 1));
                break;
            }

        }
        if (i < balls.length) {
            Y = returnPoing;
        }
        isMove = false;
        
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
        }

    }

    @Override
    public void run() {
        isActive = true;
        while (numberOfArrows > 0) {
            this.move();
            if (Y == returnPoing) {
                break;
            }
        }
        numberOfArrows--;
        String arrowText = "" + numberOfArrows;
        Main.arrowsLabel.setText(arrowText);
        System.out.println("Score:" + score);
        System.out.println("Number Of Arrows:" + numberOfArrows);
        isActive = false;
    }
}
