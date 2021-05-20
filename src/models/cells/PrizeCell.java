package models.cells;

import models.prizes.Prize;
import models.prizes.ballPrizes.FastBall;
import models.prizes.ballPrizes.FireBall;
import models.prizes.ballPrizes.MultiBall;
import models.prizes.ballPrizes.SlowBall;
import models.prizes.paddlePrizes.BigPaddle;
import models.prizes.paddlePrizes.ConfusePaddle;
import models.prizes.paddlePrizes.SmallPaddle;

import java.awt.*;

public class PrizeCell extends Cell {

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

    @Override
    public PrizeCell clone() {
        return new PrizeCell(x, y, width, height, heal, prize.clone());
    }
}
