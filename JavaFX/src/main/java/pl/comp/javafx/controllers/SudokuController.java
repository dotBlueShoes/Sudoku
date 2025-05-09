package pl.comp.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import pl.comp.javafx.SudokuApplication;
import pl.comp.javafx.language.Language;
import pl.comp.model.exceptions.SudokuException;

public class SudokuController {

    private void reloadScene() {
        URL url = SudokuApplication.class.getResource(SudokuApplication.INITIAL_VIEW);
        FXMLLoader loader = new FXMLLoader(url, SudokuApplication.bundle);

        Scene boardScene = null;
        try {
            boardScene = new Scene(loader.load(), 320, 320);
        } catch (IOException exception) {
            throw new SudokuException("exception.sudoku", exception);
        }

        SudokuApplication.globalStage.setTitle(SudokuApplication.bundle.getString("window.title"));
        SudokuApplication.globalStage.setScene(boardScene);
        SudokuApplication.globalStage.show();
    }

    @FXML
    public void onNewGameButtonClick(ActionEvent actionEvent) {
        URL url = SudokuApplication.class.getResource(SudokuApplication.CHOOSE_DIFFICULTY_VIEW);
        FXMLLoader fxmlLoader = new FXMLLoader(url, SudokuApplication.bundle);
        Scene newGameScene = null;
        try {
            newGameScene = new Scene(fxmlLoader.load(), 320, 320);
        } catch (IOException exception) {
            throw new SudokuException("exception.sudoku", exception);
        }
        SudokuApplication.globalStage.setScene(newGameScene);
        SudokuApplication.globalStage.show();
    }

    @FXML
    public void onLoadButtonClick(ActionEvent actionEvent) {
        SudokuApplication.loadGame();
        URL url = SudokuApplication.class.getResource(SudokuApplication.SUDOKU_BOARD_VIEW);
        FXMLLoader loader = new FXMLLoader(url, SudokuApplication.bundle);
        Scene boardScene = null;
        try {
            boardScene = new Scene(loader.load(), 440, 440);
        } catch (IOException exception) {
            throw new SudokuException("exception.sudoku", exception);
        }
        SudokuApplication.globalStage.setScene(boardScene);
        SudokuApplication.globalStage.show();
    }

    @FXML
    public void onLanguagePolishButtonClick(ActionEvent actionEvent) {
        Language.changeToPolish();
        reloadScene();
    }

    @FXML
    public void onLanguageEnglishButtonClick(ActionEvent actionEvent) {
        Language.changeToEnglish();
        reloadScene();
    }

    @FXML
    public void onExitButtonClick(ActionEvent actionEvent) throws IOException {
        Platform.exit();
    }

}