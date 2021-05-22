package models.prizes.paddlePrizes;

import models.Paddle;

import java.awt.*;

public class BigPaddle extends PaddlePrize {

    public BigPaddle(int x, int y, int width, int height, int time) {
        this(x, y, width, height);
        this.time = time;
    }

    public BigPaddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void onPrize(Paddle paddle) {
        paddle.setBigCounter(paddle.getBigCounter() + 1);
    }

    @Override
    public void offPrize(Paddle paddle) {
        if (paddle.getBigCounter() > 0)
            paddle.setBigCounter(paddle.getBigCounter() - 1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        super.draw(g);
    }

    @Override
    public BigPaddle clone() {
        return new BigPaddle(x, y, width, height, time);
    }
}
