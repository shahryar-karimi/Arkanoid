package models.prizes.paddlePrizes;

import models.Paddle;

import java.awt.*;

public class SmallPaddle extends PaddlePrize {

    public SmallPaddle(int x, int y, int width, int height, int time) {
        this(x, y, width, height);
        this.time = time;
    }

    public SmallPaddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void onPrize(Paddle paddle) {
        paddle.setSmallCounter(paddle.getSmallCounter() + 1);
    }

    @Override
    public void offPrize(Paddle paddle) {
        if (paddle.getSmallCounter() > 0)
            paddle.setSmallCounter(paddle.getSmallCounter() - 1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        super.draw(g);
    }

    @Override
    public SmallPaddle clone() {
        return new SmallPaddle(x, y, width, height, time);
    }
}
