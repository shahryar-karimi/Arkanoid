import models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {
    private static final int PANEL_WIDTH = 500;
    private static final int PANEL_HEIGHT = 500;
    private static final Dimension SCREEN_SIZE = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    private static final int PADDLE_WIDTH = 50;
    private static final int PADDLE_HEIGHT = 10;
    private static final int BALL_DIAMETER = 10;

    private Paddle paddle;
    private Ball ball;
    private Score score;
    private int playerHeal;


    public GamePanel() {
        this.playerHeal = 3;
        this.score = new Score(0, 0, PANEL_WIDTH, PANEL_HEIGHT, 0);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.BLACK);
        newPaddle();
        newBall();

    }

    private void newPaddle() {
        paddle = new Paddle((PANEL_WIDTH - PADDLE_WIDTH) / 2, PANEL_HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    private void newBall() {
        ball = new Ball((PANEL_WIDTH - BALL_DIAMETER) / 2, PANEL_HEIGHT - PADDLE_HEIGHT - BALL_DIAMETER, BALL_DIAMETER, BALL_DIAMETER);
    }

    public void move() {
        ball.move();
        paddle.move();
    }

    @Override
    public void paint(Graphics g) {
        draw(g);
    }

    public void draw(Graphics g) {
        paddle.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void checkCollision() {

    }

    public class AL implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }
    }
}
