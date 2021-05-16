package models.cells;

import java.awt.*;

public class GlassyCell extends Cell {

    public GlassyCell(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        this.score = 10;
        g.setColor(Color.CYAN);
        super.draw(g);
    }


}
