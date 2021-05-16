package panels;

import models.*;
import models.cells.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private static final int PANEL_WIDTH = 509;
    private static final int PANEL_HEIGHT = 509;
    private static final Dimension SCREEN_SIZE = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    private static final int PADDLE_WIDTH = 50;
    private static final int PADDLE_HEIGHT = 10;
    private static final int BALL_DIAMETER = 10;
    private static final int CELL_WIDTH = 30;
    private static final int CELL_HEIGHT = 20;

    private Paddle paddle;
    private Ball ball;
    private Score score;
    private int playerHeal;
    private ArrayList<Cell> cells;


    public GamePanel() {
        this.playerHeal = 3;
        this.score = new Score(0, 0, PANEL_WIDTH, PANEL_HEIGHT, 0);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.BLACK);
        newPaddle();
        newBall();
        newCells();

    }

    private void newPaddle() {
        paddle = new Paddle((PANEL_WIDTH - PADDLE_WIDTH) / 2, PANEL_HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    private void newBall() {
        ball = new Ball((PANEL_WIDTH - BALL_DIAMETER) / 2, PANEL_HEIGHT - PADDLE_HEIGHT - BALL_DIAMETER, BALL_DIAMETER, BALL_DIAMETER);
    }

    private void newCells() {
        cells = new ArrayList<>();
        int n = 10;
        int d = (PANEL_WIDTH - n * 30) / (n + 1);
        for (int i = 0; i < n; i++) {
            cells.add(new WoodenCell(d, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
            cells.add(new GlassyCell(100 + d, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
            cells.add(new InvisibleCell(200 + d, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
            cells.add(new PrizeCell(300 + d, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
            cells.add(new WinkCell(400 + d, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));

            cells.add(new WoodenCell(350 + d, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
            cells.add(new GlassyCell(250 + d, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
            cells.add(new InvisibleCell(450 + d, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
            cells.add(new PrizeCell(150 + d, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
            cells.add(new WinkCell(50 + d, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
        }
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
        cellDraw(g);
    }

    private void cellDraw(Graphics g) {
        for (Cell cell : cells) cell.draw(g);
    }

    private void moveCells() {
        for (Cell cell : cells) cell.y++;
    }

    public void checkCollision() {
        if (cells.size() == 0) {
            JOptionPane.showConfirmDialog(null, "You won!", "Game result", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        for (int i = 0; i < cells.size(); i++) {
            if (ball.intersects(cells.get(i))) {
                if (ball.y < cells.get(i).y || ball.y + ball.height > cells.get(i).y + cells.get(i).height) {
                    ball.setYVelocity(-ball.getYVelocity());
                }
                if (ball.x < cells.get(i).x || ball.x + ball.width > cells.get(i).x + cells.get(i).width) {
                    ball.setXVelocity(-ball.getXVelocity());
                }
                cells.get(i).loseHeal();
                if (cells.get(i).isDead()) {
                    score.setScore(score.getScore() + cells.get(i).getScore());
                    cells.remove(i);
                    i--;
                }
            }
        }
        //todo do angle
        if (ball.intersects(paddle)) {
            moveCells();
            ball.setYVelocity(-Math.abs(ball.getYVelocity()));
            //todo improve velocity
        }

        if (ball.x <= 0 || ball.x >= PANEL_WIDTH - BALL_DIAMETER) ball.setXVelocity(-ball.getXVelocity());
        if (ball.y <= 0) ball.setYVelocity(-ball.getYVelocity());
        if (ball.y >= PANEL_HEIGHT - BALL_DIAMETER) {
            playerHeal--;
            if (playerHeal > 0) {
                newPaddle();
                newBall();
            } else {
                JOptionPane.showMessageDialog(null, "Game Over", "Game result", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }

        if (paddle.x <= 0) paddle.x = 0;
        if (paddle.x >= PANEL_WIDTH - PADDLE_WIDTH) paddle.x = PANEL_WIDTH - PADDLE_WIDTH;

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
