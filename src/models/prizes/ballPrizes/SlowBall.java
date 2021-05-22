package models.prizes.ballPrizes;

import models.Ball;

import java.awt.*;
import java.util.ArrayList;

public class SlowBall extends BallPrize {

    public SlowBall(int x, int y, int width, int height, int time) {
        this(x, y, width, height);
        this.time = time;
    }

    public SlowBall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void offPrize(ArrayList<Ball> balls) {
        for (Ball ball : balls) {
            if (ball.getSlowCounter() > 0)
                ball.setSlowCounter(ball.getSlowCounter() - 1);
        }
    }

    @Override
    public void onPrize(ArrayList<Ball> balls) {
        for (Ball ball : balls) {
            ball.setSlowCounter(ball.getSlowCounter() + 1);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        super.draw(g);
    }

    @Override
    public SlowBall clone() {
        return new SlowBall(x, y, width, height, time);
    }
}
