import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    void initTest() {
        new Main();
        int[] scores = new int[3];
        Graphics.board.controlElements(scores);
        Graphics.playerScore = scores[0];
        Graphics.pcScore = scores[1];
        int x, y;

        for (int i = 0; i < 64; i++) {
            x = i % 8;
            y = i / 8;

            if (x == 3 && y == 3 || x == 4 && y == 4) {
                assertEquals('X', Graphics.board.field[x][y].getStatus());
            } else if (x == 4 && y == 3 || x == 3 && y == 4) {
                assertEquals('O', Graphics.board.field[x][y].getStatus());
            } else {
                assertEquals('.', Graphics.board.field[x][y].getStatus());
            }
        }

        assertEquals(2, Graphics.playerScore);
        assertEquals(2, Graphics.pcScore);
    }

    @Test
    void resetTest() {
        new Main();
        int[] scores = new int[3];
        Graphics.board.play(4, 2);
        Graphics.board.reset();
        Graphics.board.controlElements(scores);
        Graphics.playerScore = scores[0];
        Graphics.pcScore = scores[1];
        int x, y;

        for (int i = 0; i < 64; i++) {
            x = i % 8;
            y = i / 8;

            if (x == 3 && y == 3 || x == 4 && y == 4) {
                assertEquals('X', Graphics.board.field[x][y].getStatus());
            } else if (x == 4 && y == 3 || x == 3 && y == 4) {
                assertEquals('O', Graphics.board.field[x][y].getStatus());
            } else {
                assertEquals('.', Graphics.board.field[x][y].getStatus());
            }
        }

        assertEquals(2, Graphics.playerScore);
        assertEquals(2, Graphics.pcScore);
    }

    @Test
    void moveTest1() {
        new Main();
        int[] scores = new int[3];
        int x, y;
        Graphics.board.play(4, 2);
        Graphics.board.controlElements(scores);
        Graphics.playerScore = scores[0];
        Graphics.pcScore = scores[1];

        for (int i = 0; i < 64; i++) {
            x = i % 8;
            y = i / 8;

            if (x == 3 && y == 3 || x == 4 && y == 4 || x == 4 && y == 3 || x == 4 && y == 2) {
                assertEquals('X', Graphics.board.field[x][y].getStatus());
            } else if (x == 3 && y == 4) {
                assertEquals('O', Graphics.board.field[x][y].getStatus());
            } else {
                assertEquals('.', Graphics.board.field[x][y].getStatus());
            }
        }

        assertEquals(1, Graphics.pcScore);
        assertEquals(4, Graphics.playerScore);
    }

    @Test
    void moveTest2() {
        new Main();
        int[] scores = new int[3];
        int x, y;
        Graphics.board.play(4, 2);
        Graphics.board.controlElements(scores);
        Graphics.playerScore = scores[0];
        Graphics.pcScore = scores[1];
        Graphics.board.play();
        Graphics.board.controlElements(scores);
        Graphics.playerScore = scores[0];
        Graphics.pcScore = scores[1];

        for (int i = 0; i < 64; i++) {
            x = i % 8;
            y = i / 8;

            if (x == 4 && y == 3 || x == 4 && y == 4 || x == 4 && y == 2) {
                assertEquals('X', Graphics.board.field[x][y].getStatus());
            } else if (x == 3 && y == 4 || x == 3 && y == 3 || x == 3 && y == 2) {
                assertEquals('O', Graphics.board.field[x][y].getStatus());
            } else {
                assertEquals('.', Graphics.board.field[x][y].getStatus());
            }
        }

        assertEquals(3, Graphics.pcScore);
        assertEquals(3, Graphics.playerScore);
    }
}
