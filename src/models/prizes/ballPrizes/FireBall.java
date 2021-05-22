package models.prizes.ballPrizes;

import models.Ball;

import java.awt.*;
import java.util.ArrayList;

public class FireBall extends BallPrize {

    public FireBall(int x, int y, int width, int height, int time) {
        this(x, y, width, height);
        this.time = time;
    }

    public FireBall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void offPrize(ArrayList<Ball> balls) {
        for (Ball ball : balls) {
            ball.setFire(false);
        }
    }

    @Override
    public void onPrize(ArrayList<Ball> balls) {
        for (Ball ball : balls) {
            ball.setFire(true);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        super.draw(g);
    }

    @Override
    public FireBall clone() {
        return new FireBall(x, y, width, height, time);
    }
}
