package logic;

import java.io.File;
import java.util.Scanner;

public class Load {
    File file;

    private Load() {
    }

    public Manager load () {
        file = new File("Save.txt");
        Manager manager = new Manager();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return manager;
    }
}
