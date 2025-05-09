package pl.comp.model.solvers;

import pl.comp.model.SudokuBoard;
import pl.comp.model.annotations.ExcludeFromJacocoGeneratedReport;
import pl.comp.model.exceptions.CloneException;

public class DummyInvalidRowSolver implements ISudokuSolver {
    @Override
    public void solve(SudokuBoard sudokuBoard) {
        for (int y = 0; y < SudokuBoard.SIZE; y++) {
            for (int x = 0; x < SudokuBoard.SIZE; x++) {
                final int value = x + 1;
                sudokuBoard.set(x, y, value);
            }
        }
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public DummyInvalidRowSolver clone() throws CloneNotSupportedException {
        try {
            return (DummyInvalidRowSolver) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new CloneException("exception.clone", exception);
        }
    }

}
