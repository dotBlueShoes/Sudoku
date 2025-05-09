package pl.comp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.model.solvers.*;
import static pl.comp.model.Difficulty.*;

public class SudokuBoardTest {

    private static Logger logger = LoggerFactory.getLogger(SudokuBoardTest.class);
    ISudokuSolver sudokuSolver = new BacktrackingISudokuSolver();
    ISudokuSolver invalidColumnSolver = new DummyInvalidColumnSolver();
    ISudokuSolver invalidRowSolver = new DummyInvalidRowSolver();
    ISudokuSolver invalidBoxSolver = new DummyInvalidBoxSolver();

    // We check whether the board follows the rules of sudoku.
    @Test
    public void fillBoardTest() {

        { // Valid Solver Use Test
            SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
            sudokuBoard.solveGame();

            Assertions.assertTrue(sudokuBoard.checkBoard());
        }

        { // Invalid Column
            SudokuBoard sudokuBoard = new SudokuBoard(invalidColumnSolver);
            sudokuBoard.solveGame();
            Assertions.assertFalse(sudokuBoard.checkBoard());
        }

        { // Invalid Row
            SudokuBoard sudokuBoard = new SudokuBoard(invalidRowSolver);
            sudokuBoard.solveGame();
            Assertions.assertFalse(sudokuBoard.checkBoard());
        }

        { // Invalid Box
            SudokuBoard sudokuBoard = new SudokuBoard(invalidBoxSolver);
            sudokuBoard.solveGame();
            Assertions.assertFalse(sudokuBoard.checkBoard());
        }

    }

    // We check whether two generated boards are the same or not.
    @Test
    public void fillBoardRepeatTest() {
        SudokuBoard sudokuBoardInvalidOther = new SudokuBoard(invalidColumnSolver);
        SudokuBoard sudokuBoardInvalid = new SudokuBoard(invalidColumnSolver);
        SudokuBoard sudokuBoardOther = new SudokuBoard(sudokuSolver);
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
        String object = "";

        sudokuBoardInvalidOther.solveGame();
        sudokuBoardInvalid.solveGame();
        sudokuBoardOther.solveGame();
        sudokuBoard.solveGame();

        logger.info("Board: {}", sudokuBoard.toString());
        logger.info("Other: {}", sudokuBoardOther.toString());
        logger.info("Invalid1: {}", sudokuBoardInvalid.toString());
        logger.info("Invalid2: {}", sudokuBoardInvalidOther.toString());

        Assertions.assertEquals(sudokuBoardInvalid, sudokuBoardInvalidOther);
        Assertions.assertNotEquals(null, sudokuBoard);
        Assertions.assertNotEquals(object, sudokuBoard);
        Assertions.assertEquals(sudokuBoard, sudokuBoard);
        Assertions.assertNotEquals(sudokuBoard, sudokuBoardOther);

        Assertions.assertNotEquals(sudokuBoard.hashCode(), sudokuBoardOther.hashCode());
        Assertions.assertEquals(sudokuBoard.hashCode(), sudokuBoard.hashCode());
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        ISudokuSolver solver = new BacktrackingISudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBoard clone = board.clone();

        Assertions.assertEquals(board, clone); // correctness of creating copy.
        Assertions.assertNotSame(board, clone); // it's full disconnection.

        logger.info("Before Solve");
        logger.info(board.toString());
        logger.info(clone.toString());

        clone.solveGame();

        logger.info("After Solve");
        logger.info(board.toString());
        logger.info(clone.toString());

        Assertions.assertTrue(clone.checkBoard()); // works as supposed.
    }

    int getAllFieldsEqualZero(final SudokuBoard board) {
        int temp = 0;
        for (int column = 0; column < SudokuBoard.SIZE; column++) {
            for (int row = 0; row < SudokuBoard.SIZE; row++) {
                if (board.get(column, row) == 0) {
                    temp++;
                }
            }
        }
        return temp;
    }

    @Test
    public void setupDifficultyTest() {
        ISudokuSolver solver = new BacktrackingISudokuSolver();
        SudokuBoard boardEasy = new SudokuBoard(solver);
        SudokuBoard boardMedium = new SudokuBoard(solver);
        SudokuBoard boardHard = new SudokuBoard(solver);
        SudokuBoard boardUltra = new SudokuBoard(solver);

        boardEasy.solveGame();
        boardMedium.solveGame();
        boardHard.solveGame();
        boardUltra.solveGame();

        boardEasy.setupDifficulty(Easy);
        boardMedium.setupDifficulty(Medium);
        boardHard.setupDifficulty(Hard);
        boardUltra.setupDifficulty(Ultra);

        System.out.println("\nSetupDifficultyTest");
        System.out.println(getAllFieldsEqualZero(boardEasy));
        System.out.println(getAllFieldsEqualZero(boardMedium));
        System.out.println(getAllFieldsEqualZero(boardHard));
        System.out.println(getAllFieldsEqualZero(boardUltra));
        System.out.println(boardEasy);
        System.out.println(boardMedium);
        System.out.println(boardHard);
        System.out.println(boardUltra);


        Assertions.assertEquals(SudokuBoard.NUMBER_OF_CELLS - Easy.getValue(), getAllFieldsEqualZero(boardEasy));
        Assertions.assertEquals(SudokuBoard.NUMBER_OF_CELLS - Medium.getValue(), getAllFieldsEqualZero(boardMedium));
        Assertions.assertEquals(SudokuBoard.NUMBER_OF_CELLS - Hard.getValue(), getAllFieldsEqualZero(boardHard));
        Assertions.assertEquals(SudokuBoard.NUMBER_OF_CELLS - Ultra.getValue(), getAllFieldsEqualZero(boardUltra));
    }

}