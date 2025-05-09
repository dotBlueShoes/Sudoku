package pl.comp.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import pl.comp.dao.exceptions.DaoInOuException;
import pl.comp.model.SudokuBoard;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private final String fileName;

    FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws DaoInOuException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (SudokuBoard) inputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            throw new DaoInOuException("exception.io", exception);
        }
    }

    @Override
    public void write(SudokuBoard sudokuBoard) throws DaoInOuException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
            new FileOutputStream(fileName))
        ) {
            outputStream.writeObject(sudokuBoard);
        } catch (IOException error) {
            throw new DaoInOuException("exception.io", error);
        }
    }

    @Override
    public void close() {
        // System.out.println("DAO:close");
    }

}
