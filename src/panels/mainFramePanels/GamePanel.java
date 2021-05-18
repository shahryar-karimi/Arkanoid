package panels.mainFramePanels;

import logic.Manager;
import logic.Player;
import models.*;
import models.cells.*;
import models.prizes.Prize;
import models.prizes.RandomPrize;
import models.prizes.ballPrizes.FastBall;
import models.prizes.ballPrizes.FireBall;
import models.prizes.ballPrizes.MultiBall;
import models.prizes.ballPrizes.SlowBall;
import models.prizes.paddlePrizes.BigPaddle;
import models.prizes.paddlePrizes.ConfusePaddle;
import models.prizes.paddlePrizes.SmallPaddle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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
    private static final int PRIZE_DIAMETER = 10;

    private Paddle paddle;
    private ArrayList<Ball> balls;
    private ArrayList<Cell> cells;
    private ArrayList<Prize> prizes;
    private ArrayList<Prize> tokenPrizes;
    private Player player;
    private Manager manager;
    private boolean isGameOver;
    private Random random;


    public GamePanel(Player player, Manager manager) {
        this.random = new Random();
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
        Ball ball = new Ball((PANEL_WIDTH - BALL_DIAMETER) / 2, PANEL_HEIGHT - PADDLE_HEIGHT - BALL_DIAMETER, BALL_DIAMETER, BALL_DIAMETER);
        balls.add(ball);
    }

    private void newCells() {
        cells = new ArrayList<>();
        for (int i = 0; i < CELLS_ROW; i++) {
            makeCell(i, i);
        }
    }

    private void makeCell(int i, int c) {
        int x = EMPTY_DISTANCE;
        int y = (i + 1) * CELL_HEIGHT;
        cells.add(new WoodenCell(x, y, CELL_WIDTH, CELL_HEIGHT));
        x += 50;
        cells.add(new WinkCell(x, y, CELL_WIDTH, CELL_HEIGHT));
        x += 50;
        cells.add(new GlassyCell(x, y, CELL_WIDTH, CELL_HEIGHT));
        x += 50;
        cells.add(new PrizeCell(x, y, CELL_WIDTH, CELL_HEIGHT, getPrize(x, y, c)));
        x += 50;
        cells.add(new InvisibleCell(x, y, CELL_WIDTH, CELL_HEIGHT));
        x += 50;
        cells.add(new GlassyCell(x, y, CELL_WIDTH, CELL_HEIGHT));
        x += 50;
        cells.add(new PrizeCell(x, y, CELL_WIDTH, CELL_HEIGHT, getPrize(x, y, c)));
        x += 50;
        cells.add(new WoodenCell(x, y, CELL_WIDTH, CELL_HEIGHT));
        x += 50;
        cells.add(new WinkCell(x, y, CELL_WIDTH, CELL_HEIGHT));
        x += 50;
        cells.add(new InvisibleCell(x, y, CELL_WIDTH, CELL_HEIGHT));
    }

    private Prize getPrize(int x, int y, int i) {
        Prize prize;
        switch (i % 8) {
            case 0 -> prize = new FastBall(x, y, PRIZE_DIAMETER, PRIZE_DIAMETER);
            case 1 -> prize = new FireBall(x, y, PRIZE_DIAMETER, PRIZE_DIAMETER);
            case 2 -> prize = new MultiBall(x, y, PRIZE_DIAMETER, PRIZE_DIAMETER);
            case 3 -> prize = new SlowBall(x, y, PRIZE_DIAMETER, PRIZE_DIAMETER);
            case 4 -> prize = new BigPaddle(x, y, PRIZE_DIAMETER, PRIZE_DIAMETER);
            case 5 -> prize = new ConfusePaddle(x, y, PRIZE_DIAMETER, PRIZE_DIAMETER);
            case 6 -> prize = new SmallPaddle(x, y, PRIZE_DIAMETER, PRIZE_DIAMETER);
            default -> prize = new RandomPrize(x, y, PRIZE_DIAMETER, PRIZE_DIAMETER);
        }
        return prize;
    }

    public void move() {
        for (Ball ball : balls) ball.move();
        for (Prize prize : prizes) prize.move();
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
        prizeDraw(g);
        ballDraw(g);
    }

    private void prizeDraw(Graphics g) {
        for (Prize prize : prizes) prize.draw(g);
    }

    private void ballDraw(Graphics g) {
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }

    private void cellDraw(Graphics g) {
        for (Cell cell : cells) cell.draw(g);
    }

    public void moveCells() {
        for (Cell cell : cells) cell.y += CELL_HEIGHT;
        makeCell(0, random.nextInt(8));
    }

    public void checkCollision() {
        if (cells.size() == 0) gameOver(true);
        for (int j = 0; j < balls.size(); j++) {
            Ball ball = balls.get(j);
            for (int i = 0; i < cells.size(); i++) {
                Cell cell = cells.get(i);
                if (ball.intersects(cell)) {
                    ball.intersectsTo(cell);
                    if (cell.isDead()) {
                        if (cell instanceof PrizeCell) {
                            prizes.add(((PrizeCell) cell).getPrize());
                        }
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
                balls.remove(ball);
                j--;
                if (balls.size() == 0) {
                    player.getScore().loseHeal();
                    if (player.getScore().getHeal() > 0) {
                        newPaddle();
                        newBall();
                    } else {
                        gameOver(false);
                    }
                }
            }
        }

        if (paddle.x <= 0) paddle.x = 0;
        if (paddle.x >= PANEL_WIDTH - paddle.width) paddle.x = PANEL_WIDTH - paddle.width;

        for (int i = 0; i < prizes.size(); i++) {
            Prize prize = prizes.get(i);
            if (prize.y >= PANEL_HEIGHT) {
                prizes.remove(i);
                i--;
            } else if (prize.intersects(paddle)) {
                Prize p = findPrize(prize);
                if (p == null) {
                    prize.onPrize(this);
                    tokenPrizes.add(prize);
                } else {
                    p.setTime(20_000);
                }
                prizes.remove(i);
                i--;
            }
        }
    }

    private Prize findPrize(Prize prize) {
        Prize p;
        if (prize instanceof FastBall) {
            p = hasFastBall();
        } else if (prize instanceof FireBall) {
            p = hasFireBall();
        } else if (prize instanceof MultiBall) {
            p = hasMultiBall();
        } else if (prize instanceof SlowBall) {
            p = hasSlowBall();
        } else if (prize instanceof BigPaddle) {
            p = hasBigPaddle();
        } else if (prize instanceof ConfusePaddle) {
            p = hasConfusePaddle();
        } else if (prize instanceof SmallPaddle) {
            p = hasSmallPaddle();
        } else {
            p = findPrize(((RandomPrize) prize).getPrize());
        }
        return p;
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

    public ArrayList<Ball> getBalls() {
        return balls;
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
        this.prizes = new ArrayList<>();
        this.tokenPrizes = new ArrayList<>();
        this.balls = new ArrayList<>();
        newPaddle();
        newCells();
        newBall();
    }

    public static int getPanelWidth() {
        return PANEL_WIDTH;
    }

    public static int getPanelHeight() {
        return PANEL_HEIGHT;
    }

    public static int getBallDiameter() {
        return BALL_DIAMETER;
    }

    public static int getPaddleWidth() {
        return PADDLE_WIDTH;
    }

    public static int getPaddleHeight() {
        return PADDLE_HEIGHT;
    }

    public ArrayList<Prize> getTokenPrizes() {
        return tokenPrizes;
    }

    public FastBall hasFastBall() {
        for (Prize prize : tokenPrizes) {
            if (prize instanceof FastBall) return (FastBall) prize;
        }
        return null;
    }

    public FireBall hasFireBall() {
        for (Prize prize : tokenPrizes) {
            if (prize instanceof FireBall) return (FireBall) prize;
        }
        return null;
    }

    public MultiBall hasMultiBall() {
        for (Prize prize : tokenPrizes) {
            if (prize instanceof MultiBall) return (MultiBall) prize;
        }
        return null;
    }

    public SlowBall hasSlowBall() {
        for (Prize prize : tokenPrizes) {
            if (prize instanceof SlowBall) return (SlowBall) prize;
        }
        return null;
    }

    public BigPaddle hasBigPaddle() {
        for (Prize prize : tokenPrizes) {
            if (prize instanceof BigPaddle) return (BigPaddle) prize;
        }
        return null;
    }

    public ConfusePaddle hasConfusePaddle() {
        for (Prize prize : tokenPrizes) {
            if (prize instanceof ConfusePaddle) return (ConfusePaddle) prize;
        }
        return null;
    }

    public SmallPaddle hasSmallPaddle() {
        for (Prize prize : tokenPrizes) {
            if (prize instanceof SmallPaddle) return (SmallPaddle) prize;
        }
        return null;
    }
}
