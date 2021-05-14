package models;

import java.awt.*;

public class Paddle extends Rectangle {

    private int xVelocity;
    private int initialSpeed = 1;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
        setXVelocity(initialSpeed);
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
