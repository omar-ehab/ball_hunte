package ball;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.*;
import javax.swing.*;

public class Control extends JPanel implements ActionListener {

    JButton start = new JButton();
    ImageIcon image;
    public Ball[] balls = new Ball[6];
    public Arrow arrow;
    public static ExecutorService pool = Executors.newFixedThreadPool(7);
    boolean flag = false;

    public Control(int width, int height) {
        
        this.add(Main.shootBtn);
        Main.shootBtn.setBounds(350, Main.height - 150, 200, 60);
        Main.shootBtn.addActionListener((ActionListener) this);
        
        this.add(this.start);
        this.start.setBounds(Main.width / 2 - 100, 600, 200, 30);
        this.start.addActionListener((ActionListener) this);
        
        this.setLayout(new BorderLayout());
        arrow = new Arrow(Main.height - 80, balls);
        
        balls[0] = new Ball(Main.width / 2 + 60, 275, arrow);
        balls[1] = new Ball(Main.width / 2, 245, arrow);
        balls[2] = new Ball(Main.width / 2, 305, arrow);
        balls[3] = new Ball(Main.width / 2 - 60 ,245, arrow);
        balls[4] = new Ball(Main.width / 2 - 60, 305, arrow);
        balls[5] = new Ball(Main.width / 2 - 120, 275, arrow);

    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        image = new ImageIcon(getClass().getResource("Board.png"));
        graphics.drawImage(image.getImage(), 0, 40, Main.width, Main.height - 120, null);
        image = new ImageIcon(getClass().getResource("bow.png"));
        graphics.drawImage(image.getImage(), Main.width / 2 - 100, 600, 200, 30, null);
        image = new ImageIcon(getClass().getResource("start.png"));
        graphics.drawImage(image.getImage(), 350, Main.height - 150, 200, 60, null);

        for (int i = 0; i < balls.length; i++) {
            image = new ImageIcon(getClass().getResource("Ball" + (i + 1) + ".png"));
            balls[i].draw(graphics, image.getImage());
        }
        
        image = new ImageIcon(getClass().getResource("arrow.png"));
        arrow.draw(graphics, image.getImage());
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Main.shootBtn && arrow.numberOfArrows > 0) {

            for (int i = 0; i < balls.length; i++) {
                if (!balls[i].isActive) {
                    pool.execute(balls[i]);
                }

            }
            flag = true;
        }
        if (e.getSource() == this.start && arrow.numberOfArrows > 0) {
            if (!arrow.isActive && flag) {
                pool.execute(arrow);
            }
        }
    }
}
