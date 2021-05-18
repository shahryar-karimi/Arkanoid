package models.prizes;

import models.prizes.ballPrizes.*;

import models.prizes.paddlePrizes.BigPaddle;
import models.prizes.paddlePrizes.ConfusePaddle;
import models.prizes.paddlePrizes.SmallPaddle;
import panels.mainFramePanels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RandomPrize extends Prize {
    private Random random;
    private Prize prize;

    public RandomPrize(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        initPrize();
    }

    @Override
    public void onPrize(GamePanel gamePanel) {
        prize.onPrize(gamePanel);
    }

    private void initPrize() {
        int i = random.nextInt(7);
        switch (i) {
            case 0 -> prize = new FastBall(x, y, width, height);
            case 1 -> prize = new FireBall(x, y, width, height);
            case 2 -> prize = new MultiBall(x, y, width, height);
            case 3 -> prize = new SlowBall(x, y, width, height);
            case 4 -> prize = new BigPaddle(x, y, width, height);
            case 5 -> prize = new ConfusePaddle(x, y, width, height);
            case 6 -> prize = new SmallPaddle(x, y, width, height);
        }
    }

    @Override
    public void offPrize(GamePanel gamePanel) {
        prize.offPrize(gamePanel);
    }

    @Override
    public void draw(Graphics g) {
        ImageIcon imageIcon = new ImageIcon("images/questionMark.png");
        Image image = imageIcon.getImage();
        g.drawImage(image, x, y, width, height, null);
    }

    public Prize getPrize() {
        return prize;
    }
}
