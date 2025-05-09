package pl.comp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.comp.model.solvers.BacktrackingISudokuSolver;

public class SudokuBoardRepositoryTest {

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        SudokuBoardRepository repository = new SudokuBoardRepository(new BacktrackingISudokuSolver());
        SudokuBoard board = repository.createInstance();
        board.solveGame();
        // If true then our board has been successfully filled with values.
        //  therefore the clone happened as supposed.
        Assertions.assertTrue(board.checkBoard());
    }

}
