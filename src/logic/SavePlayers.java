package logic;

import frames.MainFrame;
import models.Ball;
import models.Paddle;
import models.Score;
import models.cells.*;
import models.prizes.Prize;
import models.prizes.RandomPrize;
import models.prizes.ballPrizes.FastBall;
import models.prizes.ballPrizes.FireBall;
import models.prizes.ballPrizes.MultiBall;
import models.prizes.ballPrizes.SlowBall;
import models.prizes.paddlePrizes.BigPaddle;
import models.prizes.paddlePrizes.ConfusePaddle;
import models.prizes.paddlePrizes.SmallPaddle;
import panels.mainFramePanels.GamePanel;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SavePlayers {

    private static File file;

    private SavePlayers() {
    }

    public static void save(Manager manager) {
        file = new File("Saves.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Player player : manager.getPlayers()) {
                fileWriter.write("{\n");
                savePlayer(fileWriter, player);
                fileWriter.write("}\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void savePlayer(FileWriter fileWriter, Player player) throws IOException {
        saveInformation(fileWriter, "userName = ", player.getUserName());
        saveInformation(fileWriter, "password = ", player.getPassword());
        saveScore(fileWriter, player);
        saveScores(fileWriter, player);
        fileWriter.write(space(1) + "paused game = [\n");
        for (String name : player.getPausesGames().keySet())
            saveMainFrame(fileWriter, player, name);
        fileWriter.write(space(1) + "]\n");

    }

    private static void saveScore(FileWriter fileWriter, Player player) throws IOException{
        fileWriter.write(space(1) + "Score = [\n");
        fileWriter.write(space(2) + "score = " + player.getScore().getScore() + "\n");
        fileWriter.write(space(2) + "heal = " + player.getScore().getHeal() + "\n");
        fileWriter.write(space(1) + "]\n");
    }

    private static void saveMainFrame(FileWriter fileWriter, Player player, String name) throws IOException {
        fileWriter.write(space(2) + name + " = [\n");
        MainFrame mainFrame = player.getPausesGames().get(name);
        fileWriter.write(space(3) + "TimeLoop = " + mainFrame.getTimeLoop() + "\n");
        saveGamePanel(fileWriter, mainFrame.getGamePanel(), player);
        saveButtonPanel(fileWriter, player);
        fileWriter.write(space(3) + "WinkCounter = " + mainFrame.getWinkCounter() + "\n");
        fileWriter.write(space(3) + "AddRowCounter = " + mainFrame.getAddRowCounter() + "\n");
        fileWriter.write(space(2) + "]\n");
    }

    private static void saveButtonPanel(FileWriter fileWriter, Player player) throws IOException {
        fileWriter.write(space(3) + "Button panel = [\n");
        fileWriter.write(space(4) + "UserName = " + player.getUserName() + "\n");
        fileWriter.write(space(3) + "]\n");
    }

    private static void saveGamePanel(FileWriter fileWriter, GamePanel gamePanel, Player player) throws IOException {
        fileWriter.write(space(3) + "Game panel = [\n");
        savePaddle(fileWriter, gamePanel.getPaddle());
        saveBalls(fileWriter, gamePanel.getBalls());
        saveCells(fileWriter, gamePanel.getCells());
        savePrizes(fileWriter, gamePanel.getPrizes());
        saveTokenPrizes(fileWriter, gamePanel.getTokenPrizes());
        fileWriter.write(space(4) + "UserName = " + player.getUserName() + "\n");
        fileWriter.write(space(3) + "]\n");
    }

    private static void saveTokenPrizes(FileWriter fileWriter, ArrayList<Prize> tokenPrizes) throws IOException {
        fileWriter.write(space(4) + "TokenPrizes = [\n");
        for (Prize prize : tokenPrizes) {
            savePrize(fileWriter, prize, 5);
        }
        fileWriter.write(space(4) + "]\n");
    }

    private static void savePrizes(FileWriter fileWriter, ArrayList<Prize> prizes) throws IOException {
        fileWriter.write(space(4) + "Prizes = [\n");
        for (Prize prize : prizes) {
            savePrize(fileWriter, prize, 5);
        }
        fileWriter.write(space(4) + "]\n");
    }

    private static void savePrize(FileWriter fileWriter, Prize prize, int i) throws IOException {
        if (prize instanceof FastBall)
            fileWriter.write(space(i) + "FastBall");
        else if (prize instanceof FireBall)
            fileWriter.write(space(i) + "FireBall");
        else if (prize instanceof MultiBall)
            fileWriter.write(space(i) + "MultiBall");
        else if (prize instanceof SlowBall)
            fileWriter.write(space(i) + "SlowBall");
        else if (prize instanceof BigPaddle)
            fileWriter.write(space(i) + "BigPaddle");
        else if (prize instanceof ConfusePaddle)
            fileWriter.write(space(i) + "ConfusePaddle");
        else if (prize instanceof SmallPaddle)
            fileWriter.write(space(i) + "SmallPaddle");
        else
            fileWriter.write(space(i) + "RandomPrize");
        fileWriter.write(" = [\n");
        saveRect(fileWriter, i + 1, prize);
        fileWriter.write(space(i + 1) + "time = " + prize.getTime() + "\n");
        if (prize instanceof RandomPrize) {
            savePrize(fileWriter, ((RandomPrize) prize).getPrize(), i + 1);
        }
        fileWriter.write(space(i) + "]\n");
    }

    private static void saveCells(FileWriter fileWriter, ArrayList<Cell> cells) throws IOException {
        fileWriter.write(space(4) + "Cells = [\n");
        for (Cell cell : cells) {
            saveCell(fileWriter, cell);
        }
        fileWriter.write(space(4) + "]\n");
    }

    private static void saveCell(FileWriter fileWriter, Cell cell) throws IOException {
        if (cell instanceof GlassyCell)
            fileWriter.write(space(5) + "GlassyCell");
        else if (cell instanceof InvisibleCell)
            fileWriter.write(space(5) + "InvisibleCell");
        else if (cell instanceof PrizeCell)
            fileWriter.write(space(5) + "PrizeCell");
        else if (cell instanceof WinkCell)
            fileWriter.write(space(5) + "WinkCell");
        else
            fileWriter.write(space(5) + "WoodenCell");
        fileWriter.write(" = [\n");
        saveRect(fileWriter, 6, cell);
        fileWriter.write(space(6) + "heal = " + cell.getHeal() + "\n");
        if (cell instanceof PrizeCell) {
            savePrize(fileWriter, ((PrizeCell) cell).getPrize(), 6);
        } else if (cell instanceof WinkCell) {
            fileWriter.write(space(6) + "isWink = " + ((WinkCell) cell).isWink() + "\n");
        }
        fileWriter.write(space(5) + "]\n");
    }

    private static void saveBalls(FileWriter fileWriter, ArrayList<Ball> balls) throws IOException {
        fileWriter.write(space(4) + "Balls = [\n");
        for (Ball ball : balls) {
            saveBall(fileWriter, ball);
        }
        fileWriter.write(space(4) + "]\n");
    }

    private static void saveBall(FileWriter fileWriter, Ball ball) throws IOException {
        fileWriter.write(space(5) + "[\n");
        saveRect(fileWriter, 6, ball);
        fileWriter.write(space(6) + "xVelocity = " + ball.getXVelocity() + "\n");
        fileWriter.write(space(6) + "yVelocity = " + ball.getYVelocity() + "\n");
        fileWriter.write(space(6) + "isFire = " + ball.isFire() + "\n");
        fileWriter.write(space(6) + "velocityRatio = " + ball.getVelocityRatio() + "\n");
        fileWriter.write(space(5) + "]\n");
    }

    private static void savePaddle(FileWriter fileWriter, Paddle paddle) throws IOException {
        fileWriter.write(space(4) + "Paddle = [\n");
        saveRect(fileWriter, 5, paddle);
        fileWriter.write(space(5) + "xVelocity = " + paddle.getXVelocity() + "\n");
        fileWriter.write(space(5) + "isNormal = " + paddle.isNormal() + "\n");
        fileWriter.write(space(4) + "]\n");
    }

    private static void saveRect(FileWriter fileWriter, int i, Rectangle r) throws IOException {
        fileWriter.write(space(i) + "x = " + r.x + "\n");
        fileWriter.write(space(i) + "y = " + r.y + "\n");
        fileWriter.write(space(i) + "width = " + r.width + "\n");
        fileWriter.write(space(i) + "height = " + r.height + "\n");
    }

    private static void saveScores(FileWriter fileWriter, Player player) throws IOException {
        fileWriter.write(space(1) + "token scores = [\n");
        for (Score score : player.getTokenScores()) {
            fileWriter.write(space(2) + score.getScore() + "\n");
        }
        fileWriter.write(space(1) + "]\n");
    }

    private static void saveInformation(FileWriter fileWriter, String s, String userName) throws IOException {
        fileWriter.write(space(1) + s + userName + "\n");
    }

    public static String space(int i) {
        String result = "";
        for (int j = 0; j < i; j++) {
            result += "    ";
        }
        return result;
    }
}
