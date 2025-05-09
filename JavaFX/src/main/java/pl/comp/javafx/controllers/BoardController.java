package pl.comp.javafx.controllers;

import static pl.comp.javafx.SudokuApplication.board;
import static pl.comp.javafx.SudokuApplication.originalBoard;

import java.io.IOException;
import java.net.URL;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import pl.comp.javafx.SudokuApplication;
import pl.comp.javafx.converters.SudokuFieldConverter;
import pl.comp.javafx.helpers.SudokuFieldHelper;
import pl.comp.javafx.language.Language;
import pl.comp.model.SudokuBoard;
import pl.comp.model.exceptions.SudokuException;

public class BoardController {

    private final JavaBeanIntegerProperty[][] properties = new JavaBeanIntegerProperty[9][9];

    @FXML
    private TextField[][] fields = new TextField[9][9];

    @FXML
    private GridPane grid = new GridPane();

    private StringConverter sudokuFieldConverter = new SudokuFieldConverter();

    private void setupSudokuField(int col, int row) {

        TextField textField = fields[col][row] = new TextField();

        textField.setMinSize(30, 30);
        textField.setMaxSize(30,30);

        if (board.get(col, row) != 0 && board.get(col, row) == originalBoard.get(col, row)) {
            textField.setEditable(false);
        }

        // javafx.beans.property.adapter
        // - Adapters between a regular Java Bean property and a corresponding JavaFX Property.
        try {
            properties[col][row] = JavaBeanIntegerPropertyBuilder
                .create()
                .bean(new SudokuFieldHelper(board, col, row))
                .name("Field")
                .build();
        } catch (NoSuchMethodException exception) {
            exception.printStackTrace();
        }

        // Connection TextField <-> StringProperty
        textField.textProperty().bindBidirectional(properties[col][row], sudokuFieldConverter);

        // Callback -> value checker.
        textField.textProperty().addListener((observableValue, previousValue, newValue) -> {
            if (!(SudokuFieldHelper.PATTERN.matcher(newValue).matches() || newValue.equals(""))) {
                textField.setText(previousValue);
            }
        });

        grid.add(textField, col, row);
    }

    @FXML
    public void initialize() {
        grid.setMinSize(30 * SudokuBoard.SIZE, 30 * SudokuBoard.SIZE);
        grid.setMaxSize(30 * SudokuBoard.SIZE, 30 * SudokuBoard.SIZE);
        grid.getChildren().removeAll();
        for (int col = 0; col < SudokuBoard.SIZE; col++) {
            for (int row = 0; row < SudokuBoard.SIZE; row++) {
                setupSudokuField(col, row);
            }
        }
        grid.setGridLinesVisible(true);
    }

    private void restartLevel() throws IOException {
        URL url = SudokuApplication.class.getResource(SudokuApplication.SUDOKU_BOARD_VIEW);
        FXMLLoader loader = new FXMLLoader(url, SudokuApplication.bundle);
        Scene boardScene = new Scene(loader.load(), 440, 440);
        SudokuApplication.globalStage.setTitle(SudokuApplication.bundle.getString("window.title"));
        SudokuApplication.globalStage.setScene(boardScene);
        SudokuApplication.globalStage.show();
    }

    @FXML
    public void onExitButtonClick(ActionEvent ignoredActionEvent) {
        URL url = SudokuApplication.class.getResource(SudokuApplication.INITIAL_VIEW);
        FXMLLoader fxmlLoader = new FXMLLoader(url, SudokuApplication.bundle);
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 320, 320);
        } catch (IOException exception) {
            throw new SudokuException("exception.sudoku", exception);
        }
        SudokuApplication.globalStage.setScene(scene);
        SudokuApplication.globalStage.show();
    }

    @FXML
    public void onSaveButtonClick(ActionEvent ignoredActionEvent) {
        SudokuApplication.saveGame();
    }

    @FXML
    public void onLanguagePolishButtonClick(ActionEvent ignoredActionEvent) {
        Language.changeToPolish();
        try {
            restartLevel();
        } catch (IOException exception) {
            throw new SudokuException("exception.sudoku", exception);
        }
    }

    @FXML
    public void onLanguageEnglishButtonClick(ActionEvent ignoredActionEvent) {
        Language.changeToEnglish();
        try {
            restartLevel();
        } catch (IOException exception) {
            throw new SudokuException("exception.sudoku", exception);
        }
    }

}
