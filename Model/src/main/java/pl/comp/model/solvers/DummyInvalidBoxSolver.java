package pl.comp.model.solvers;

import pl.comp.model.SudokuBoard;
import pl.comp.model.annotations.ExcludeFromJacocoGeneratedReport;
import pl.comp.model.exceptions.CloneException;

public class DummyInvalidBoxSolver implements ISudokuSolver {

    private final int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private int current = 8;

    int getNext() {
        ++current;

        if (current == 9) {
            current = 0;
        }

        return values[current];
    }

    @Override
    public void solve(SudokuBoard sudokuBoard) {

        for (int y = 0; y < SudokuBoard.SIZE; y++) {
            for (int x = 0; x < SudokuBoard.SIZE; x++) {
                final int value = getNext();
                sudokuBoard.set(x, y, value);
            }
            getNext();
        }

    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public DummyInvalidBoxSolver clone() throws CloneNotSupportedException {
        try {
            return (DummyInvalidBoxSolver) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new CloneException("exception.clone", exception);
        }
    }

}
