package models.cells;

import java.awt.*;

public class WoodenCell extends Cell{

    public WoodenCell(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.heal = 2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(109, 52, 11));
        g.fillRect(x, y, width, height);
    }
}
