package models.prizes.paddlePrizes;

import models.Paddle;
import models.prizes.Prize;
import panels.mainFramePanels.GamePanel;

import java.awt.*;

public abstract class PaddlePrize extends Prize {

    public PaddlePrize(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void onPrize(GamePanel gamePanel) {
        onPrize(gamePanel.getPaddle());
    }

    @Override
    public void offPrize(GamePanel gamePanel) {
        offPrize(gamePanel.getPaddle());
    }

    public abstract void onPrize(Paddle paddle);

    public abstract void offPrize(Paddle paddle);

    @Override
    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }
}
