package models.prizes.ballPrizes;

import models.Ball;
import models.prizes.Prize;
import panels.mainFramePanels.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public abstract class BallPrize extends Prize {
    public BallPrize(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void onPrize(GamePanel gamePanel) {
        onPrize(gamePanel.getBalls());
    }

    @Override
    public void offPrize(GamePanel gamePanel) {
        offPrize(gamePanel.getBalls());
    }

    public abstract void onPrize(ArrayList<Ball> balls);

    public abstract void offPrize(ArrayList<Ball> balls);

    @Override
    public void draw(Graphics g) {
        g.fillOval(x, y, width, height);
    }
}
