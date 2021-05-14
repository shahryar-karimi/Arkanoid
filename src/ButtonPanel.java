import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private static final int PANEL_WIDTH = 50;
    private static final int PANEL_HEIGHT = 500;
    private static final Dimension SCREEN_SIZE = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);

    public ButtonPanel() {
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.BLUE);
    }
}
