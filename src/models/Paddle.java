package models;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {

    private static final int INITIAL_SPEED = 3;
    private static final int PADDLE_WIDTH = 64;

    private int xVelocity;
    private boolean isNormal;
    private int bigCounter;
    private int smallCounter;

    public Paddle(int x, int y, int width, int height, int xVelocity, boolean isNormal, int bigCounter, int smallCounter) {
        super(x, y, width, height);
        this.xVelocity = xVelocity;
        this.isNormal = isNormal;
        this.bigCounter = bigCounter;
        setSmallCounter(smallCounter);
    }

    public Paddle(int x, int y, int width, int height) {
        this(x, y, width, height, 0, true, 0, 0);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (isNormal) setXVelocity(xVelocity + INITIAL_SPEED);
            else setXVelocity(xVelocity - INITIAL_SPEED);
            move();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (isNormal) setXVelocity(xVelocity - INITIAL_SPEED);
            else setXVelocity(xVelocity + INITIAL_SPEED);
            move();
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            setXVelocity(0);
            move();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            setXVelocity(0);
            move();
        }
    }

    public void move() {
        x += xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public boolean isNormal() {
        return isNormal;
    }

    public void setNormal(boolean normal) {
        isNormal = normal;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setWidth() {
        width = (int) (PADDLE_WIDTH * Math.pow(2, bigCounter - smallCounter));
    }

    public void setBigCounter(int bigCounter) {
        this.bigCounter = bigCounter;
        setWidth();
    }

    public void setSmallCounter(int smallCounter) {
        this.smallCounter = smallCounter;
        setWidth();
    }

    public int getBigCounter() {
        return bigCounter;
    }

    public int getSmallCounter() {
        return smallCounter;
    }

    @Override
    public Paddle clone() {
        return new Paddle(x, y, width, height, xVelocity, isNormal, bigCounter, smallCounter);
    }
}
