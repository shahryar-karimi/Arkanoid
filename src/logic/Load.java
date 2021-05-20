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
import panels.mainFramePanels.ButtonPanel;
import panels.mainFramePanels.GamePanel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Load {
    private static File file;

    private Load() {
    }

    public static Manager load() {
        file = new File("Saves.txt");
        Manager manager = new Manager();
        try {
            Scanner scanner = new Scanner(file);
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.equals("{")) {
                    Player player = loadPlayer(scanner);
                    manager.addPlayer(player);
                    player.setPausesGames(getPausedGames(scanner, manager));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return manager;
    }

    private static Player loadPlayer(Scanner scanner) {
        String userName = getInformation(scanner);
        String password = getInformation(scanner);
        ArrayList<Score> scores = getScores(scanner);
        Player player = new Player();
        return player.setTokenScores(scores).setPassword(password).setUserName(userName);
    }

    private static HashMap<String, MainFrame> getPausedGames(Scanner scanner, Manager manager) {
        scanner.nextLine();
        HashMap<String, MainFrame> pausedGames = new HashMap<>();
        String line;
        String name;
        while (scanner.hasNext()) {
            line = skipSpace(1, scanner.nextLine());
            if (line.equals("]")) break;
            name = skipSpace(1, line).split(" = ")[0];
            pausedGames.put(name, loadMainFrame(scanner, manager));
        }
        scanner.nextLine();
        return pausedGames;
    }

    private static MainFrame loadMainFrame(Scanner scanner, Manager manager) {
        int timeLoop = getInt(3, scanner);
        GamePanel gamePanel = getGamePanel(scanner, manager);
        ButtonPanel buttonPanel = getButtonPanel(scanner, manager);
        int winkCounter = getInt(3, scanner);
        int addRowCounter = getInt(3, scanner);
        scanner.nextLine();
        return new MainFrame(timeLoop, gamePanel, buttonPanel, winkCounter, addRowCounter);
    }

    private static ButtonPanel getButtonPanel(Scanner scanner, Manager manager) {
        scanner.nextLine();
        String userName = getString(4, scanner);
        scanner.nextLine();
        return new ButtonPanel(userName, manager);
    }

    private static GamePanel getGamePanel(Scanner scanner, Manager manager) {
        scanner.nextLine();
        Paddle paddle = loadPaddle(scanner);
        ArrayList<Ball> balls = loadBalls(scanner);
        ArrayList<Cell> cells = loadCells(scanner);
        ArrayList<Prize> prizes = loadPrizes(scanner);
        ArrayList<Prize> tokenPrize = loadPrizes(scanner);
        String userName = getString(4, scanner);
        scanner.nextLine();
        return new GamePanel(paddle, balls, cells, prizes, tokenPrize, userName, manager);
    }

    private static ArrayList<Prize> loadPrizes(Scanner scanner) {
        scanner.nextLine();
        String line;
        String material;
        ArrayList<Prize> prizes = new ArrayList<>();
        while (scanner.hasNext()) {
            line = skipSpace(4, scanner.nextLine());
            if (line.equals("]")) break;
            line = skipSpace(1, line);
            material = line.split(" = ")[0];
            prizes.add(loadPrize(6, scanner, material));
        }
        return prizes;
    }

    private static ArrayList<Cell> loadCells(Scanner scanner) {
        scanner.nextLine();
        String line;
        ArrayList<Cell> cells = new ArrayList<>();
        while (scanner.hasNext()) {
            line = skipSpace(4, scanner.nextLine());
            if (line.equals("]")) break;
            line = skipSpace(1, line);
            String material = line.split(" = ")[0];
            int x = getInt(6, scanner);
            int y = getInt(6, scanner);
            int width = getInt(6, scanner);
            int height = getInt(6, scanner);
            int heal = getInt(6, scanner);
            if (material.equals("WinkCell")) {
                cells.add(getCell(x, y, width, height, heal, material, getBoolean(6, scanner)));
            } else if (material.equals("PrizeCell")) {
                String m = skipSpace(6, scanner.nextLine()).split(" = ")[0];
                cells.add(getCell(x, y, width, height, heal, material, loadPrize(7, scanner, m)));
            } else {
                cells.add(getCell(x, y, width, height, heal, material, null));
            }
            scanner.nextLine();
        }
        return cells;
    }

    private static Prize loadPrize(int i, Scanner scanner, String material) {
        int x = getInt(i, scanner);
        int y = getInt(i, scanner);
        int width = getInt(i, scanner);
        int height = getInt(i, scanner);
        int time = getInt(i, scanner);
        if (material.equals("RandomPrize")) {
            String m = skipSpace(i, scanner.nextLine()).split(" = ")[0];
            Prize prize = loadPrize(i + 1, scanner, m);
            scanner.nextLine();
            return getPrize(x, y, width, height, time, material, prize);
        }
        scanner.nextLine();
        return getPrize(x, y, width, height, time, material, null);
    }

    private static Prize getPrize(int x, int y, int width, int height, int time, String material, Prize prize) {
        if (material.equals("FastBall")) {
            return new FastBall(x, y, width, height, time);
        } else if (material.equals("FireBall")) {
            return new FireBall(x, y, width, height, time);
        } else if (material.equals("MultiBall")) {
            return new MultiBall(x, y, width, height, time);
        } else if (material.equals("SlowBall")) {
            return new SlowBall(x, y, width, height, time);
        } else if (material.equals("BigPaddle")) {
            return new BigPaddle(x, y, width, height, time);
        } else if (material.equals("ConfusePaddle")) {
            return new ConfusePaddle(x, y, width, height, time);
        } else if (material.equals("SmallPaddle")) {
            return new SmallPaddle(x, y, width, height, time);
        } else {
            return new RandomPrize(x, y, width, height, time, prize);
        }
    }

    private static Cell getCell(int x, int y, int width, int height, int heal, String material, Object o) {
        if (material.equals("GlassyCell")) {
            return new GlassyCell(x, y, width, height, heal);
        } else if (material.equals("InvisibleCell")) {
            return new InvisibleCell(x, y, width, height, heal);
        } else if (material.equals("PrizeCell")) {
            return new PrizeCell(x, y, width, height, heal, (Prize) o);
        } else if (material.equals("WinkCell")) {
            return new WinkCell(x, y, width, height, heal, (Boolean) o);
        } else return new WoodenCell(x, y, width, height, heal);
    }

    private static ArrayList<Ball> loadBalls(Scanner scanner) {
        scanner.nextLine();
        String line;
        ArrayList<Ball> balls = new ArrayList<>();
        while (scanner.hasNext()) {
            line = skipSpace(4, scanner.nextLine());
            if (line.equals("]")) break;
            int x = getInt(6, scanner);
            int y = getInt(6, scanner);
            int width = getInt(6, scanner);
            int height = getInt(6, scanner);
            int xVelocity = getInt(6, scanner);
            int yVelocity = getInt(6, scanner);
            boolean isFire = getBoolean(6, scanner);
            double velocityRatio = getDouble(6, scanner);
            balls.add(new Ball(x, y, width, height, xVelocity, yVelocity, isFire, velocityRatio));
            scanner.nextLine();
        }
        return balls;
    }

    private static Paddle loadPaddle(Scanner scanner) {
        scanner.nextLine();
        int x = getInt(5, scanner);
        int y = getInt(5, scanner);
        int width = getInt(5, scanner);
        int height = getInt(5, scanner);
        int xVelocity = getInt(5, scanner);
        boolean isNormal = getBoolean(5, scanner);
        scanner.nextLine();
        return new Paddle(x, y, width, height, xVelocity, isNormal);
    }

    private static String getInformation(Scanner scanner) {
        return skipSpace(1, scanner.nextLine()).split(" = ")[1];
    }

    private static ArrayList<Score> getScores(Scanner scanner) {
        ArrayList<Score> scores = new ArrayList<>();
        scanner.nextLine();
        while (scanner.hasNext()) {
            String score = skipSpace(1, scanner.nextLine());
            if (score.equals("]")) break;
            scores.add(new Score(0, 0, 509, 509, Integer.parseInt(score.substring(4))));
        }
        return scores;
    }

    private static String skipSpace(int i, String s) {
        return s.substring(4 * i);
    }

    private static int getInt(int i, Scanner scanner) {
        return Integer.parseInt(getString(i, scanner));
    }

    private static double getDouble(int i, Scanner scanner) {
        return Double.parseDouble(getString(i, scanner));
    }

    private static boolean getBoolean(int i, Scanner scanner) {
        return getString(i, scanner).equals("true");
    }

    private static String getString(int i, Scanner scanner) {
        return skipSpace(i, scanner.nextLine()).split(" = ")[1];
    }
}
