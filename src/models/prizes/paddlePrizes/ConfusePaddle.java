package models.prizes.paddlePrizes;

import models.Paddle;

import java.awt.*;

public class ConfusePaddle extends PaddlePrize{

    public ConfusePaddle(int x, int y, int width, int height, int time) {
        this(x, y, width, height);
        this.time = time;
    }

    public ConfusePaddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void onPrize(Paddle paddle) {
        paddle.setNormal(false);
    }

    @Override
    public void offPrize(Paddle paddle) {
        paddle.setNormal(true);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        super.draw(g);
    }

    @Override
    public ConfusePaddle clone() {
        return new ConfusePaddle(x, y, width, height, time);
    }
}
