package models;

import java.awt.*;

public class Ball extends Rectangle {
    private int xVelocity;
    private int yVelocity;
    private int initialSpeed = -1;

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

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }
}
