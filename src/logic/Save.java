package logic;

import models.Score;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {

    private static File file;

    private Save() {}

    public static void save(Manager manager) {
        file = new File("Saves.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Player player : manager.getPlayers()) {
                fileWriter.write("{\n");

                fileWriter.write(space(1) + "userName = " + player.getUserName() + "\n");
                fileWriter.write(space(1) + "password = " + player.getPassword() + "\n");
                fileWriter.write(space(1) + "scores = [\n");
                for (Score score : player.getScores()) {
                    fileWriter.write(space(2) + score.getScore() + "\n");
                }
                fileWriter.write(space(1) + "]\n");

                fileWriter.write("}\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String space(int i) {
        String result = "";
        for (int j = 0; j < i; j++) {
            result += "    ";
        }
        return result;
    }
}
