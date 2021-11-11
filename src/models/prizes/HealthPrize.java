package models.prizes;

import panels.mainFramePanels.GamePanel;

import javax.swing.*;
import java.awt.*;

public class HealthPrize extends Prize{
    public HealthPrize(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void onPrize(GamePanel gamePanel) {
        gamePanel.getScore().increaseHeal();
    }

    @Override
    public void offPrize(GamePanel gamePanel) {
    }

    @Override
    public void draw(Graphics g) {
        ImageIcon imageIcon = new ImageIcon("images/heart.png");
        Image image = imageIcon.getImage();
        g.drawImage(image, x, y, width, height, null);
    }

    @Override
    public Prize clone() {
        return new HealthPrize(x, y, width, height);
    }
}
