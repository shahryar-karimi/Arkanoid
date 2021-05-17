import logic.Load;
import logic.Manager;

public class Main {
    public static void main(String[] args) {
        Manager manager = Load.load();
        manager.start();
    }
}
