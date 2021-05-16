package models;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {

    private int xVelocity;
    private int initialSpeed = 3;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
        setXVelocity(0);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            setXVelocity(xVelocity + initialSpeed);
            move();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            setXVelocity(xVelocity - initialSpeed);
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

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
}
