package models;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {

    private int xVelocity;
    private int initialSpeed = 3;
    private boolean isNormal;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
        setXVelocity(0);
        this.isNormal = true;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (isNormal) setXVelocity(xVelocity + initialSpeed);
            else setXVelocity(xVelocity - initialSpeed);
            move();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (isNormal) setXVelocity(xVelocity - initialSpeed);
            else setXVelocity(xVelocity + initialSpeed);
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
}
