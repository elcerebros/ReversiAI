import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    public Main() {
        // Настройка окна графического интерфейса
        setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        setSize(width / 2, height / 2);
        setLocationRelativeTo(null);

        // Подключение графики
        JPanel pnlLeft = new Graphics();
        add(pnlLeft, BorderLayout.CENTER);

        // Донастройка окна графического интерфейса
        setSize(550, 690);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) { new Main(); }
}
