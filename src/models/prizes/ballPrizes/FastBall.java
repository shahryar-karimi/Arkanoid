package models.prizes.ballPrizes;

import models.Ball;

import java.awt.*;
import java.util.ArrayList;

public class FastBall extends BallPrize {

    public FastBall(int x, int y, int width, int height, int time) {
        this(x, y, width, height);
        this.time = time;
    }

    public FastBall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void offPrize(ArrayList<Ball> balls) {
        for (Ball ball : balls) {
            if (ball.getFastCounter() > 0)
                ball.setFastCounter(ball.getFastCounter() - 1);
        }
    }

    @Override
    public void onPrize(ArrayList<Ball> balls) {
        for (Ball ball : balls)
            ball.setFastCounter(ball.getFastCounter() + 1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        super.draw(g);
    }

    @Override
    public FastBall clone() {
        return new FastBall(x, y, width, height, time);
    }
}
