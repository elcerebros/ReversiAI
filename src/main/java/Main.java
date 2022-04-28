import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    public Main() {
        setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        setSize(width / 2, height / 2);

        // center the jframe on screen
        setLocationRelativeTo(null);

        JPanel pnlLeft = new Graphics();
        add(pnlLeft, BorderLayout.CENTER);

        setSize(550, 690);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) { new Main(); }
}
