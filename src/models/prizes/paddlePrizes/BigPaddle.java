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
        paddle.width = paddle.width * 2;
    }

    @Override
    public void offPrize(Paddle paddle) {
        paddle.width = paddle.width / 2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        super.draw(g);
    }
}
