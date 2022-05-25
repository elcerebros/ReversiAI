import logic.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.ImageIcon;

/**
 * Graphics - класс, реализующий графический интерфейс пользователя.
 *
 * (используется библиотека Swing)
 */
public class Graphics extends JPanel{
    // Инициализация элементов GUI
    private static JLabel scorePlayer; // Лэйбл счёта игрока
    private static JLabel scoreComputer; // Лэйбл счёта компьютера
    private static JButton newGame; // Кнопка "Новая игра"
    private static JButton[] cell; // Кнопки-клетки игрового поля

    // Инициализация логики игры
    public static int playerScore = 2;
    public static int pcScore = 2;
    public static ReversiLogic board; // Логика игрового поля
    private static final ArrayList<ReversiLogic> arrReversi = new ArrayList<>();
    private static ReversiLogic start;
    private static final int rows = 8;
    private static final int columns = 8;

    public Graphics() {
        // Инициализация рабочего пространства
        super(new BorderLayout());
        setPreferredSize(new Dimension(800,770));
        setLocation(0, 0);

        // Инициализация панели
        board = new ReversiLogic();
        start = board;
        arrReversi.add(new ReversiLogic(board));

        // Панель с кнопками "Новая игра" и "Шаг назад"
        JPanel panel = new JPanel(); // Панель с кнопками "Новая игра" и "Шаг назад"
        panel.setPreferredSize(new Dimension(800,60));
        add(panel, BorderLayout.SOUTH);

        // Кнопка "Новая игра"
        newGame = new JButton();
        newGame.setPreferredSize(new Dimension(80,50));
        try {
            BufferedImage img = ImageIO.read(new File("files/start.png"));
            newGame.setIcon(new ImageIcon(img));
        } catch (IOException ignored) { }
        newGame.addActionListener(new Action());
        panel.add(newGame);

        // Инициализация игрового поля и клеток-кнопок
        JPanel boardPanel = new JPanel(new GridLayout(8, 8)); // Панель с игровой доской
        cell = new JButton[64];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cell[k] = new JButton();
                cell[k].setSize(50, 50);
                cell[k].setBackground(Color.GREEN);
                cell[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if (board.field[i][j].getStatus() == 'X') {
                    try {
                        BufferedImage img = ImageIO.read(new File("files/dark.png"));
                        cell[k].setIcon(new ImageIcon(img));
                    } catch (IOException ignored) { }
                }
                else if (board.field[i][j].getStatus() == 'O') {
                    try {
                        BufferedImage img = ImageIO.read(new File("files/light.png"));
                        cell[k].setIcon(new ImageIcon(img));
                    } catch (IOException ignored) { }
                }
                else if (i == 2 && j == 4 || i == 3 && j == 5 || i == 4 && j == 2 || i == 5 && j == 3 ) {
                    try {
                        BufferedImage img = ImageIO.read(new File("files/legalMoveIcon.png"));
                        cell[k].setIcon(new ImageIcon(img));
                    } catch (IOException ignored) { }
                }
                cell[k].addActionListener(new Action());
                boardPanel.add(cell[k]);
                k++;
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        // Панель счёта
        JPanel scorePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        scorePanel.setPreferredSize(new Dimension(800,70));
        // Инициализация лэйблов счёта игрока и компьютера
        JLabel dark = new JLabel();
        try {
            BufferedImage img = ImageIO.read(new File("files/dark.png"));
            dark.setIcon(new ImageIcon(img));
        } catch (IOException ignored) { }
        JLabel light = new JLabel();
        try {
            BufferedImage img = ImageIO.read(new File("files/light.png"));
            light.setIcon(new ImageIcon(img));
        } catch (IOException ignored) { }
        scorePlayer = new JLabel();
        scorePlayer.setText(" Player : " + playerScore + "      ");
        scorePlayer.setFont(new Font("Serif", Font.BOLD, 22));
        scoreComputer = new JLabel();
        scoreComputer.setText(" Computer : " + pcScore + "  ");
        scoreComputer.setFont(new Font("Serif", Font.BOLD, 22));
        c.gridx = 0;
        c.gridy = 1;
        scorePanel.add(dark, c);
        c.gridx = 1;
        c.gridy = 1;
        scorePanel.add(scorePlayer,c);
        c.gridx = 2;
        c.gridy = 1;
        scorePanel.add(light, c);
        c.gridx = 3;
        c.gridy = 1;
        scorePanel.add(scoreComputer,c);
        add(scorePanel, BorderLayout.NORTH);
    }

    private static class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Нажата кнопка "Новая игра"
            if (e.getSource() == newGame) {
                board.reset();
                arrReversi.clear();
                arrReversi.add(new ReversiLogic(start));
                int k = 0;

                // Простановка иконок на поле при перезагрузке игры
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        cell[k].setIcon(null);
                        if (board.field[i][j].getStatus() == 'X') {
                            try {
                                BufferedImage img = ImageIO.read(new File("files/dark.png"));
                                cell[k].setIcon(new ImageIcon(img));
                            } catch (IOException ignored) { }
                        } else if (board.field[i][j].getStatus() == 'O') {
                            try {
                                BufferedImage img = ImageIO.read(new File("files/light.png"));
                                cell[k].setIcon(new ImageIcon(img));
                            } catch (IOException ignored) { }
                        }
                        // Возможные ходы
                        if (i == 2 && j == 4 || i == 3 && j == 5 || i == 4 && j == 2 || i == 5 && j == 3 ) {
                            try {
                                BufferedImage img = ImageIO.read(new File("files/legalMoveIcon.png"));
                                cell[k].setIcon(new ImageIcon(img));
                            } catch (IOException ignored) { }
                        }
                        k++;
                    }
                }

                playerScore = 2; pcScore = 2;
                scorePlayer.setText(" Player : " + playerScore + "  ");
                scoreComputer.setText(" Computer : " + pcScore + "  ");

            // Сделан ход -> обновление игрового поля
            } else {
                for (int i = 0; i < 64; i++) {
                    if (e.getSource() == cell[i]) {
                        int x, y; // Координаты нажатой клетки
                        int status; // 0 - все ок; 1 - выбрана занятая клетка доски; -1 - боченки не были перекрыты
                        int[] scores = new int[3];

                        y = i % 8;
                        x = i / 8;
                        status = board.play(x, y);

                        // Обновление поля после хода игрока
                        if (status == 0) {
                            arrReversi.add(new ReversiLogic(board));
                            int k = 0;

                            for (int row = 0; row < rows; row++) {
                                for (int column = 0; column < columns; column++) {
                                    if (board.field[row][column].getStatus() == 'X') {
                                        try {
                                            BufferedImage img = ImageIO.read(new File("files/dark.png"));
                                            cell[k].setIcon(new ImageIcon(img));
                                        } catch (IOException ignored) { }
                                    } else if (board.field[row][column].getStatus() == 'O') {
                                        try {
                                            BufferedImage img = ImageIO.read(new File("files/light.png"));
                                            cell[k].setIcon(new ImageIcon(img));
                                        } catch (IOException ignored) { }
                                    }
                                    k++;
                                }
                            }

                            board.controlElements(scores);
                            playerScore = scores[0]; pcScore = scores[1];
                            scorePlayer.setText("Player : " + playerScore + "  ");
                            scoreComputer.setText("Computer : " + pcScore + "  ");
                        }

                        // Последующий ход компьютера
                        if (status == 0 || status == -1) {
                            board.play();
                            arrReversi.add(new ReversiLogic(board));
                            ArrayList <Integer> legalMoves = new ArrayList <>();
                            int k = 0;

                            // Обновление игрового поля после хода компьютера
                            for (int row = 0; row < rows; row++) {
                                for (int colum = 0; colum < columns; colum++) {
                                    if (board.field[row][colum].getStatus() == 'X') {
                                        try {
                                            BufferedImage img = ImageIO.read(new File("files/dark.png"));
                                            cell[k].setIcon(new ImageIcon(img));
                                        } catch (IOException ignored) { }
                                    } else if (board.field[row][colum].getStatus() == 'O') {
                                        try {
                                            BufferedImage img = ImageIO.read(new File("files/light.png"));
                                            cell[k].setIcon(new ImageIcon(img));
                                        } catch (IOException ignored) { }
                                    } else if(board.field[row][colum].getStatus() == '.') {
                                        cell[k].setIcon(null);
                                    }
                                    k++;
                                }
                            }
                            // Простановка возможных ходов
                            board.findLegalMove('X', legalMoves);
                            for (int j = 0; j < legalMoves.size(); j += 2) {
                                try {
                                    BufferedImage img = ImageIO.read(new File("files/legalMoveIcon.png"));
                                    cell[legalMoves.get(j) * rows + legalMoves.get(j + 1)].setIcon(new ImageIcon(img));
                                } catch (IOException ignored) { }
                            }

                            // Простановка счёта
                            board.controlElements(scores);
                            playerScore = scores[0]; pcScore = scores[1];
                            scorePlayer.setText("Player : " + playerScore + "  ");
                            scoreComputer.setText("Computer : " + pcScore + "  ");
                        }
                    }

                }

                int statusOfGame = board.endOfGame();
                // 0 - нет возможных ходов
                // 1 - победа белых
                // 2 - победа черных
                // 3 - ничья
                if (statusOfGame == 0) {
                    if (playerScore > pcScore) {
                        JOptionPane.showMessageDialog(null, "No legal move!\nPlayer Win!",
                                "Game Over", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No legal move!\nComputer Win!",
                                "Game Over", JOptionPane.PLAIN_MESSAGE);
                    }
                } else if (statusOfGame == 1) {
                    JOptionPane.showMessageDialog(null,"Computer Win!","Game Over",JOptionPane.PLAIN_MESSAGE);
                } else if (statusOfGame == 2) {
                    JOptionPane.showMessageDialog(null,"Player Win!","Game Over",JOptionPane.PLAIN_MESSAGE);
                } else if (statusOfGame == 3) {
                    JOptionPane.showMessageDialog(null,"Draw!!","Game Over",JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }
}