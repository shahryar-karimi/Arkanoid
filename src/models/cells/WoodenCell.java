package models.cells;

import java.awt.*;

public class WoodenCell extends Cell{

    public WoodenCell(int x, int y, int width, int height, int heal) {
        this(x, y, width, height);
        this.heal = heal;
    }

    public WoodenCell(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.heal = 2;
        this.score = 25;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(109, 52, 11));
        super.draw(g);
    }

    @Override
    public WoodenCell clone() {
        return new WoodenCell(x, y, width, height, heal);
    }
}
