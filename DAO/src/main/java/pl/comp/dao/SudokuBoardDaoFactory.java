package pl.comp.dao;

import pl.comp.dao.exceptions.DaoFileException;
import pl.comp.model.SudokuBoard;

public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFile(String fileName) throws DaoFileException {
        Dao<SudokuBoard> dao = new FileSudokuBoardDao(fileName);
        return dao;
    }
}
