package models.prizes.ballPrizes;

import models.Ball;
import panels.mainFramePanels.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class MultiBall extends BallPrize{

    private Ball ball1;
    private Ball ball2;

    public MultiBall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void offPrize(ArrayList<Ball> balls) {}

    @Override
    public void onPrize(ArrayList<Ball> balls) {
        ball1 = new Ball(1,
                GamePanel.getPanelHeight() - GamePanel.getBallDiameter(),
                GamePanel.getBallDiameter(),
                GamePanel.getBallDiameter());
        ball2 = new Ball(GamePanel.getPanelWidth() - GamePanel.getBallDiameter(),
                GamePanel.getPanelHeight() - GamePanel.getBallDiameter(),
                GamePanel.getBallDiameter(),
                GamePanel.getBallDiameter());
        ball1.setInitialSpeedX(4);

        balls.add(ball1);
        balls.add(ball2);

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        super.draw(g);
    }
}
