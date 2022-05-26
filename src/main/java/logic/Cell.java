package logic;

/**
 * Cell - класс, реализующий клетку игрового поля.
 *
 * Обозначения клетки:
 * 'X' - бочонок игрока;
 * 'O' - бочонок компьютера;
 * '.' - клетка пуста.
 *
 */
public class Cell {
    private int y;
    private char x;
    private char status;

    public Cell(char x, int y, char status) {
        this.x = x;
        this.y = y;
        this.status = status;
    }

    public char getX() { return x; }

    public int getY() { return y; }

    public char getStatus() { return status; }

    public void setPosition(char x, int y, char status) {
        this.x = x;
        this.y = y;
        this.status = status;
    }
}