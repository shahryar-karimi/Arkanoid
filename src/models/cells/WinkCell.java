package models.cells;

import java.awt.*;

public class WinkCell extends Cell {
    private boolean isWink;

    public WinkCell(int x, int y, int width, int height, int heal, boolean isWink) {
        this(x, y, width, height);
        this.heal = heal;
        this.isWink = isWink;
    }

    public WinkCell(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.isWink = false;
        this.score = 50;
    }

    @Override
    public void draw(Graphics g) {
        if (isWink) g.setColor(Color.BLACK);
        else g.setColor(Color.LIGHT_GRAY);
        super.draw(g);
    }

    public boolean isWink() {
        return isWink;
    }

    public void wink() {
        isWink = !isWink;
    }

    @Override
    public WinkCell clone() {
        return new WinkCell(x, y, width, height, heal, isWink);
    }
}
