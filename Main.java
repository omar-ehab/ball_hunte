package ball;

import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    static int width, height;
    static JButton shootBtn = new JButton("Start");
    public static JLabel scoreLabel = new JLabel("0");
    public static JLabel arrowsLabel = new JLabel("10");

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Balls");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        width = 900;
        height = 650;
        frame.setSize(width, height + 40);
        Control control = new Control(width, height);
        frame.add(control);
        control.setLayout(null);
        JLabel label = new JLabel("Score: ");
        JLabel label2 = new JLabel("Number Of Arrows: ");
        label.setLocation(5, 5);
        label2.setLocation(70, 5);
        label.setSize(50,14);
        label2.setSize(120,14);
        scoreLabel.setLocation(45, 5);
        arrowsLabel.setLocation(183, 5);
        scoreLabel.setSize(50,14);
        arrowsLabel.setSize(50,14);
        control.add(label);
        control.add(label2);
        control.add(scoreLabel);
        control.add(arrowsLabel);
        frame.setVisible(true);
    }
}
