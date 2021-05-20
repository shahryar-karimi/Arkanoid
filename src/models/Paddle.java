package models;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {

    private static final int INITIAL_SPEED = 3;

    private int xVelocity;
    private boolean isNormal;

    public Paddle(int x, int y, int width, int height, int xVelocity, boolean isNormal) {
        super(x, y, width, height);
        this.xVelocity = xVelocity;
        this.isNormal = isNormal;
    }

    public Paddle(int x, int y, int width, int height) {
        this(x, y, width, height, 0, true);
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

    @Override
    public Paddle clone() {
        return new Paddle(x, y, width, height, xVelocity, isNormal);
    }
}
