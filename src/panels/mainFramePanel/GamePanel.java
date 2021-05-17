package panels.mainFramePanel;

import logic.Manager;
import logic.Player;
import models.*;
import models.cells.*;

import javax.swing.*;
import java.awt.*;
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
    private static final int CELLS_ROW = 10;
    private static final int CELLS_COLUMNS = 10;
    private static final int EMPTY_DISTANCE = (PANEL_WIDTH - CELLS_COLUMNS * 30) / (CELLS_COLUMNS + 1);

    private Paddle paddle;
    private Ball ball;
    private Player player;
    private ArrayList<Cell> cells;
    private Manager manager;
    private boolean isGameOver;


    public GamePanel(Player player, Manager manager) {
        this.isGameOver = false;
        this.manager = manager;
        this.player = player;
        this.player.setScore(new Score(0, 0, PANEL_WIDTH, PANEL_HEIGHT, 0));
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.BLACK);
        reset();
    }

    private void newPaddle() {
        paddle = new Paddle((PANEL_WIDTH - PADDLE_WIDTH) / 2, PANEL_HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    private void newBall() {
        ball = new Ball((PANEL_WIDTH - BALL_DIAMETER) / 2, PANEL_HEIGHT - PADDLE_HEIGHT - BALL_DIAMETER, BALL_DIAMETER, BALL_DIAMETER);
    }

    private void newCells() {
        cells = new ArrayList<>();
        for (int i = 0; i < CELLS_ROW; i++) {
            makeCell(i);
        }
    }

    private void makeCell(int i) {
        cells.add(new WoodenCell(EMPTY_DISTANCE, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
        cells.add(new GlassyCell(100 + EMPTY_DISTANCE, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
        cells.add(new InvisibleCell(200 + EMPTY_DISTANCE, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
        cells.add(new PrizeCell(300 + EMPTY_DISTANCE, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
        cells.add(new WinkCell(400 + EMPTY_DISTANCE, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));

        cells.add(new WoodenCell(350 + EMPTY_DISTANCE, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
        cells.add(new GlassyCell(250 + EMPTY_DISTANCE, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
        cells.add(new InvisibleCell(450 + EMPTY_DISTANCE, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
        cells.add(new PrizeCell(150 + EMPTY_DISTANCE, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
        cells.add(new WinkCell(50 + EMPTY_DISTANCE, (i + 1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
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
        player.getScore().draw(g);
        cellDraw(g);
        ball.draw(g);
    }

    private void cellDraw(Graphics g) {
        for (Cell cell : cells) cell.draw(g);
    }

    public void moveCells() {
        for (Cell cell : cells) cell.y += CELL_HEIGHT;
        makeCell(0);
    }

    public void checkCollision() {
        if (cells.size() == 0) {

            gameOver(true);
        }

        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            if (ball.intersects(cell)) {
                ball.intersectsTo(cell);
                if (cell.isDead()) {
                    player.getScore().setScore(player.getScore().getScore() + cell.getScore());
                    cells.remove(i);
                    i--;
                }
            }
            if (cell.intersectsLine(0, 9 * PANEL_HEIGHT / 10, PANEL_WIDTH, 9 * PANEL_HEIGHT / 10)) {
                gameOver(false);
            }
        }
        if (ball.intersects(paddle)) {
            ball.intersectsTo(paddle);
        }

        if (ball.x <= 0 || ball.x >= PANEL_WIDTH - BALL_DIAMETER) ball.setXVelocity(-ball.getXVelocity());
        if (ball.y <= 0) ball.setYVelocity(-ball.getYVelocity());
        if (ball.y >= PANEL_HEIGHT - BALL_DIAMETER) {
            player.getScore().loseHeal();
            if (player.getScore().getHeal() > 0) {
                newPaddle();
                newBall();
            } else {
                gameOver(false);
            }
        }

        if (paddle.x <= 0) paddle.x = 0;
        if (paddle.x >= PANEL_WIDTH - PADDLE_WIDTH) paddle.x = PANEL_WIDTH - PADDLE_WIDTH;

    }

    public void gameOver(boolean result) {
        if (result) {
            gameOver("You won!\nYour score is: " + player.getScore().getScore());
        } else {
            gameOver("Game Over\nYour score is: " + player.getScore().getScore());
        }
    }

    private void gameOver(String message) {
        this.isGameOver = true;
        JOptionPane.showMessageDialog(null, message, "Game result", JOptionPane.INFORMATION_MESSAGE);
        player.addScore();
        reset();
        player.setScore(new Score(0, 0, PANEL_WIDTH, PANEL_HEIGHT, 0));
        manager.save();
    }

    public void restart() {
        this.isGameOver = false;
        reset();
        player.setScore(new Score(0, 0, PANEL_WIDTH, PANEL_HEIGHT, 0));
        repaint();
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public Ball getBall() {
        return ball;
    }

    public void wink() {
        for (Cell cell : cells) {
            if (cell instanceof WinkCell) {
                ((WinkCell) cell).wink();
            }
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    private void reset() {
        newPaddle();
        newCells();
        newBall();
    }
}
