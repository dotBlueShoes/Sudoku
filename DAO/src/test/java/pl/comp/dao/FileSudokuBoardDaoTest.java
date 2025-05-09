package pl.comp.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pl.comp.dao.exceptions.DaoInOuException;
import pl.comp.model.SudokuBoard;
import pl.comp.model.solvers.ISudokuSolver;
import pl.comp.model.solvers.BacktrackingISudokuSolver;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileSudokuBoardDaoTest {

    private final String filePath = "src/test/testWriteToFile";
    private final ISudokuSolver solver = new BacktrackingISudokuSolver();
    private final SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private final SudokuBoard board = new SudokuBoard(solver);
    private SudokuBoard other;

    @AfterEach
    public void deleteFile() {
        File file = new File(filePath);
        file.delete();
    }

    @Test
    public void writeAndReadTest() {
        board.solveGame();

        try (Dao<SudokuBoard> boardDao = factory.getFile(filePath)) {
            boardDao.write(board);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try (Dao<SudokuBoard> boardDao = factory.getFile(filePath)) {
            other = boardDao.read();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        assertEquals(board, other);
    }

    @Test
    public void daoInOuExceptionTest() {

        /* File does not exist. */
        assertThrows(DaoInOuException.class, () -> {
            Dao<SudokuBoard> boardDao = factory.getFile(filePath);
            boardDao.read();
        });

        /* File name cannot be represented as such string name. */
        assertThrows(DaoInOuException.class, () -> {
            Dao<SudokuBoard> boardDao = factory.getFile("!@$%^/\\&\0#");
            boardDao.write(board);
        });

    }

}
