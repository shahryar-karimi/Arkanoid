package models;

import models.cells.Cell;
import models.cells.WinkCell;

import java.awt.*;

public class Ball extends Rectangle {
    private static final int INITIAL_SPEED_Y = -4;

    private int xVelocity;
    private int yVelocity;
    private boolean isFire;
    private int fastCounter;
    private int slowCounter;
    private double velocityRatio;

    public Ball(int x, int y, int width, int height, int xVelocity, int yVelocity, boolean isFire, int fastCounter, int slowCounter) {
        super(x, y, width, height);
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.isFire = isFire;
        this.fastCounter = fastCounter;
        this.slowCounter = slowCounter;
        setVelocityRatio();
    }

    public Ball(int x, int y, int width, int height) {
        this(x, y, width, height, -4, INITIAL_SPEED_Y, false, 0, 0);
    }

    public void draw(Graphics g) {
        if (isFire) draw(g, Color.RED);
        else draw(g, Color.WHITE);
    }

    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void move() {
        x += velocityRatio * xVelocity;
        y += velocityRatio * yVelocity;
    }

    public void intersectsTo(Cell cell) {
        if (cell instanceof WinkCell) {
            WinkCell winkCell = (WinkCell) cell;
            if (winkCell.isWink()) {
                return;
            }
        }
        if (!isFire) {
            if (x >= cell.x && x + width <= cell.x + cell.width) {
                setYVelocity(-getYVelocity());
            } else if (y >= cell.y && y + height <= cell.y + cell.height) {
                setXVelocity(-getXVelocity());
            } else if (x < cell.x && y < cell.y) {
                if (x + width - cell.x >= y + height - cell.y) {
                    setYVelocity(-Math.abs(getYVelocity()));
                }
                if (x + width - cell.x <= y + height - cell.y) {
                    setXVelocity(-Math.abs(getXVelocity()));
                }
            } else if (x < cell.x && y > cell.y) {
                if (x + width - cell.x >= cell.y + cell.height - y) {
                    setYVelocity(Math.abs(getYVelocity()));
                }
                if (x + width - cell.x <= cell.y + cell.height - y) {
                    setXVelocity(-Math.abs(getXVelocity()));
                }
            } else if (x > cell.x && y < cell.y) {
                if (cell.x + cell.width - x >= y + height - cell.y) {
                    setYVelocity(-Math.abs(getYVelocity()));
                }
                if (cell.x + cell.width - x <= y + height - cell.y) {
                    setXVelocity(Math.abs(getXVelocity()));
                }
            } else if (x > cell.x && y > cell.y) {
                if (cell.x + cell.width - x >= cell.y + cell.height - y) {
                    setYVelocity(Math.abs(getYVelocity()));
                }
                if (cell.x + cell.width - x <= cell.y + cell.height - y) {
                    setXVelocity(Math.abs(getXVelocity()));
                }
            }
        }
        cell.loseHeal();
    }

    public void intersectsTo(Paddle paddle) {
        int m = paddle.x + (paddle.width - width) / 2;
        xVelocity += (x - m) / 5;
        yVelocity = -Math.abs(yVelocity);
    }

    public boolean isFire() {
        return isFire;
    }

    public void setFire(boolean fire) {
        isFire = fire;
    }

    public int getFastCounter() {
        return fastCounter;
    }

    public void setFastCounter(int fastCounter) {
        this.fastCounter = fastCounter;
        setVelocityRatio();
    }

    public int getSlowCounter() {
        return slowCounter;
    }

    public void setSlowCounter(int slowCounter) {
        this.slowCounter = slowCounter;
        setVelocityRatio();
    }

    private void setVelocityRatio() {
        velocityRatio = Math.pow(2, fastCounter - slowCounter);
    }

    @Override
    public Ball clone() {
        return new Ball(x, y, width, height, xVelocity, yVelocity, isFire, fastCounter, slowCounter);
    }
}
