package pl.comp.javafx.helpers;

import java.util.regex.Pattern;
import pl.comp.model.SudokuBoard;

public class SudokuFieldHelper {
    private SudokuBoard board;
    private int row;
    private int col;

    public static final Pattern PATTERN = Pattern.compile("[0-9]");

    public SudokuFieldHelper(SudokuBoard board, int col, int row) {
        this.board = board;
        this.row = row;
        this.col = col;
    }

    public void setField(int value) {
        board.set(col, row, value);
    }

    public int getField() {
        return board.get(col, row);
    }

}
