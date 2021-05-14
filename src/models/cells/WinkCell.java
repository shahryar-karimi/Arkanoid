package models.cells;

import java.awt.*;

public class WinkCell extends Cell {
    private boolean isWink;

    public WinkCell(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.isWink = false;
    }

    @Override
    public void draw(Graphics g) {
        if (isWink) g.setColor(Color.LIGHT_GRAY);
        else g.setColor(Color.BLACK);
    }

    public void setWink(boolean wink) {
        isWink = wink;
    }

    public boolean isWink() {
        return isWink;
    }
}
