package pl.comp.model;

import pl.comp.model.solvers.ISudokuSolver;

public class SudokuBoardRepository {
        private SudokuBoard board;

    public SudokuBoardRepository(ISudokuSolver solver) {
        board = new SudokuBoard(solver);
    }

    public SudokuBoard createInstance() throws CloneNotSupportedException {
        return board.clone();
    }
}
