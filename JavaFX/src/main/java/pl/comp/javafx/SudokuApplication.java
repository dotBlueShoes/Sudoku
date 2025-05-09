package pl.comp.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.dao.Dao;
import pl.comp.dao.SudokuBoardDaoFactory;
import pl.comp.model.Difficulty;
import pl.comp.model.SudokuBoard;
import pl.comp.model.exceptions.CloneException;
import pl.comp.model.exceptions.SudokuException;
import pl.comp.model.solvers.BacktrackingISudokuSolver;
import pl.comp.model.solvers.ISudokuSolver;

public class SudokuApplication extends Application {

    private static Logger logger = LoggerFactory.getLogger(SudokuApplication.class);

    public static final String SUDOKU_BOARD_VIEW = "scenes/sudokuBoard-view.fxml";
    public static final String INITIAL_VIEW = "scenes/hello-view.fxml";
    public static final String CHOOSE_DIFFICULTY_VIEW = "scenes/newForm-view.fxml";
    public static final String LOCALE_BASE = "pl.comp.javafx.Lang";
    public static final String LOCALE_PL = "pl";
    public static final String LOCALE_EN = "en";
    private static final String FILEPATH_CURRENT = "CFile";
    private static final String FILEPATH_ORIGINAL = "OFile";

    public static SudokuBoard originalBoard;
    public static SudokuBoard board;
    public static Difficulty difficulty;

    public static ResourceBundle bundle;
    public static Stage globalStage;

    @Override
    public void start(Stage stage) {

        Locale english = new Locale.Builder()
                .setLanguage(LOCALE_EN)
                .setRegion(LOCALE_EN)
                .build();

        Locale.setDefault(english);

        bundle = ResourceBundle.getBundle(LOCALE_BASE, english);

        Locale polish = new Locale.Builder()
                .setLanguage(LOCALE_PL)
                .build();

        final String bundleName = "pl.comp.javafx.Authors";
        ResourceBundle authorsBundle = ResourceBundle.getBundle(bundleName, polish);

        logger.info(authorsBundle.getString("title"));
        logger.info(authorsBundle.getString("1"));

        URL url = SudokuApplication.class.getResource(INITIAL_VIEW);
        FXMLLoader loader = new FXMLLoader(url, bundle);

        Scene mainScene = null;
        try {
            mainScene = new Scene(loader.load(), 320, 320);
        } catch (IOException exception) {
            throw new SudokuException("exception.sudoku", exception);
        }

        globalStage = stage;
        stage.setTitle(bundle.getString("window.title"));
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void prepareNewGame() {
        ISudokuSolver solver = new BacktrackingISudokuSolver();
        originalBoard = new SudokuBoard(solver);
        originalBoard.solveGame();
        originalBoard.setupDifficulty(SudokuApplication.difficulty);

        // We're keeping both original and current SudokuBoard for saving purposes.
        try {
            board = originalBoard.clone();
        } catch (CloneException exception) {
            throw new SudokuException("exception.sudoku", exception);
        }

    }

    public static void loadGame() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

        // Load Current Board
        try (Dao<SudokuBoard> file = factory.getFile(FILEPATH_CURRENT)) {
            board = file.read();
        } catch (Exception exception) {
            logger.info("EXCEPTION - DaoFileException !");
            exception.printStackTrace();
        }

        // Load Original Board
        try (Dao<SudokuBoard> file = factory.getFile(FILEPATH_ORIGINAL)) {
            originalBoard = file.read();
        } catch (Exception exception) {
            logger.info("EXCEPTION - DaoFileException !");
            exception.printStackTrace();
        }

    }

    public static void saveGame() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

        // Save Current Board
        try (Dao<SudokuBoard> file = factory.getFile(FILEPATH_CURRENT)) {
            file.write(board);
        } catch (Exception exception) {
            logger.info("EXCEPTION - DaoFileException !");
            exception.printStackTrace();
        }

        // Save Original Board
        try (Dao<SudokuBoard> file = factory.getFile(FILEPATH_ORIGINAL)) {
            file.write(originalBoard);
        } catch (Exception exception) {
            logger.info("EXCEPTION - DaoFileException !");
            exception.printStackTrace();
        }

    }



}