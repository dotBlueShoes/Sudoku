package pl.comp.model.solvers;

import java.io.Serializable;
import pl.comp.model.SudokuBoard;

public interface ISudokuSolver extends Serializable, Cloneable {
    void solve(SudokuBoard sudokuBoard);

    ISudokuSolver clone() throws CloneNotSupportedException;
}
