package models.cells;

import java.awt.*;

public class InvisibleCell extends Cell{
    public InvisibleCell(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }
}
