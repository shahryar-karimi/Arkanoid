package models.cells;

import java.awt.*;

public class InvisibleCell extends Cell{

    public InvisibleCell(int x, int y, int width, int height, int heal) {
        this(x, y, width, height);
        this.heal = heal;
    }

    public InvisibleCell(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.score = 20;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        super.draw(g);
    }
}
