package models.prizes;

import panels.mainFramePanels.GamePanel;

import java.awt.*;

public abstract class Prize extends Rectangle {

    private static final int Y_VELOCITY = 3;
    protected int time = 20_000;

    public Prize(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public abstract void onPrize(GamePanel gamePanel);

    public abstract void offPrize(GamePanel gamePanel);

    public abstract void draw(Graphics g);

    public void move() {
        y += Y_VELOCITY;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
