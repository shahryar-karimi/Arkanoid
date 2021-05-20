package models.prizes.ballPrizes;

import models.Ball;
import panels.mainFramePanels.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class MultiBall extends BallPrize{

    public MultiBall(int x, int y, int width, int height, int time) {
        this(x, y, width, height);
        this.time = time;
    }

    public MultiBall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void offPrize(ArrayList<Ball> balls) {}

    @Override
    public void onPrize(ArrayList<Ball> balls) {
        Ball ball1 = new Ball(1,
                GamePanel.getPanelHeight() - GamePanel.getBallDiameter(),
                GamePanel.getBallDiameter(),
                GamePanel.getBallDiameter());
        Ball ball2 = new Ball(GamePanel.getPanelWidth() - GamePanel.getBallDiameter(),
                GamePanel.getPanelHeight() - GamePanel.getBallDiameter(),
                GamePanel.getBallDiameter(),
                GamePanel.getBallDiameter());
        ball1.setXVelocity(4);

        balls.add(ball1);
        balls.add(ball2);

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        super.draw(g);
    }

    @Override
    public MultiBall clone() {
        return new MultiBall(x, y, width, height, time);
    }
}
