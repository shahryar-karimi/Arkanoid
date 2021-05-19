package models.cells;

import models.prizes.Prize;

import java.awt.*;

public class PrizeCell extends Cell{

    private Prize prize;

    public PrizeCell(int x, int y, int width, int height, int heal, Prize prize) {
        this(x, y, width, height, prize);
        this.heal = heal;
    }

    public PrizeCell(int x, int y, int width, int height, Prize prize) {
        super(x, y, width, height);
        this.score = 10;
        this.prize = prize;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(215, 189, 49));
        super.draw(g);
    }
}
