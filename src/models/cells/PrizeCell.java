package models.cells;

import java.awt.*;

public class PrizeCell extends Cell{
    public PrizeCell(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(215, 189, 49));
        g.fillRect(x, y, width, height);
    }
}
