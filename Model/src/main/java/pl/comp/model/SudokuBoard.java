package pl.comp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.comp.model.annotations.ExcludeFromJacocoGeneratedReport;
import pl.comp.model.exceptions.CloneException;
import pl.comp.model.solvers.ISudokuSolver;

public class SudokuBoard implements Serializable, Cloneable {
    public static final int BOX_X = 3;
    public static final int BOX_Y = 3;
    public static final int SIZE = 9;
    public static final int NUMBER_OF_CELLS = SIZE * SIZE;
    private final ISudokuSolver sudokuSolver;
    private final List<List<SudokuField>> board;

    public int get(int x, int y) {
        return board.get(x).get(y).getFieldValue();
    }

    public void set(int x, int y, int newValue) {
        board.get(x).get(y).setFieldValue(newValue);
    }

    public SudokuBoard(ISudokuSolver sudokuSolver) {
        this.sudokuSolver = sudokuSolver;

        // Initialization of list 1st dimension.
        board = Arrays.asList(new List[SIZE]); // This means it's fixed. Unable change it's size.
        for (int i = 0; i < SIZE; i++) {
            // Initialization of list 2nd dimension.
            board.set(i, Arrays.asList(new SudokuField[SIZE]));
            // Change from null to SudokuField.
            for (int j = 0; j < SIZE; j++) {
                this.board.get(i).set(j, new SudokuField());
            }
        }
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public SudokuColumn getColumn(int column) {
        List<SudokuField> temp = new ArrayList<>();

        for (List<SudokuField> row : board) {
            temp.add(row.get(column));
        }

        return new SudokuColumn(temp);
    }

    public SudokuRow getRow(int row) {
        return new SudokuRow(board.get(row));
    }

    public SudokuBox getBox(int x, int y) {

        // x, y is in range 0-2
        //  meaning 0 box, 1st box, 2nd box

        final int rangeX = x * BOX_X;
        final int rangeY = y * BOX_Y;

        List<SudokuField> temp = new ArrayList<>();

        for (int i = rangeX; i < BOX_X + rangeX; i++) {
            for (int j = rangeY; j < BOX_Y + rangeY; j++) {
                temp.add(board.get(i).get(j));
            }
        }

        return new SudokuBox(temp);
    }

    public boolean checkBoard() {
        for (int i = 0; i < SIZE; i++) {
            SudokuColumn column = getColumn(i);
            if (!column.verify()) {
                return false;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            SudokuRow row = getRow(i);
            if (!row.verify()) {
                return false;
            }
        }

        for (int x = 0; x < BOX_X; x++) {
            for (int y = 0; y < BOX_Y; y++) {
                SudokuBox box = getBox(x, y);
                if (!box.verify()) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isColumn(int rowNumber, int columnNumber, int value) {
        for (int y = 0; y < rowNumber; y++) {
            if (value == board.get(y).get(columnNumber).getFieldValue()) {
                return false;
            }
        }
        return true;
    }

    private boolean isRow(int rowNumber, int columnNumber, int value) {
        for (int x = 0; x < columnNumber; x++) {
            if (value == board.get(rowNumber).get(x).getFieldValue()) {
                return false;
            }
        }
        return true;
    }

    private boolean isBox(int rowNumber, int columnNumber, int value, int index) {
        int bigColumnIndex = columnNumber / (SIZE / BOX_X);
        int bigRowIndex = rowNumber / (SIZE / BOX_Y);
        int realColumnIndex;
        int realRowIndex;

        for (int row = 0; row < BOX_Y; row++) {
            for (int column = 0; column < BOX_X; column++) {
                realColumnIndex = column + bigColumnIndex * BOX_X;
                realRowIndex = row + bigRowIndex * BOX_Y;
                if (board.get(realRowIndex).get(realColumnIndex).getFieldValue() == value
                    && (realRowIndex * SIZE + realColumnIndex) < index) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(int index) {

        // index is a number of a cell.
        //  meaning it's a number between 0-81.

        int rowNumber = index / SIZE; // cells number in rows
        int columnNumber = index % SIZE; // cells number in cols
        int value = board.get(rowNumber).get(columnNumber).getFieldValue(); // cell

        // Here we're checking if the specific number we got randomly previously
        //  is valid in comparison to the ones we have added before.
        //  Therefore, we cannot use getColumn/Row/Box here.
        //   as those methods check values for complete Column/Row/Box.

        if (!isColumn(rowNumber, columnNumber, value)) {
            return false;
        }

        if (!isRow(rowNumber, columnNumber, value)) {
            return false;
        }

        return isBox(rowNumber, columnNumber, value, index);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof SudokuBoard that)) {
            return false;
        }

        EqualsBuilder equalsBuilder = new EqualsBuilder();

        for (int y = 0; y < SudokuBoard.SIZE; y++) {
            for (int x = 0; x < SudokuBoard.SIZE; x++) {
                if (!(equalsBuilder.append(this.get(x, y), that.get(x, y)).isEquals())) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(board).toHashCode();

    }

    @Override
    public String toString() {
        ToStringBuilder temp = new ToStringBuilder(this);
        temp.append("board:");
        for (int i = 0; i < SIZE; i++) {
            temp.append("row", board.toArray()[i]);
        }
        return temp.toString();
    }

    // Thrown only when the interface is not implemented
    @Override
    @ExcludeFromJacocoGeneratedReport
    public SudokuBoard clone() throws CloneException {
        try {
            ISudokuSolver cloneSolver = sudokuSolver.clone();
            SudokuBoard clone = new SudokuBoard(cloneSolver);

            for (int y = 0; y < SIZE; y++) {
                for (int x = 0; x < SIZE; x++) {
                    clone.set(x, y, get(x, y));
                }
            }

            return clone;
        } catch (CloneNotSupportedException exception) {
            throw new CloneException("exception.clone", exception);
        }
    }

    public void setupDifficulty(Difficulty difficulty) {
        deleteFields(NUMBER_OF_CELLS - difficulty.getValue());
    }

    private void deleteFields(final int cellsToDelete) {
        Set<FieldCoord> coords = new HashSet<>();
        Random random = new Random();
        FieldCoord newCoord;

        // First we get random (cellsToDelete) coordinates to operate on.
        for (int i = 0; i < cellsToDelete; i++) {
            do {
                int col = random.nextInt(SIZE);
                int row = random.nextInt(SIZE);
                newCoord = new FieldCoord(col, row);
            } while (coords.contains(newCoord)); // while specified coord wasn't already used.
            coords.add(newCoord);
        }

        // Then we set those values under those coordinates.
        for (FieldCoord coord: coords) {
            set(coord.getX(), coord.getY(), 0);
        }
    }

}


