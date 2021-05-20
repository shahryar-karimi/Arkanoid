package models.cells;

import java.awt.*;

public class GlassyCell extends Cell {

    public GlassyCell(int x, int y, int width, int height, int heal) {
        this(x, y, width, height);
        this.heal = heal;
    }

    public GlassyCell(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.score = 10;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        super.draw(g);
    }

    @Override
    public GlassyCell clone() {
        return new GlassyCell(x, y, width, height, heal);
    }
}
