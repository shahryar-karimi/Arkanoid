package models;

import models.cells.Cell;
import models.cells.WinkCell;

import java.awt.*;

public class Ball extends Rectangle {
    private int xVelocity;
    private int yVelocity;
    private int initialSpeed = -3;

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        setXVelocity(initialSpeed);
        setYVelocity(initialSpeed);
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
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
        x += xVelocity;
        y += yVelocity;
    }

    public void intersectsTo(Cell cell) {
        if (cell instanceof WinkCell) {
            WinkCell winkCell = (WinkCell) cell;
            if (winkCell.isWink()) {
                return;
            }
        }
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
        cell.loseHeal();
    }

    public void intersectsTo(Paddle paddle) {
        int m = paddle.x + (paddle.width - width) / 2;
        xVelocity = (x - m) / 5;
        yVelocity = -Math.abs(yVelocity);
    }
}
