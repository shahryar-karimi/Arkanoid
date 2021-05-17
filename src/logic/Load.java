package logic;

import models.Score;

import java.io.File;
import java.util.ArrayList;
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
                    manager.addPlayer(loadPlayer(scanner));
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
        player.setScores(scores).setPassword(password).setUserName(userName);
        return player;
    }

    private static String getInformation(Scanner scanner) {
        return scanner.nextLine().substring(4).split("\\s")[2];
    }

    private static ArrayList<Score> getScores(Scanner scanner) {
        ArrayList<Score> scores = new ArrayList<>();
        scanner.nextLine();
        while (scanner.hasNext()) {
            String score = scanner.nextLine().substring(4);
            if (score.equals("]")) break;
            scores.add(new Score(0, 0, 509, 509, Integer.parseInt(score.substring(4))));
        }
        return scores;
    }
}
