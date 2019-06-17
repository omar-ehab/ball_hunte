package ball;

import java.awt.*;

public class Ball implements Runnable {

    public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange + 1));
    }
    
    boolean isActive = false;
    int X;
    int Y;
    int radius = 30;

    // Direction
    int dx;
    int dy;
    Arrow arrow;

    public Ball(int x, int y, Arrow arrow) {
        dy = 1;
        dx = 1;
        this.X = x;
        this.Y = y;
        this.arrow = arrow;
    }

    public void draw(Graphics graphics, Image image) {
        graphics.drawImage(image, X, Y, (2 * this.radius), (2 * this.radius), null);
    }

    public void move() {
        if (arrow.isMove == false) {
            int width = Main.width-10;
            int height = Main.height - 80;
            int Xpos = 0;
            int Ypos = 40;

            X = (int) (X + dx);
            Y = (int) (Y + dy);

            if (X < Xpos) {
                dx = -dx;
                X = Xpos;
            } else if (X + 2 * radius > width) {
                dx = -dx;
                X = width - 2 * radius;
            }

            if (Y < Ypos) {
                dy = -dy;
                Y = Ypos;
            } else if (Y + 2 * radius > height) {
                dy = -dy;
                Y = height - 2 * radius;
            }

            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
            }
        }
    }

    @Override
    public void run() {
        isActive = true;
        X = random(200 * Main.width / 257 - 10 * Main.height / 99) + 9 * Main.width / 257;
        Y = random(80 * Main.height / 99) + Main.width / 22;
        
        while (isActive && arrow.numberOfArrows > 0) {
            this.move();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        
        if (arrow.numberOfArrows > 0) {
            Control.pool.execute(this);
        }
    }

}
